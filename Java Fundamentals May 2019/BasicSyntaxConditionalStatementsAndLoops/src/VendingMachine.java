import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String coinsInserted = scanner.nextLine();

        double totalMoneyInserted = 0;

        while (!"Start".equals(coinsInserted)) {
            double currentCoin = Double.parseDouble(coinsInserted);

            if (currentCoin == 0.1 || currentCoin == 0.2 || currentCoin == 0.5 || currentCoin == 1 || currentCoin == 2) {
                totalMoneyInserted += currentCoin;
            } else {
                System.out.printf("Cannot accept %.2f%n", currentCoin);
            }
            coinsInserted = scanner.nextLine();
        }

        String chosenProduct = scanner.nextLine();
        double productPrice = 0;
        boolean availableProduct = true;


        while (!"End".equals(chosenProduct)) {
            switch (chosenProduct) {
                case "Nuts":
                    productPrice = 2.0;
                    break;
                case "Water":
                    productPrice = 0.7;
                    break;
                case "Crisps":
                    productPrice = 1.5;
                    break;
                case "Soda":
                    productPrice = 0.8;
                    break;
                case "Coke":
                    productPrice = 1.0;
                    break;
                default:
                    System.out.println("Invalid product");
                    availableProduct = false;
                    break;
            }

            if (productPrice <= totalMoneyInserted && availableProduct) {
                System.out.printf("Purchased %s%n", chosenProduct);
                totalMoneyInserted -= productPrice;
            } else if (productPrice > totalMoneyInserted && availableProduct){
                System.out.println("Sorry, not enough money");
            }

            chosenProduct = scanner.nextLine();
        }

        System.out.printf("Change: %.2f", totalMoneyInserted);
    }
}
