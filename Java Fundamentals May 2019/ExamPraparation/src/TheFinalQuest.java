import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TheFinalQuest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> words = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("Stop")){
            String[] command = input.split("\\s+");

            String action = command[0];

            if (action.equals("Delete")){
                int index = Integer.parseInt(command[1]);

                deleteWordAfterIndex(words, index);

            }else if (action.equals("Swap")){
                String word1 = command[1];
                String word2 = command[2];

                swapWords(words, word1, word2);

            }else if (action.equals("Put")){
                String word = command[1];
                int index = Integer.parseInt(command[2]);

                putWord(words, word, index);

            }else if(action.equals("Sort")){

                Collections.sort(words);
                Collections.reverse(words);

            }else if(action.equals("Replace")){

                String word1 = command[1];
                String word2 = command[2];

                replaceWords(words, word1, word2);
            }
            input = scanner.nextLine();
        }
        for (String word: words) {
            System.out.print(word + " ");
        }
    }
    private static void replaceWords(List<String> words, String word1, String word2) {

        int indexWord2 = words.indexOf(word2);

        if(isValidIndex(words, indexWord2)){
            words.set(indexWord2, word1);
        }
    }
    private static void putWord(List<String> words, String word, int index) {

        if(isValidIndex(words, index - 1)){                //тук може да гръмне заради последния индекс

            words.add(index -1, word);
        }
        if(index == words.size() + 1){
            words.add(word);
        }
    }
    private static void swapWords(List<String> words, String word1, String word2) {

        int indexWord1 = words.indexOf(word1);
        int indexWord2 = words.indexOf(word2);

        if(isValidIndex(words, indexWord1) && isValidIndex(words, indexWord2)){
            words.set(indexWord1, word2);
            words.set(indexWord2, word1);
        }
    }
    private static boolean isValidIndex(List<String> words, int index) {
        boolean isValidIndex = false;
        if(index >= 0 && index < words.size()){
            isValidIndex = true;
        }
        return isValidIndex;
    }
    private static void deleteWordAfterIndex(List<String> words, int index) {
        if(isValidIndex(words, index + 1)){
            words.remove(index + 1);
        }
    }
}
