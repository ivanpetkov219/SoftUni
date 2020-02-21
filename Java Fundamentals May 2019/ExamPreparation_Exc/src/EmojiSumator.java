import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiSumator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        int[] numbers = Arrays.stream(scanner.nextLine().split(":")).mapToInt(Integer::parseInt).toArray();

        String inputEmoji = "";

        for (int number : numbers) {
            inputEmoji += (char) number;
        }

        List<String> validEmojis = new ArrayList<>();

        String regex = "\\s:([a-z]{4,}):(?=[ ,.!?])";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int totalEmojiSum = 0;

        while (matcher.find()) {

            int currentEmojiSum = 0;

            String foundEmoji = matcher.group(1);

            validEmojis.add(foundEmoji);

            for (int i = 0; i < foundEmoji.length(); i++) {
                currentEmojiSum += foundEmoji.charAt(i);
            }
            totalEmojiSum += currentEmojiSum;
        }
        if (validEmojis.contains(inputEmoji)) {
            totalEmojiSum *= 2;
        }
        if (!validEmojis.isEmpty()) {
            System.out.print("Emojis found: ");
            for (int i = 0; i < validEmojis.size(); i++) {

                if (i != validEmojis.size() - 1) {
                    System.out.print(":" + validEmojis.get(i) + ":, ");
                } else {
                    System.out.println(":" + validEmojis.get(i) + ":");
                }
            }
        }
        System.out.println("Total Emoji Power: " + totalEmojiSum);
    }
}
