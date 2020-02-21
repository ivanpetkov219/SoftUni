import java.awt.*;
import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String password = scanner.nextLine();

        if (!lenghtCheck(password)) {
            System.out.println("Password must be between 6 and 10 characters");
        }

        if (!letterAndDigitsOnly(password)) {
            System.out.println("Password must consist only of letters and digits");
        }

        if (!countDigits(password)) {
            System.out.println("Password must have at least 2 digits");
        }

        if (lenghtCheck(password) && letterAndDigitsOnly(password) && countDigits(password)) {
            System.out.println("Password is valid");
        }

    }

    static boolean lenghtCheck(String word) {

        boolean correctLenght = true;

        if (word.length() < 6 || word.length() > 10) {
            correctLenght = false;
        }
        return correctLenght;
    }

    static boolean letterAndDigitsOnly(String word) {
        boolean isLetterOrDigit = true;

        for (int i = 0; i < word.length(); i++) {

            isLetterOrDigit = Character.isLetterOrDigit(word.charAt(i));
            if (!isLetterOrDigit) {
                break;
            }
        }

        return isLetterOrDigit;

    }

    static boolean countDigits(String word) {
        int count = 0;
        boolean enoughDigits = false;

        for (int i = 0; i < word.length(); i++) {

            if (Character.isDigit(word.charAt(i))) {
                count++;
            }
            if (count > 1) {
                break;
            }

        }
        if (count > 1) {
            enoughDigits = true;
        }

        return enoughDigits;

    }
}
