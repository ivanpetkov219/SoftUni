import java.util.Scanner;

public class FoodOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budjet = Double.parseDouble(scanner.nextLine());
        double totalPrice = 2.50;
        int counter = 0;

        String input = scanner.nextLine();

        while (!"Order".equals(input)){
            double currentPrice = Double.parseDouble(scanner.nextLine());
            totalPrice += currentPrice;
            counter++;

            if(totalPrice > budjet){
                System.out.println("You will exceed the budget if you order this!");
                totalPrice -= currentPrice;
                counter--;
            }
            input = scanner.nextLine();
        }

        if (totalPrice <= budjet){
            System.out.printf("You ordered %d items!%n", counter);
            System.out.printf("Total: %.2f", totalPrice);
        }
    }
}
