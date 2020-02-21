import java.util.Scanner;

public class TopIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split(" ");
        int[] arrayNumbers = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            arrayNumbers[i] = Integer.parseInt(array[i]);
        }

        for (int j = 0; j < arrayNumbers.length; j++) {

            int element = arrayNumbers[j];
            boolean isTopInteger = true;

            for (int i = j + 1; i < arrayNumbers.length; i++) {

                if (element <= arrayNumbers[i]) {
                    isTopInteger = false;
                    break;
                }
            }
            if (isTopInteger) {
                System.out.print(element + " ");
            }
        }

    }
}
