import java.util.*;

public class MakeASalad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] vegetablesArr = scanner.nextLine().split("\\s+");
        int[] caloriesArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayDeque<String> queueVegetables = new ArrayDeque<>();
        ArrayDeque<Integer> stackCalories = new ArrayDeque<>();
        List<Integer> madeSalads = new LinkedList<>();

        for (String vegetable : vegetablesArr) {
            queueVegetables.offer(vegetable);
        }
        for (int calorie : caloriesArr) {
            stackCalories.push(calorie);
        }
        Map<String, Integer> vegetablesAndTheirCalories = new HashMap<>();

        vegetablesAndTheirCalories.put("tomato", 80);
        vegetablesAndTheirCalories.put("carrot", 136);
        vegetablesAndTheirCalories.put("lettuce", 109);
        vegetablesAndTheirCalories.put("potato", 215);

        while (!queueVegetables.isEmpty() && !stackCalories.isEmpty()) {

            int saladCalories = stackCalories.pop();
            madeSalads.add(saladCalories);

            while (saladCalories > 0 && !queueVegetables.isEmpty()) {

                String currentVegie = queueVegetables.poll();
                int currentVegieCalories = vegetablesAndTheirCalories.get(currentVegie);

                saladCalories -= currentVegieCalories;
            }
        }
        if (madeSalads.size() != 0) {
            for (Integer madeSalad : madeSalads) {
                System.out.print(madeSalad + " ");
            }
        }
        System.out.println();

        if (queueVegetables.isEmpty()) {
            while (!stackCalories.isEmpty()) {
                System.out.print(stackCalories.pop() + " ");
            }
        }
        if (stackCalories.isEmpty()) {
            while (!queueVegetables.isEmpty()) {
                System.out.print(queueVegetables.poll() + " ");
            }
        }

    }

}
