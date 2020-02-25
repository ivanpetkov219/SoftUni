package Vehicles;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double consumptionPerKm;
    private double tankCapacity;


    public VehicleImpl(double fuelQuantity, double consumptionPerKm, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.consumptionPerKm = consumptionPerKm;
        this.tankCapacity = tankCapacity;
    }

    public void drive(double distance) {
        if (hasEnoughFuelToDrive(distance)) {
            reduceFuelQuantity(distance);
            printTravellMessage(distance);
        } else {
            printNeedsRefuelMessage();
        }
    }

    public void refuel(double liters) {
        if (liters > 0) {
            double result = this.getFuelQuantity() + liters;
            if (result > this.tankCapacity) {
                System.out.println("Cannot fit fuel in tank");
            } else {
                if (this.getClass().getSimpleName().equals("Truck")) {
                    this.fuelQuantity += liters * 0.95;
                } else {
                    this.fuelQuantity += liters;
                }
            }
        } else {
            System.out.println("Fuel must be a positive number");
        }
    }

    protected boolean hasEnoughFuelToDrive(double distance) {
        return this.getFuelQuantity() >= this.consumptionPerKm * distance;
    }

    protected void reduceFuelQuantity(double distance) {
        this.setFuelQuantity(this.getFuelQuantity() - (this.getConsumptionPerKm() * distance));
    }

    protected void printTravellMessage(double distance) {

        DecimalFormat output = new DecimalFormat("#.##");
        System.out.println(String.format("%s travelled %s km", this.getClass().getSimpleName(), output.format(distance)));
    }

    protected void printNeedsRefuelMessage() {
        System.out.println(String.format("%s needs refueling", this.getClass().getSimpleName()));
    }

    protected double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
          this.fuelQuantity = fuelQuantity;
    }

    protected double getConsumptionPerKm() {
        return this.consumptionPerKm;
    }
}
