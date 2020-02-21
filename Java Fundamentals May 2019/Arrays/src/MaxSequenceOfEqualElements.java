import java.util.Scanner;

public class MaxSequenceOfEqualElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split(" ");
        int[] numbers = new int[array.length];

        for (int i = 0; i < array.length ; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }


        int maxCounter = 0;
        int currentCounter = 1;
        int repeatedDigit = 0;

        for (int i = 0; i < numbers.length; i++) {
            if(i != numbers.length - 1 && numbers[i] != numbers[i + 1]){
                currentCounter = 1;
                continue;
            }else {
                if (i != numbers.length - 1) {
                    currentCounter++;
                }
                if(currentCounter > maxCounter) {
                    maxCounter = currentCounter;
                    repeatedDigit = numbers[i];

                }
            }
        }

        int[] maxSequenceArray = new int[maxCounter];
        for (int i = 0; i < maxCounter; i++) {
            maxSequenceArray[i] = repeatedDigit;
        }
        for (int i = 0; i < maxSequenceArray.length; i++) {
            System.out.print(maxSequenceArray[i] + " ");

        }

    }
}
