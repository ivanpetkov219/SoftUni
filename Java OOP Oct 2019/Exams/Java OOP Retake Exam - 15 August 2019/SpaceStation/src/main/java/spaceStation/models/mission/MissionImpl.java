package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {


    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        for (Astronaut astronaut : astronauts) {
            if (astronaut.canBreath()) {
                List<String> items = new ArrayList<>(planet.getItems());



            }
        }
    }
}
