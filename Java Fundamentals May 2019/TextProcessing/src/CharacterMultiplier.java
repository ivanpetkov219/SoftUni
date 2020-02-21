import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strings = scanner.nextLine().split("\\s+");
        String first = strings[0];
        String second = strings[1];

        int totalSum = 0;

        for (int i = 0; i < first.length() && i < second.length(); i++) {
            totalSum += first.charAt(i) * second.charAt(i);

        }

        if(first.length() > second.length()){
            for (int i = second.length(); i < first.length() ; i++) {
                totalSum += first.charAt(i);
            }
        }else {
            for (int i = first.length(); i < second.length() ; i++) {
                totalSum += second.charAt(i);
            }
        }
        System.out.println(totalSum);
    }
}
