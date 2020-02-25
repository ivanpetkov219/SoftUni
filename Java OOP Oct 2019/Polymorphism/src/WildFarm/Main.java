package WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();
        List<Food> foodList = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")){
            String[] animalTokens = input.split("\\s+");
            String[] foodTokens = scanner.nextLine().split("\\s+");

            String animalType = animalTokens[0];
            String animalName = animalTokens[1];
            double animalWeight = Double.parseDouble(animalTokens[2]);
            String animalLivingRegion = animalTokens[3];

            switch (animalType){
                case "Mouse":
                    Mouse mouse = new Mouse(animalName, animalType, animalWeight,animalLivingRegion);
                    animals.add(mouse);
                    break;
                case "Zebra":
                    Zebra zebra = new Zebra(animalName, animalType, animalWeight,animalLivingRegion);
                    animals.add(zebra);
                    break;
                case "Tiger":
                    Tiger tiger = new Tiger(animalName, animalType, animalWeight,animalLivingRegion);
                    animals.add(tiger);
                    break;
                case "Cat":
                    String breed = animalTokens[4];
                    Cat cat = new Cat(animalName, animalType, animalWeight,animalLivingRegion, breed);
                    animals.add(cat);
                    break;
            }

            String foodType = foodTokens[0];
            int foodQuantity = Integer.parseInt(foodTokens[1]);

            if (foodType.equals("Meat")){
                Meat meat = new Meat(foodQuantity);
                foodList.add(meat);
            }else {
                Vegetable vegetable = new Vegetable(foodQuantity);
                foodList.add(vegetable);
            }
            input = scanner.nextLine();
        }

        for (Animal animal : animals) {
            animal.makeSound();
            animal.eat(foodList.get(0));
            System.out.println(animal);
        }
    }
}
