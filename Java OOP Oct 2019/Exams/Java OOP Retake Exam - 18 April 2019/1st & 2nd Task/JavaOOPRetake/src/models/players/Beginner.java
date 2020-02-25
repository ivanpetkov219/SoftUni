package models.players;

import repositories.interfaces.CardRepository;

public class Beginner extends BasePlayer {
    private static final int INITIAL_HEALTH_POINTS = 50;

    public Beginner(CardRepository cardRepository, String username) {
        super(cardRepository, username, INITIAL_HEALTH_POINTS);
    }
}
