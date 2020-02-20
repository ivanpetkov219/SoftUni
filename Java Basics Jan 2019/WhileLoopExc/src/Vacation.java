import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double vacationPrice = Double.parseDouble(scanner.nextLine());
        double availableMoney = Double.parseDouble(scanner.nextLine());
        int spendDays = 0;
        int days = 0;

        String action = scanner.nextLine();
        while (availableMoney < vacationPrice) {
            double sum = Double.parseDouble(scanner.nextLine());


            if ("save".equals(action)) {
                availableMoney += sum;
                spendDays = 0;
            } else if ("spend".equals(action)) {
                if (sum > availableMoney) {
                    sum = availableMoney;
                }
                availableMoney -= sum;
                spendDays++;
            }

            if (spendDays == 5) {
                System.out.println("You can't save the money.");
                System.out.println(days + 1);
                break;
            }

            days++;
            if (availableMoney >= vacationPrice) {
                System.out.printf("You saved the money for %d days.", days);
                break;
            }
            action = scanner.nextLine();


        }
    }
}
