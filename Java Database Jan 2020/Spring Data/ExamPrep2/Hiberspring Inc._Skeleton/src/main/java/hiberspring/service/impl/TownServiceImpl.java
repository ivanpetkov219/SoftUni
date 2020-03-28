package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dtos.TownSeedDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
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
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns(String townsFileContent) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        TownSeedDto[] dtos = this.gson.fromJson(new FileReader(TOWNS_FILE_PATH), TownSeedDto[].class);

        Arrays.stream(dtos).forEach(townSeedDto -> {

            if(this.validationUtil.isValid(townSeedDto)) {
                if (this.townRepository.findByName(townSeedDto.getName()) == null) {
                    Town town = this.modelMapper.map(townSeedDto, Town.class);

                    this.townRepository.saveAndFlush(town);

                    result.append(String.format(SUCCESSFUL_IMPORT_MESSAGE, town.getClass().getSimpleName(), town.getName()));


                } else {
                    result.append("Town is already in Database!");
                }
            }else {
                result.append(INCORRECT_DATA_MESSAGE);
            }
            result.append(System.lineSeparator());



        });





        return result.toString().trim();
    }
}
