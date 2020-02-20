import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter start salary:");
        double salary = scanner.nextDouble();
        System.out.println("Enter years of work:");
        double yearsOfWork = scanner.nextDouble();


        for (int currentYear = 0; currentYear < yearsOfWork ; currentYear++) {
            salary *= 1.08;

        }

        System.out.printf("Your salary after working %.0f years is %.2f leva", yearsOfWork, salary);
    }
}
