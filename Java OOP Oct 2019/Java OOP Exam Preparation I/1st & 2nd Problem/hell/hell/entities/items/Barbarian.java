package hell.entities.items;

import hell.entities.HeroImpl;

public class Barbarian extends HeroImpl {

    private static final int INITIAL_STRENGTH = 90;
    private static final int INITIAL_AGILITY = 25;
    private static final int INITIAL_INTELLIGENCE = 10;
    private static final int INITIAL_HIT_POINTS = 350;
    private static final int INITIAL_DAMAGE = 150;


    public Barbarian(String name) {
        super(name, INITIAL_STRENGTH, INITIAL_AGILITY, INITIAL_INTELLIGENCE, INITIAL_HIT_POINTS, INITIAL_DAMAGE);
    }
}
