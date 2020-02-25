package pizzacalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaData = scanner.nextLine().split("\\s+");
        String[] doughData = scanner.nextLine().split("\\s+");
        Pizza pizza = new Pizza();
        try {
            pizza = new Pizza(pizzaData[1], Integer.parseInt(pizzaData[2]));
            Dough dough = new Dough(doughData[1], doughData[2], Double.parseDouble(doughData[3]));
            pizza.setDough(dough);
            String command = scanner.nextLine();

            while (!"END".equals(command)) {
                String[] data = command.split("\\s+");
                Topping topping = new Topping(data[1], Double.parseDouble(data[2]));
                pizza.addTopping(topping);
                command = scanner.nextLine();
            }
            System.out.println(pizza.toString());
        } catch (IllegalArgumentException message) {
            System.out.println(message.getMessage());
        }

    }

}
