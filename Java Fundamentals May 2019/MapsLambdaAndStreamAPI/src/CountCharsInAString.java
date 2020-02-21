import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountCharsInAString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        LinkedHashMap<Character, Integer> repeats = new LinkedHashMap<>();

        for (int i = 0; i < input.length() ; i++) {
            char currentChar = input.charAt(i);
            if(currentChar != ' '){
                if (!repeats.containsKey(currentChar)){
                    repeats.put(currentChar, 1);
                }else {
                    int currentCharRepeats = repeats.get(currentChar);
                    repeats.put(currentChar, currentCharRepeats + 1);
                }
            }
        }

        for (Map.Entry<Character, Integer> symbol : repeats.entrySet()) {
            System.out.println(String.format("%c -> %d", symbol.getKey(), symbol.getValue()));
        }
    }
}
