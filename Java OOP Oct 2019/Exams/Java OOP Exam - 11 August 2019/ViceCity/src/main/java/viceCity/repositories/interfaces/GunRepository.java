package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;


public class GunRepository implements Repository<Gun> {
    private Collection<Gun> models;

    public GunRepository (){
        this.models = new LinkedList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Gun model) {
        if(!models.contains(model)){
            models.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return models.remove(model);
    }

    @Override
    public Gun find(String name) {

        return models.stream().filter(model -> model.getName().equals(name)).findFirst().orElse(null);
    }
}
