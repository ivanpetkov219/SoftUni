package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RiderRepository implements Repository<Rider> {

    private final Collection<Rider> models;

    public RiderRepository(){
        this.models = new ArrayList<>();
    }
    @Override
    public Rider getByName(String name) {
        return this.models.stream().filter(rider -> rider.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Rider model) {
        if(this.models.contains(model)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS, model.getName()));
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Rider model) {
        if(!this.models.contains(model)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_FOUND, model.getName()));
        }
        return this.models.remove(model);
    }
}
