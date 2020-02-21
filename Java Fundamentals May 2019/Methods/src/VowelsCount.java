import java.util.Currency;
import java.util.Scanner;

public class VowelsCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        int result = getVowelCount(word);

        System.out.println(result);

    }

    static int getVowelCount(String input) {
        int count = 0;
        input = input.toLowerCase();

        for (int i = 0; i < input.length() ; i++) {
            char currentChar = input.charAt(i);


            if (currentChar == 'a' || currentChar == 'o' || currentChar == 'u' ||
                    currentChar == 'e' || currentChar == 'i' || currentChar == 'y') {
                count++;
            }
        }

        return count;

    }
}
