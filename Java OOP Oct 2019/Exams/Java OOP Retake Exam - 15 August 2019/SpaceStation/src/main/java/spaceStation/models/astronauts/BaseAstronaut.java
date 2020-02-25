package models.astronauts;

import models.bags.Backpack;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Bag;

import java.util.ArrayList;

public abstract class BaseAstronaut implements spaceStation.models.astronauts.Astronaut {
    private String name;
    private double oxygen;
    private Backpack bag;

    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.oxygen = oxygen;
    }

    private void setName(String name) {
       if (name == null || name.trim().isEmpty()){
           throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
       }
       this.name = name;
    }

    private void setOxygen(double oxygen) {
        if(oxygen < 0){
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }

        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.getOxygen() > 10;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    public void breath() {
        if (canBreath()) {
            this.setOxygen(this.getOxygen() - 10);
        }
    }

}
