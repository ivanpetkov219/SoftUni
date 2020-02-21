import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        StringBuilder encryptedInput = new StringBuilder();

        for (int i = 0; i < input.length() ; i++) {
            char encryptedChar = (char)(input.charAt(i) + 3);
            encryptedInput.append(encryptedChar);
        }

        System.out.println(encryptedInput);
    }
}
