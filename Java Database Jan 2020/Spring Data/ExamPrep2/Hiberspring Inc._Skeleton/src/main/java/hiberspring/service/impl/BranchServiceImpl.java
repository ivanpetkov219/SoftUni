package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchSeedDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
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
public class BranchServiceImpl implements BranchService {

    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;

    public BranchServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, BranchRepository branchRepository, TownRepository townRepository) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(Path.of(BRANCHES_FILE_PATH));
    }

    @Override
    public String importBranches(String branchesFileContent) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        BranchSeedDto[] dtos = this.gson.fromJson(new FileReader(BRANCHES_FILE_PATH), BranchSeedDto[].class);

        Arrays.stream(dtos).forEach(branchSeedDto -> {

            if(this.validationUtil.isValid(branchSeedDto)) {
                if (this.branchRepository.findByName(branchSeedDto.getName()) == null) {
                    if(this.townRepository.findByName(branchSeedDto.getTown()) != null) {

                        Branch branch = this.modelMapper.map(branchSeedDto, Branch.class);

                        Town town = this.townRepository.findByName(branchSeedDto.getTown());

                        this.branchRepository.saveAndFlush(branch);

                        result.append(String.format(SUCCESSFUL_IMPORT_MESSAGE, branch.getClass().getSimpleName(), branch.getName()));

                    }else {
                        result.append("Town does not exist!");
                    }
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
