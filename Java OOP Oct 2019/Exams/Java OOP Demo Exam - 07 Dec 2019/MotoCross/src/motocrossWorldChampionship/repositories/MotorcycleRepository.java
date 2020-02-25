package motocrossWorldChampionship.repositories;

import java.util.*;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

public class MotorcycleRepository implements Repository<Motorcycle> {

    private Collection<Motorcycle> models;

    public MotorcycleRepository(){
        this.models = new ArrayList<>();
    }


    @Override
    public Motorcycle getByName(String name) {
        return this.models.stream().filter(motorcycle -> motorcycle.getModel().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Motorcycle model) {
        if(this.models.contains(model)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model.getModel()));
        }

        this.models.add(model);
    }

    @Override
    public boolean remove(Motorcycle model) {
        if(!this.models.contains(model)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND, model.getModel()));
        }
        return this.models.remove(model);
    }


}
