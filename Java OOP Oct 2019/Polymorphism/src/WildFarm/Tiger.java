package WildFarm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("ROAAR!!!");
    }

    protected void eat(Food food){
        if(!(food instanceof Meat)){
            System.out.println(String.format("%ss are not eating that type of food!", this.getClass().getSimpleName()));
        }else {
            super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
        }
    }
}
