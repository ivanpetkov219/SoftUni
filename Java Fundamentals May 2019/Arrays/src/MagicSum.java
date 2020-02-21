import java.util.Scanner;

public class MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split(" ");
        int givenNumber = Integer.parseInt(scanner.nextLine());

        int[] numbers = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {

                if (numbers[i] + numbers[j] == givenNumber) {
                    System.out.printf("%d %d%n", numbers[i], numbers[j]);
                }

            }

        }
    }
}
