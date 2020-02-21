import java.util.Scanner;

public class ZigZagArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lines = Integer.parseInt(scanner.nextLine());

        int[] firstArray = new int[lines];
        int[] secondArray = new int[lines];

        for (int i = 0; i < lines; i++) {
            int firstNumber = scanner.nextInt();
            int secondNumber = scanner.nextInt();

            if (i % 2 == 0) {
                firstArray[i] = firstNumber;
                secondArray[i] = secondNumber;
            } else {
                firstArray[i] = secondNumber;
                secondArray[i] = firstNumber;
            }

        }

        for (int i = 0; i < firstArray.length; i++) {

            System.out.print(firstArray[i] + " ");

        }
        System.out.println();
        for (int i = 0; i < secondArray.length; i++) {

            System.out.print(secondArray[i] + " ");
        }
    }
}
