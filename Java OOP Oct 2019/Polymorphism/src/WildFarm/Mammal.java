package WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;


    public Mammal(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    @Override
    protected void eat(Food food){
        String animalResult = this.getClass().getSimpleName().equals("Mouse") ? "Mice" : "Zebras";
        if (!(food instanceof Vegetable)){
            System.out.println(String.format("%s are not eating that type of food!", animalResult));
        }else {
            super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
        }
    }

    protected String getLivingRegion(){
        return this.livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %d]", this.getAnimalType(), this.getAnimalName(), formatter.format(this.getAnimalWeight()), this.getLivingRegion(), this.getFoodEaten());
    }
}
