package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public abstract class MotorcycleImpl implements Motorcycle {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setHorsePower(int horsePower) {
        if(!validHorsePower(horsePower)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        this.horsePower = horsePower;
    }

    protected abstract boolean validHorsePower(int horsePower);


    private void setModel(String model){
        if (model == null || model.trim().isEmpty() || model.length() < 4){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL, model, 4));
        }
        this.model = model;
    }

    public String getModel(){
        return this.model;
    }

    public int getHorsePower(){
        return this.horsePower;
    }

    public double getCubicCentimeters(){
        return this.cubicCentimeters;
    }

    public double calculateRacePoints(int laps){
        return this.cubicCentimeters / (this.horsePower * laps);
    }


}
