package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dtos.EmployeeCardSeedDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static hiberspring.common.GlobalConstants.*;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final EmployeeCardRepository employeeCardRepository;

    public EmployeeCardServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, EmployeeCardRepository employeeCardRepository) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEE_CARDS_FILE_PATH));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        EmployeeCardSeedDto[] dtos = this.gson.fromJson(new FileReader(EMPLOYEE_CARDS_FILE_PATH), EmployeeCardSeedDto[].class);

        Arrays.stream(dtos).forEach(card -> {
            if(this.validationUtil.isValid(card)){
                if(this.employeeCardRepository.findByNumber(card.getNumber()) == null){
                    EmployeeCard employeeCard = this.modelMapper.map(card, EmployeeCard.class);

                    this.employeeCardRepository.saveAndFlush(employeeCard);

                    result.append(String.format(SUCCESSFUL_IMPORT_MESSAGE, employeeCard.getClass().getSimpleName(), employeeCard.getNumber()));


                }else {
                    result.append("Employee card already exists!");
                }


            }else {
                result.append(INCORRECT_DATA_MESSAGE);
            }
            result.append(System.lineSeparator());



        });







        return result.toString().trim();
    }
}
