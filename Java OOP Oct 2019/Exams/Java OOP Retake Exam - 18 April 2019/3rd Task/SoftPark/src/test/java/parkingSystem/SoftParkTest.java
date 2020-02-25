package parkingSystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoftParkTest {

    private SoftPark softPark;
        //TODO ->

   @Before
   public void setUp(){
       softPark = new SoftPark();

   }

    @Test(expected = UnsupportedOperationException.class)
    public void getParking_ThrowsWhenTryingToModifyList(){
       softPark.getParking().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkCar_ThrowsWhenParkingSpotDoesntExist(){
        Car car = new Car("Honda", "8777");
        softPark.parkCar("K1", car);
    }

    @Test(expected = IllegalStateException.class)
    public void parkCar_ThrowsWhenParkingSpotIsTaken(){
        Car car = new Car("Honda", "8777");
        softPark.parkCar("A1", car);
        softPark.parkCar("B1", car);
    }

    @Test//(expected = IllegalStateException.class)
    public void parkCar_ReturnsCorrectResult(){
        Car car = new Car("Honda", "8777");

        Assert.assertEquals("Car:8777 parked successfully!", softPark.parkCar("A1", car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCar_ThrowsWhenParkingSpotDoesntExist(){
        Car car = new Car("Honda", "8777");
        softPark.removeCar("K1", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCar_ThrowsWhenCarDoesntExist(){
        Car car = new Car("Honda", "8777");
        softPark.removeCar("A1", car);
    }
    @Test//(expected = IllegalStateException.class)
    public void removeCar_ReturnsCorrectResult(){
        Car car = new Car("Honda", "8777");
        softPark.parkCar("A1", car);

        Assert.assertEquals("Remove car:8777 successfully!", softPark.removeCar("A1", car));
    }





}