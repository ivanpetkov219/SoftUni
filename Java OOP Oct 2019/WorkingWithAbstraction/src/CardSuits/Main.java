package CardSuits;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        StringBuilder sb = new StringBuilder();

        sb.append("Card Ranks:").append(System.lineSeparator());

        CardSuits[] values = CardSuits.values();

        for (CardSuits value : values) {

        sb.append(String.format("Ordinal value: %d; Name value: %s", value.getIndex(), value.getName() )).append(System.lineSeparator());
        }


        System.out.println(sb.toString());
    }
}
