package spaceStation.repositories;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.planets.PlanetImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlanetRepository implements Repository {

    private List<PlanetImpl> planets;

    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(planets);
    }

    @Override
    public void add(Object model) {
        planets.add((PlanetImpl) model);

    }

    @Override
    public boolean remove(Object model) {
        return planets.remove(model);
    }

    @Override
    public Object findByName(String name) {
        for (PlanetImpl planet : planets) {
            if (planet.getName().equals(name)) {
                return planet;
            }
        }
        return null;
    }

}
