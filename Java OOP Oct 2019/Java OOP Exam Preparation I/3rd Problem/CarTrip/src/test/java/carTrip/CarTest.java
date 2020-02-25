package carTrip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {


    @Test
    public void getModel_ReturnsCorrectResult(){
        Car car = new Car("Car", 60, 20, 10);

        Assert.assertEquals("Car", car.getModel());
    }
    @Test
    public void getTankCapacity_ReturnsCorrectResult(){
        Car car = new Car("Car", 60, 20, 10);

        Assert.assertEquals(Double.doubleToLongBits(60), Double.doubleToLongBits(car.getTankCapacity()));
    }

    @Test
    public void getFuelAmount_ReturnsCorrectResult(){
        Car car = new Car("Car", 60, 20, 10);

        Assert.assertEquals(Double.doubleToLongBits(20), Double.doubleToLongBits(car.getFuelAmount()));
    }

    @Test
    public void getFuelConsumptionPerKm_ReturnsCorrectResult(){
        Car car = new Car("Car", 60, 20, 10);

        Assert.assertEquals(Double.doubleToLongBits(10), Double.doubleToLongBits(car.getFuelConsumptionPerKm()));
    }

    @Test
    public void setTankCapacity_SetsCorrectlyParameter(){
        Car car = new Car("Car", 60, 20, 10);

        car.setTankCapacity(40);

        Assert.assertEquals(Double.doubleToLongBits(40), Double.doubleToLongBits(car.getTankCapacity()));
    }

    @Test
    public void setFuelAmount_SetsCorrectlyParameter(){
        Car car = new Car("Car", 60, 20, 10);

        car.setFuelAmount(40);

        Assert.assertEquals(Double.doubleToLongBits(40), Double.doubleToLongBits(car.getFuelAmount()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmount_ThrowsWhenMoreThanCapacity(){
        Car car = new Car("Car", 60, 20, 10);

        car.setFuelAmount(100);
    }

    @Test
    public void setFuelConsumptionPerKm_SetsCorrectlyParameter(){
        Car car = new Car("Car", 60, 20, 10);

        car.setFuelConsumptionPerKm(40);

        Assert.assertEquals(Double.doubleToLongBits(40), Double.doubleToLongBits(car.getFuelConsumptionPerKm()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelConsumptionPerKm_ThrowsWhenNegative(){
        Car car = new Car("Car", 60, 20, 10);

        car.setFuelConsumptionPerKm(-100);
    }

    @Test
    public void setModel_SetsCorrectlyParameter(){
        Car car = new Car("Car", 60, 20, 10);

        car.setModel("40");

        Assert.assertEquals("40", car.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModel_ThrowsWhenNull(){
        Car car = new Car(null, 60, 20, 10);


    }

    @Test(expected = IllegalArgumentException.class)
    public void setModel_ThrowsWhenEmpty(){
        Car car = new Car("Car", 60, 20, 10);

        car.setModel("");
    }

    @Test(expected = IllegalStateException.class)
    public void drive_ThrowsWhenFuelNotEnough(){
        Car car = new Car("Car", 60, 20, 1);

        double tripConsumotion = 1000 * car.getFuelConsumptionPerKm();

        car.drive(1000);

    }

    @Test//(expected = IllegalStateException.class)
    public void drive_ReturnsCorrectResult(){
        Car car = new Car("Car", 60, 20, 1);

        double tripConsumotion = 1 * car.getFuelConsumptionPerKm();

        String message = car.drive(1);

        Assert.assertEquals("Have a nice trip", car.drive(1));

    }

    @Test(expected = IllegalStateException.class)
    public void refuel_ThrowsWhenFuelIsOverTankCapacity(){
        Car car = new Car("Car", 60, 20, 1);

        double totalFuelAmount = car.getFuelAmount() + 1000;

        car.refuel(1000);

    }

    @Test//(expected = IllegalStateException.class)
    public void refuel_ReturnsCorrectResult(){
        Car car = new Car("Car", 60, 20, 1);

        double totalFuelAmount = car.getFuelAmount() + 1;

        car.refuel(1);

        Assert.assertEquals(Double.doubleToLongBits(totalFuelAmount),Double.doubleToLongBits(car.getFuelAmount()));

    }

    @Test//(expected = IllegalStateException.class)
    public void refuel_ReturnsCorrectMessageWithException(){
        Car car = new Car("Car", 60, 20, 1);

        double totalFuelAmount = car.getFuelAmount() + 1000;
        String message = null;
        try {
            car.refuel(1000);
        }catch (IllegalStateException ex){
           message = ex.getMessage();
        }


        Assert.assertEquals("Cannot fill 20.00 in the tank", message);

    }



}