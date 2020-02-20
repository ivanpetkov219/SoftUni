import java.util.Scanner;

public class Travelling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String destionation = input;
            double sum = 0;
            double moneyNeeded = Double.parseDouble(scanner.nextLine());
            while (sum < moneyNeeded) {
                double moneySaved = Double.parseDouble(scanner.nextLine());
                sum += moneySaved;
            }

            System.out.printf("Going to %s!%n", destionation);


            input = scanner.nextLine();


        }

    }


}

