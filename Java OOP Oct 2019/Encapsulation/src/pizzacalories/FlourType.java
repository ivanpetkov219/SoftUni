package pizzacalories;

public enum FlourType {
    White(1.5),
    Wholegrain(1.0);

    double modifier;

    FlourType(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return this.modifier;
    }
}
