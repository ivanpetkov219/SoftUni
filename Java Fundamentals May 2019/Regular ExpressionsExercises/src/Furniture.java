import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String regex = "[>]{2}([A-Z]+[a-z]*)[<]{2}(\\d+\\.?\\d+)[!](\\d+)";
        double totalMoneySpent = 0;
        List<String> furniture = new ArrayList<>();

        while (!input.equals("Purchase")) {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String currentFurniture = matcher.group(1);
                double price = Double.parseDouble(matcher.group(2));
                int quantity = Integer.parseInt(matcher.group(3));

                totalMoneySpent += price * quantity;
                furniture.add(currentFurniture);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bought furniture:");

        for (String entry : furniture) {
            System.out.println(entry);
        }
        System.out.printf("Total money spend: %.2f", totalMoneySpent);

    }
}
