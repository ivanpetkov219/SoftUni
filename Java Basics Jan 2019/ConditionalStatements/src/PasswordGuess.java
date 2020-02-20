import java.util.Scanner;

public class PasswordGuess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String enteredPassword = scanner.nextLine();
    String actualPassword = "s3cr3t!P@ssw0rd";

    if (enteredPassword.equals(actualPassword)){
        System.out.println("Welcome");
    }else
        System.out.println("Wrong password!");

    }
}
