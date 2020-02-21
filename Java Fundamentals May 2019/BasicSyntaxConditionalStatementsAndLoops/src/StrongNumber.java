import java.util.Scanner;

public class StrongNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String numberInString = "" + number;

        int factSum = 0;
        for (int i = 0; i < numberInString.length(); i++) {

            int currentDigit = numberInString.charAt(i) - '0';
            int factorial = 1;
            for (int j = 2; j <= currentDigit; j++) {

                factorial = factorial * j;


            }
            factSum += factorial;
        }

        if (factSum == number) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}