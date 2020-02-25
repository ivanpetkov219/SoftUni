package hell.entities.items;

import hell.entities.items.ItemImpl;

public class CommonItem extends ItemImpl {

    public CommonItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
    }
}
