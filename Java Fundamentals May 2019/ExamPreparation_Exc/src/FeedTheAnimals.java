import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class FeedTheAnimals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> animalsByFood = new TreeMap<>();
        Map<String, Integer> areasCount = new HashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("Last Info")) {

            String[] commands = input.split(":");
            String action = commands[0];
            String animal = commands[1];
            int food = Integer.parseInt(commands[2]);
            String area = commands[3];

            if (action.equals("Add")) {

                if (!animalsByFood.containsKey(animal)) {
                    animalsByFood.put(animal, food);
                    if(!areasCount.containsKey(area)) {
                        areasCount.put(area, 1);
                    }else {
                        areasCount.put(area, areasCount.get(area) + 1);
                    }
                } else {
                    int updatedFood = animalsByFood.get(animal) + food;

                    int updatedAreaCount = areasCount.get(area) + 1;
                    animalsByFood.put(animal, updatedFood);
                    areasCount.put(area, updatedAreaCount);
                }
            } else if (action.equals("Feed")) {

                if (animalsByFood.containsKey(animal)) {
                    int foodAfterFeeding = animalsByFood.get(animal) - food;
                    if (foodAfterFeeding > 0) {
                        animalsByFood.put(animal, foodAfterFeeding);
                    } else {
                        System.out.println(animal + " was successfully fed");
                        animalsByFood.remove(animal);
                        int updateAreaCount = areasCount.get(area) - 1;
                        if (updateAreaCount != 0) {
                            areasCount.put(area, updateAreaCount);
                        } else {
                            areasCount.remove(area);
                        }
                    }
                }
            }
            input = scanner.nextLine();
        }

        //TODO Print hungry animals

        if (!animalsByFood.isEmpty()) {
            System.out.println("Animals:");
            animalsByFood.entrySet().stream().sorted((first, second) -> {
                int result = second.getValue().compareTo(first.getValue());

                if (result == 0) {
                    result = first.getKey().compareTo(second.getKey());
                }
                return result;

            }).forEach(entry -> {
                System.out.println(entry.getKey() + " -> " + entry.getValue() + "g");
            });
        }


        if (!areasCount.isEmpty()) {
            System.out.println("Areas with hungry animals:");
            areasCount.entrySet().stream().sorted((first, second) -> {
                int result = second.getValue().compareTo(first.getValue());

                if (result == 0) {
                    result = first.getKey().compareTo(second.getKey());
                }
                return result;

            }).forEach(entry -> {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            });
        }
    }
}
