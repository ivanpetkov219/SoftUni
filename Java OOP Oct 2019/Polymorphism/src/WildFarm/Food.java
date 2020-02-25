package WildFarm;

public abstract class Food {
    private int quantity;


    public Food(int quantity) {
        this.quantity = quantity;
    }

    protected int getQuantity() {
        return this.quantity;
    }
}
