import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        getMiddleChar(input);
    }

    static void getMiddleChar(String word) {
        int middleCharIndex1 = -1;
        int middleCharIndex2 = -1;
        if (word.length() % 2 == 0) {
            middleCharIndex1 = word.length() / 2 - 1;
            middleCharIndex2 = word.length() / 2;
            System.out.print(word.charAt(middleCharIndex1));
            System.out.print(word.charAt(middleCharIndex2));
        } else {
            middleCharIndex1 = word.length() / 2;
            System.out.println(word.charAt(middleCharIndex1));
        }
    }
}
