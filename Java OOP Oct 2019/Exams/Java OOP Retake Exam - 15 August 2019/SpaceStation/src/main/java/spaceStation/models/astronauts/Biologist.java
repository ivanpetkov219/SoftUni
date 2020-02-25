package models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final int INITIAL_OXYGEN_UNITS = 70;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN_UNITS);
    }


}
