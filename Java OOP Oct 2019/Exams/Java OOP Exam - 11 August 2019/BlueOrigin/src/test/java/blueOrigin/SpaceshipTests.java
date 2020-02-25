package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship
    private Astronaut astronaut;
    private Spaceship spaceship;

    @Before
    public void setUp(){
        this.spaceship = new Spaceship("Huston", 10);
    }

    @Test
    public void getCount_ReturnsCorrectResult(){
        astronaut = new Astronaut("Vankata", 10);
        this.spaceship.add(astronaut);

        Assert.assertEquals(1, spaceship.getCount());
    }

    @Test
    public void getName_ReturnsCorrectResult(){

        Assert.assertEquals("Huston", spaceship.getName());
    }

    @Test
    public void getCapacity_ReturnsCorrectResult(){

        Assert.assertEquals(10, spaceship.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAstronaut_ThrowsWithFullCapacity(){
        astronaut = new Astronaut("Vankata", 10);
        Spaceship spaceship2 = new Spaceship("Sofia", 1);
        spaceship2.add(astronaut);
        spaceship2.add(astronaut);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addAstronaut_ThrowsWithExcistingAstronaut(){
        astronaut = new Astronaut("Vankata", 10);
        spaceship.add(astronaut);
        spaceship.add(astronaut);

    }

    @Test
    public void addAstronaut_SuccessfullyAddsAstronaut(){
        astronaut = new Astronaut("Vankata", 10);

        spaceship.add(astronaut);

    }

    @Test
    public void remove_removesSuccessfulyAstronaut(){
        astronaut = new Astronaut("Vankata", 10);
        spaceship.add(astronaut);

        Assert.assertTrue(spaceship.remove("Vankata"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacity_ThrowsWithInvalidCapacity(){
        astronaut = new Astronaut("Vankata", 10);

        Spaceship spaceship2 = new Spaceship("One", -1);

    }

    @Test(expected = NullPointerException.class)
    public void setName_ThrowsWithEmptyName(){

        Spaceship spaceship2 = new Spaceship("    ", 10);

    }

    @Test(expected = NullPointerException.class)
    public void setName_ThrowsWithNullName(){

        Spaceship spaceship2 = new Spaceship(null, 10);

    }



}
