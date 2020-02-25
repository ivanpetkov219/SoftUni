package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {

    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium

    @Test
    public void getName_ReturnsCorrectResult(){
        Aquarium aquarium = new Aquarium("Name", 10);
        Assert.assertEquals("Name", aquarium.getName());
    }

    @Test
    public void getCapacity_ReturnsCorrectResult(){
        Aquarium aquarium = new Aquarium("Name", 10);


        Assert.assertEquals(10, aquarium.getCapacity());
    }

    @Test
    public void getCount_ReturnsCorrectResult(){
        Aquarium aquarium = new Aquarium("Name", 10);
        Fish fish = new Fish("Vankata");
        aquarium.add(fish);

        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test
    public void addFish_ReturnsCorrectResult(){
        Aquarium aquarium = new Aquarium("Name", 10);
        Fish fish = new Fish("Vankata");
        aquarium.add(fish);
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFish_ThrowsWithFullCapacity(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");
        Fish fish2 = new Fish("Vankata");
        aquarium.add(fish);
        aquarium.add(fish2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFish_ThrowsWithNonExcistingFish(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");

        aquarium.add(fish);

        aquarium.remove("Fish");
    }

    @Test(expected = NullPointerException.class)
    public void removeFish_ThrowsWithNullFish(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");

        aquarium.add(fish);

        aquarium.remove(null);
    }

    @Test//(expected = IllegalArgumentException.class)
    public void removeFish_RemovesSuccessfullyFish(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");

        aquarium.add(fish);

        aquarium.remove("Vankata");

        Assert.assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellFish_ThrowsWithNonExcistingFish(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");

        aquarium.add(fish);

        aquarium.sellFish("Fish");
    }

    @Test//(expected = IllegalArgumentException.class)
    public void sellFish_setsAvailabilityToFalse(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");

        aquarium.add(fish);

        aquarium.sellFish("Vankata");

        Assert.assertFalse(fish.isAvailable());
    }

    @Test//(expected = IllegalArgumentException.class)
    public void sellFish_returnsFish(){
        Aquarium aquarium = new Aquarium("Name", 1);
        Fish fish = new Fish("Vankata");

        aquarium.add(fish);

        aquarium.sellFish("Vankata");

        Assert.assertEquals(fish,aquarium.sellFish("Vankata"));
    }

    @Test//(expected = IllegalArgumentException.class)
    public void report_returnsCorrectResult(){
        Aquarium aquarium = new Aquarium("Name", 2);
        Fish fish = new Fish("Vankata");
        Fish fish2 = new Fish("Pesho");

        aquarium.add(fish);
        aquarium.add(fish2);

        Assert.assertEquals("Fish available at Name: Vankata, Pesho",aquarium.report());
    }




}

