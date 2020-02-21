import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArray = scanner.nextLine().split(" ");

        int[] arrayNumbers = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            arrayNumbers[i] = Integer.parseInt(inputArray[i]);
        }

        int rotations = Integer.parseInt(scanner.nextLine());

        rotations = rotations % arrayNumbers.length;

        for (int j = 0; j < rotations; j++) {


            int firstNumber = arrayNumbers[0];

            for (int i = 0; i < arrayNumbers.length; i++) {
                if (i < arrayNumbers.length - 1) {
                    arrayNumbers[i] = arrayNumbers[i + 1];
                }
            }
            arrayNumbers[arrayNumbers.length - 1] = firstNumber;
        }

        for (int i = 0; i < arrayNumbers.length ; i++) {

            System.out.print(arrayNumbers[i] + " ");

        }
    }

}
