package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood { //TODO

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        //SHOOTS BULLETS, RELOADS IF EMPTY AND CONTINUES UNTIL NO BULLETS. THEN TAKES THE NEXT GUN
        //STOPS WHEN GUNREPO IS EMPTY OR ALL CIVILS ARE DEAD
        //

        while (mainPlayer.isAlive() && !civilPlayers.isEmpty()){
            Gun gun = mainPlayer.getGunRepository().getModels().stream().findFirst().orElse(null);
            if(gun == null){
                break;
            }
            int damage = gun.fire();

            CivilPlayer civilPlayer = (CivilPlayer) civilPlayers.stream().findFirst().orElse(null);

            civilPlayer.takeLifePoints(damage);
        }

    }


}
