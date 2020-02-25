package motocrossWorldChampionship.entities;


public class SpeedMotorcycle extends MotorcycleImpl{
    private static final int MINIMUM_HORSE_POWER = 50;
    private static final int MAXIMUM_HORSE_POWER = 69;
    private static final double DEFAULT_CUBIC_CENTIMETERS = 125;

    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }


    protected boolean validHorsePower(int horsePower) {
        return horsePower >= MINIMUM_HORSE_POWER && horsePower <= MAXIMUM_HORSE_POWER;
    }
}
