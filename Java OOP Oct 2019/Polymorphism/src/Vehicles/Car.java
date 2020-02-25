package Vehicles;

public class Car extends VehicleImpl {

    private static final double SUMMER_FUEL_CONSUMPTION_INCREASE = 0.9;

    public Car(double fuelQuantity, double consumptionPerKm, double tankCapacity) {
        super(fuelQuantity, consumptionPerKm + SUMMER_FUEL_CONSUMPTION_INCREASE, tankCapacity);
    }

}
