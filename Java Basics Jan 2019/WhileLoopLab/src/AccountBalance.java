import java.util.Scanner;

public class AccountBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberTransactions = Integer.parseInt(scanner.nextLine());
        double total = 0;

        while (numberTransactions-- > 0){
            double transaction = Double.parseDouble(scanner.nextLine());

            if (transaction < 0){
                System.out.println("Invalid operation!");
                break;
            }

            System.out.printf("Increase: %.2f%n",transaction);

            total += transaction;
        }
        System.out.printf("Total: %.2f", total);
    }
}
