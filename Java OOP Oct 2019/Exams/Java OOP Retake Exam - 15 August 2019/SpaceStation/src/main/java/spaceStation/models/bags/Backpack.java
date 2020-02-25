package spaceStation.models.bags;

import java.util.ArrayList;
import java.util.List;

public abstract class Backpack implements spaceStation.models.bags.Bag {

    private List<Backpack> items;


    public Backpack(List<Backpack> items) {
        this.items = new ArrayList<>();
    }

}
