import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder result = new StringBuilder();
        char temp = Character.MIN_VALUE;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar != temp) {
                System.out.print(currentChar);
                temp = currentChar;
            }
        }
    }
}
