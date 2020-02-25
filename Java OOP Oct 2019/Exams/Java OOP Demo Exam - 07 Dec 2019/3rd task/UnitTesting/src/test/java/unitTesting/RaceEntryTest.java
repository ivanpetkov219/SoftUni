package unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceEntryTest {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS RaceEntry
    private Map<String, UnitRider> riders;
    private UnitMotorcycle unitMotorcycle;
    private RaceEntry raceEntry;


    @Before
    public void setUp(){
         this.riders = new LinkedHashMap<>();
         this.unitMotorcycle = new UnitMotorcycle("Honda", 100, 100);
         raceEntry = new RaceEntry();
    }

    @Test
    public void addRider_addsRiderWithCorrectParam(){

        UnitRider rider = new UnitRider("Vankata", unitMotorcycle);

        riders.put(rider.getName(), rider);

        Assert.assertEquals(1, riders.size());
    }

    @Test(expected = NullPointerException.class)
    public void addRider_ThrowsWithNullRider(){

        UnitRider rider = null;

        riders.put(rider.getName(), rider);

    }

   @Test(expected = IllegalArgumentException.class)
   public void addRider_ThrowsWithExcistingRider(){

       UnitRider rider = new UnitRider("Vankata", unitMotorcycle);
       UnitRider rider2 = new UnitRider("Pesho", unitMotorcycle);

       raceEntry.addRider(rider);
       raceEntry.addRider(rider);

   }

    @Test(expected = IllegalArgumentException.class)
    public void calculateAverageHorsePower_ThrowsWithLessThanEnoughParticipants(){

        UnitRider rider = new UnitRider("Vankata", unitMotorcycle);

        raceEntry.addRider(rider);

        raceEntry.calculateAverageHorsePower();

    }

    @Test
    public void calculateAverageHorsePower_ReturnsCorrectResult(){

        UnitRider rider = new UnitRider("Vankata", unitMotorcycle);

        UnitMotorcycle unitMotorcycle2 = new UnitMotorcycle("Kawazaki", 200, 200);

        UnitRider rider2 = new UnitRider("Pesho", unitMotorcycle2);

        raceEntry.addRider(rider);
        raceEntry.addRider(rider2);

        double result = raceEntry.calculateAverageHorsePower();

        Assert.assertEquals(Double.doubleToLongBits(150.0), Double.doubleToLongBits(result));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void getRiders_ThrowsWithUnmodifiableList(){

        raceEntry.getRiders().clear();

    }


}
