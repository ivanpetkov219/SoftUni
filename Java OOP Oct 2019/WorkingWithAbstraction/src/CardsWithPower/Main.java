package CardsWithPower;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rank = scanner.nextLine();
        String suit = scanner.nextLine();


        int sum = Card.valueOf(rank).getIndex() + Card.valueOf(suit).getIndex();

        System.out.println(String.format("Card name: %s of %s; Card power: %d", rank, suit, sum));



    }
}
