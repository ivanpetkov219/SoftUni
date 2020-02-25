package shoppingspree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] peopleWithMoneyInput = scanner.nextLine().split(";");
        String[] productsWithCostInput = scanner.nextLine().split(";");

        Map<String, Person> personMap = new LinkedHashMap<>();
        Map<String, Product> productMap = new LinkedHashMap<>();

        for (int i = 0; i < peopleWithMoneyInput.length; i++) {
            String[] personAndMoney = peopleWithMoneyInput[i].split("=");
            String name = personAndMoney[0];
            double money = Double.parseDouble(personAndMoney[1]);
            try {
                Person person = new Person(name, money);

                personMap.put(name, person);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
        for (int i = 0; i < productsWithCostInput.length; i++) {
            String[] productAndCost = productsWithCostInput[i].split("=");
            String name = productAndCost[0];
            double cost = Double.parseDouble(productAndCost[1]);
            try {
                Product product = new Product(name, cost);
                productMap.put(name, product);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String[] tokens = input.split("\\s+");
            String personName = tokens[0];
            String productName = tokens[1];

            if (!personMap.isEmpty()) {
                personMap.
                        get(personName).
                        buyProduct(productMap.get(productName));
            }
            input = scanner.nextLine();
        }
        for (Person person : personMap.values()) {
            System.out.println(person);
        }
    }
}
