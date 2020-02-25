import java.util.*;
import java.util.stream.Collectors;

public class HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Set<String>> playerAndCards = new LinkedHashMap<>();


        String input = scanner.nextLine();

        while (!input.equals("JOKER")){

            String[] nameWithCards = input.split(": ");
            String player = nameWithCards[0];
            Set<String> cards = Arrays.stream(nameWithCards[1].split(", ")).collect(Collectors.toSet());

            playerAndCards.putIfAbsent(player, new HashSet<>());

            playerAndCards.get(player).addAll(cards);




            input = scanner.nextLine();
        }

    }
}
