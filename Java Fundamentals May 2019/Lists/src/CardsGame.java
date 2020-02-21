import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> playerOne = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> playerTwo = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        while (playerOne.size() != 0 && playerTwo.size() != 0){
            int firstCardPlayerOne = playerOne.get(0);
            int firstCardPlayerTwo = playerTwo.get(0);

            if (firstCardPlayerOne > firstCardPlayerTwo){
                addWinnigBit(playerOne, playerTwo, firstCardPlayerOne, firstCardPlayerTwo);
            }else if (firstCardPlayerTwo > firstCardPlayerOne){
                addWinnigBit(playerTwo, playerOne, firstCardPlayerTwo, firstCardPlayerOne);
            }else {
                playerOne.remove(0);
                playerTwo.remove(0);
            }
        }
        int sum = 0;

        if (playerOne.size() > 0){
            for (int i = 0; i < playerOne.size() ; i++) {
                sum += playerOne.get(i);
            }
            System.out.printf("First player wins! Sum: %d", sum);
        }else if (playerTwo.size() > 0){
            for (int i = 0; i < playerTwo.size() ; i++) {
                sum += playerTwo.get(i);
            }
            System.out.printf("Second player wins! Sum: %d", sum);
        }

    }
    static void addWinnigBit (List<Integer> list1, List<Integer> list2, int winningCard, int losingCard){
        list1.add(winningCard);
        list1.add(losingCard);
        list1.remove(0);
        list2.remove(0);
    }
}
