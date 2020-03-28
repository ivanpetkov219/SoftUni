package softuni.exam.service;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.PlayerSeedDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.util.ValidationUtil;


import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.GlobalConstants.PLAYERS_FILE_PATH;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final PictureService pictureService;
    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, PictureService pictureService, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.pictureService = pictureService;
        this.teamService = teamService;
    }


    @Override
    public String importPlayers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayerSeedDto[] playerSeedDto = this.gson
                .fromJson(new FileReader(PLAYERS_FILE_PATH), PlayerSeedDto[].class);

        Arrays.stream(playerSeedDto).forEach(dto -> {

            if(this.validationUtil.isValid(dto)){
                if(this.playerRepository.findByFirstNameAndLastNameAndNumber(dto.getFirstName(),
                        dto.getLastName(), dto.getNumber()) == null) {

                        Player player = this.modelMapper.map(dto, Player.class);

                        Picture picture = this.pictureService.getPictureByUrl(dto.getPictureSeedDto().getUrl());

                        Team team = this.teamService.getTeamByName(dto.getTeamSeedDto().getName());

                        player.setPicture(picture);
                        player.setTeam(team);

                        this.playerRepository.saveAndFlush(player);
                        sb.append(String.format("Successfully imported player: %s %s", dto.getFirstName(), dto.getLastName()));

                }else {
                    sb.append("Player is already in the database!");
                }

            }else {
                sb.append("Invalid player");
            }

            sb.append(System.lineSeparator());


        });

       return sb.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
       return "";
    }

    @Override
    public String exportPlayersInATeam() {
        return "";
    }


}
