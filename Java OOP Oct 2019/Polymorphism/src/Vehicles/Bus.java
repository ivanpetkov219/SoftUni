package Vehicles;

public class Bus extends VehicleImpl {

    private static final double DRIVING_FULL_FUEL_CONSUMPTION_INCREASE = 1.4;

    public Bus(double fuelQuantity, double consumptionPerKm, double tankCapacity) {
        super(fuelQuantity, consumptionPerKm + DRIVING_FULL_FUEL_CONSUMPTION_INCREASE, tankCapacity);
    }

    public void driveEmpty(double distance) {
        double currentFuelConsumption = this.getConsumptionPerKm() - DRIVING_FULL_FUEL_CONSUMPTION_INCREASE;
        if (this.getFuelQuantity() >= currentFuelConsumption * distance){
            this.setFuelQuantity(this.getFuelQuantity() - (currentFuelConsumption * distance));
            printTravellMessage(distance);
        } else {
            printNeedsRefuelMessage();
        }
    }

}
