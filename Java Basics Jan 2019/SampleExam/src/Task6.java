import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        String numberInString = "" + number;

        int firstDigit = Integer.parseInt("" + numberInString.charAt(0));
        int secondDigit = Integer.parseInt("" + numberInString.charAt(1));
        int thirdDigit = Integer.parseInt("" + numberInString.charAt(2));

        for (int i = 1; i <= thirdDigit; i++) {
            for (int j = 1; j <= secondDigit; j++) {
                for (int k = 1; k <= firstDigit; k++) {

                    int multiplication = i * j * k;

                    System.out.printf("%d * %d * %d = %d;", i, j, k, multiplication);
                    System.out.println();

                }

            }

        }

    }


}
