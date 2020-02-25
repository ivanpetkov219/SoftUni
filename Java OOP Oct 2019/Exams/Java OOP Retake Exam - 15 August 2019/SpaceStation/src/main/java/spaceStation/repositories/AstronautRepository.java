package spaceStation.repositories;

import models.astronauts.BaseAstronaut;
import spaceStation.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AstronautRepository implements Repository {
    private List<models.astronauts.BaseAstronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }


    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(astronauts);
    }

    @Override
    public void add(Object model) {
        astronauts.add((BaseAstronaut) model);
    }

    @Override
    public boolean remove(Object model) {
        return astronauts.remove(model);

    }

    @Override
    public Object findByName(String name) {
        for (models.astronauts.BaseAstronaut astronaut : astronauts) {
            if(astronaut.getName().equals(name)){
                return astronaut;
            }
        }
        return ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST;
    }
}
