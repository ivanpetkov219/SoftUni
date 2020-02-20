import java.util.Scanner;

public class OddEvenSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int evenSum = 0;
        int oddSum = 0;

        for (int i = 1; i <= n; i++) {
            int currentNumber = Integer.parseInt(scanner.nextLine());

            if (i % 2 == 0){

                evenSum += currentNumber;
            }else
                oddSum += currentNumber;

        }

        if (oddSum == evenSum){
            System.out.printf("Yes%nSum = %d", oddSum);
        }else {
            int diff = Math.abs(oddSum - evenSum);
            System.out.printf("No%nDiff = %d", diff);
        }
    }
}
