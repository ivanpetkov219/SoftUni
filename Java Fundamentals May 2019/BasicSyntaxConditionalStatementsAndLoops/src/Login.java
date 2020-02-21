import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();
        String password = "";
        int countWrongPass = 0;

        for (int i = username.length() - 1; i >= 0; i--) {
            char currentChar = username.charAt(i);
            password += currentChar;
        }

        String input = scanner.nextLine();

        while (!input.equals(password)) {
            countWrongPass++;
            if (countWrongPass == 4) {
                break;
            }

            System.out.println("Incorrect password. Try again.");

            input = scanner.nextLine();
        }

        if (input.equals(password)) {

            System.out.printf("User %s logged in.", username);
        } else {
            System.out.printf("User %s blocked!", username);
        }


    }
}
