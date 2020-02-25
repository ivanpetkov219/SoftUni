package hell.entities.items;

import hell.interfaces.Recipe;

import java.util.List;

public class RecipeItem extends ItemImpl implements Recipe {
    private List<String> recipe;


    protected RecipeItem(String name,
                         int strengthBonus,
                         int agilityBonus,
                         int intelligenceBonus,
                         int hitPointsBonus,
                         int damageBonus,
                         List<String> recipe) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.recipe = recipe;
    }

    @Override
    public List<String> getRequiredItems() {
        return this.recipe;
    }
}
