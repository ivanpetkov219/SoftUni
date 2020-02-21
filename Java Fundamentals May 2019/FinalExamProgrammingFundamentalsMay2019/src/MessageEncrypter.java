import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageEncrypter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lines = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= lines ; i++) {
            String input = scanner.nextLine();

            String regex = "([*@])([A-Z][a-z]{3,})\\1: \\[([A-Za-z])]\\|\\[([A-Za-z])\\]\\|\\[([A-Za-z])\\]\\|$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if(!matcher.find()){
                System.out.println("Valid message not found!");
            }else {
                    String tag = matcher.group(2);
                    int firstChar = matcher.group(3).charAt(0);
                    int secondChar = matcher.group(4).charAt(0);
                    int thirdChar = matcher.group(5).charAt(0);

                    System.out.printf("%s: %d %d %d%n", tag, firstChar, secondChar, thirdChar);
            }
        }
    }
}
