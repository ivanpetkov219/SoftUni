import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EasterGifts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> gifts = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("No Money")) {
            String[] command = input.split("\\s+");
            String action = command[0];

            if (action.equals("OutOfStock")) {
                String giftName = command[1];
                for (int i = 0; i < gifts.size(); i++) {
                    if (gifts.get(i).equals(giftName)) {
                        gifts.set(i, "None");
                    }
                }

            } else if (action.equals("Required")) {
                String gift = command[1];
                int index = Integer.parseInt(command[2]);

                if (index >= 0 && index < gifts.size()) {
                    gifts.set(index, gift);
                }


            } else if (action.equals("JustInCase")) {

                String gift = command[1];
                gifts.set(gifts.size() - 1, gift);
            }


            input = scanner.nextLine();
        }

        for (String index : gifts) {
            if (!index.equals("None")) {
                System.out.print(index + " ");
            }
        }
    }
}
