package Vehicles;

public class Truck extends VehicleImpl {

    private static final double SUMMER_FUEL_CONSUMPTION_INCREASE = 1.6;

    public Truck (double fuelQuantity, double consumptionPerKm, double tankCapacity) {
        super(fuelQuantity, consumptionPerKm + SUMMER_FUEL_CONSUMPTION_INCREASE, tankCapacity);
    }
}
