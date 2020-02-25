package aquarium.models.fish;

public class SaltwaterFish extends BaseFish {
    private int size;
    private static final int INITIAL_SIZE = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.size = INITIAL_SIZE;
    }

    @Override
    public void eat() {
       this.size += 2;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
