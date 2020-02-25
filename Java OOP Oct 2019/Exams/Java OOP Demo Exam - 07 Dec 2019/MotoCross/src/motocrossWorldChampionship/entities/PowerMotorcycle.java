package motocrossWorldChampionship.entities;



public class PowerMotorcycle extends MotorcycleImpl {
    private static final int MINIMUM_HORSE_POWER = 70;
    private static final int MAXIMUM_HORSE_POWER = 100;
    private static final double DEFAULT_CUBIC_CENTIMETERS = 450;


    public PowerMotorcycle(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);

    }

    protected boolean validHorsePower(int horsePower) {
       return horsePower >= MINIMUM_HORSE_POWER && horsePower <= MAXIMUM_HORSE_POWER;
    }
}
