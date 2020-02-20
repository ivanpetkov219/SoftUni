import java.util.Scanner;

public class Coding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String numberInString = number + "";
        int numberLenght = numberInString.length();

        for (int i = 1; i <= numberLenght; i++) {

            int lastDigit = number % 10;

            if (lastDigit != 0) {

                for (int j = 1; j <= lastDigit; j++) {
                    System.out.print((char)(lastDigit + 33));

                }
            } else
                System.out.print("ZERO");

            number /= 10;
            System.out.println();

        }

    }
}
