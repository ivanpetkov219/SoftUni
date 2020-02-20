import java.util.Scanner;

public class EqualSumsEvenOddPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());

        for (int i = firstNumber; i <= secondNumber ; i++) {
            int evenSum = 0;
            int oddSum = 0;

            String currentNumber = "" + i;

            for (int j = 0; j < currentNumber.length() ; j++) {
                int number = Integer.parseInt("" + currentNumber.charAt(j));
                if (j % 2 == 0){
                    evenSum += number;
                }else {
                    oddSum += number;
                }

            }

            if (evenSum == oddSum){
                System.out.print(i + " ");
            }

        }
    }
}
