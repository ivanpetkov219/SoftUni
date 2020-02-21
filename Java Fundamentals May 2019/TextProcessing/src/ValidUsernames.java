import java.util.Scanner;

public class ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] usernames = scanner.nextLine().split(", ");
        boolean isValidUsername = true;

        for (String username : usernames) {
            if (validateUsername(username)) {
                System.out.println(username);
            }
        }
    }
    private static boolean validateUsername(String username) {
        if (username.length() < 3 || username.length() > 16) {
            return false;
        }

        for (int i = 0; i < username.length(); i++) {
            char currentChar = username.charAt(i);
            if (!Character.isLetterOrDigit(currentChar) && currentChar != '-' && currentChar != '_') {
                return false;
            }
        }
        return true;
    }
}



