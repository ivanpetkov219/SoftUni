package birthdaycelebrations;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Citizen> citizenMap = new HashMap<>();
        Map<String, Rebel> rebelMap = new HashMap<>();


        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {

            String[] inputData = scanner.nextLine().split("\\s+");
            String name = inputData[0];
            int age = Integer.parseInt(inputData[1]);

            if (inputData.length == 4) {

                Citizen citizen = new Citizen(name, age, inputData[2], inputData[3]);

                citizenMap.put(name, citizen);

            } else if (inputData.length == 3) {

                Rebel rebel = new Rebel(name, age, inputData[2]);

                rebelMap.put(name, rebel);

            }
        }
        String input = scanner.nextLine();

        while (!input.equals("End")) {

            if (citizenMap.containsKey(input)) {
                citizenMap.get(input).buyFood();

            } else if (rebelMap.containsKey(input)) {
                rebelMap.get(input).buyFood();
            }
            input = scanner.nextLine();
        }

        int totalFood = 0;

        for (Map.Entry<String, Citizen> citizenEntry : citizenMap.entrySet()) {
            totalFood += citizenEntry.getValue().getFood();
        }
        for (Map.Entry<String, Rebel> rebelEntry : rebelMap.entrySet()) {
            totalFood += rebelEntry.getValue().getFood();
        }

        System.out.println(totalFood);
    }

}
