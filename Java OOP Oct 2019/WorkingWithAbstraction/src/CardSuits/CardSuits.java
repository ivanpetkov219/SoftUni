package CardSuits;

public enum CardSuits {

    ACE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    NINE(8),
    TEN(9),
    JACK(10),
    QUEEN(11),
    KING(12);

    private final int index;

    CardSuits (int index){
        this.index = index;
    }

    public int getIndex() {

        return this.ordinal();
    }

    public String getName() {

        return this.name();
    }

}
