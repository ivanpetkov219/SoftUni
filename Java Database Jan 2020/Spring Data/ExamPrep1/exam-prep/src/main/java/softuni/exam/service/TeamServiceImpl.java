package softuni.exam.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.TeamSeedRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;


import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.constants.GlobalConstants.TEAMS_FILE_PATH;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PictureService pictureService;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public TeamServiceImpl(TeamRepository teamRepository, PictureService pictureService, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.teamRepository = teamRepository;
        this.pictureService = pictureService;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    
    public String importTeams() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TeamSeedRootDto teamSeedRootDto = this.xmlParser.convertFromFile(TEAMS_FILE_PATH, TeamSeedRootDto.class);

        teamSeedRootDto.getTeams().stream().forEach(teamSeedDto -> {

            if(this.validationUtil.isValid(teamSeedDto)){
                if(this.teamRepository.findByName(teamSeedDto.getName()) == null){


                   if(this.pictureService.getPictureByUrl(teamSeedDto.getPicture().getUrl()) != null){
                       Team team = this.modelMapper.map(teamSeedDto, Team.class);

                       Picture picture = this.pictureService.getPictureByUrl(teamSeedDto.getPicture().getUrl());

                    team.setPicture(picture);

                       sb.append(String.format("Successfully imported - %s", teamSeedDto.getName()));

                       this.teamRepository.saveAndFlush(team);


                   }else {
                       sb.append("Invalid team");
                   }


                }else {
                    sb.append("Team is already in the database!");
                }

            }else {
                sb.append("Invalid team");
            }

            sb.append(System.lineSeparator());

        });






       return sb.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return Files.readString(Path.of(TEAMS_FILE_PATH));
    }

    @Override
    public Team getTeamByName(String name) {
        return this.teamRepository.findByName(name);
    }

}
