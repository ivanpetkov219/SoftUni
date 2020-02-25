import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Character, Integer> occurences = new TreeMap<>();

        String input = scanner.nextLine();

        for (int i = 0; i < input.length(); i++) {

            char currentChar = input.charAt(i);

            occurences.putIfAbsent(currentChar, 0);
            occurences.put(currentChar, occurences.get(currentChar) + 1);
        }

        for (Character occurency : occurences.keySet()) {
            System.out.printf("%c: %d time/s%n", occurency, occurences.get(occurency));
        }


    }
}
