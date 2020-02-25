package viceCity.core.interfaces;

import viceCity.common.ConstantMessages;
import viceCity.models.guns.BaseGun;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
        private MainPlayer mainPlayer;
        private CivilPlayer civilPlayer;
        private List<CivilPlayer> civilPlayers;
        private Repository<Gun> gunRepository;
        private GangNeighbourhood gangNeighbourhood;

    public ControllerImpl(){
        mainPlayer = new MainPlayer();
        civilPlayers = new LinkedList<>();
        gunRepository = new GunRepository();
        gangNeighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        civilPlayers.add(new CivilPlayer(name));
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        BaseGun gun;
        String result;

        switch (type){
            case "Pistol":
                gun = new Pistol(name);
                result = String.format(ConstantMessages.GUN_ADDED,type, name);
                gunRepository.add(gun);
                break;
            case "Rifle":
                gun = new Rifle(name);
                result = String.format(ConstantMessages.GUN_ADDED,type, name);
                gunRepository.add(gun);
                break;
                default:
                    result = ConstantMessages.GUN_TYPE_INVALID;
        }
        return result;
    }

    @Override
    public String addGunToPlayer(String name) {
        String result;

        Gun gun = gunRepository.getModels().stream().findFirst().orElse(null);

        if(name.equals("Vercetti")){
            mainPlayer.getGunRepository().add(gun);
            result = String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }else {
            CivilPlayer civilPlayer = civilPlayers.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
            if(civilPlayer != null){
                result = String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
            }else {
                result = ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
            }
        }

        return result;
    }

    @Override
    public String fight() {
      this.gangNeighbourhood.action(mainPlayer, new LinkedList<>(this.civilPlayers));







        return null;
    }
}
