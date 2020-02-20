import java.util.Scanner;

public class EqualPairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int currentSum = 0;
        int previousSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int maxDiff = 0;
        int diff = 0;


        for (int i = 1; i <= n; i++) {
            previousSum = currentSum;
            currentSum = 0;
            int number1 = Integer.parseInt(scanner.nextLine());
            int number2 = Integer.parseInt(scanner.nextLine());

            currentSum = number1 + number2;

            if (i != 1) {
                diff = Math.abs(currentSum - previousSum);
                if (diff != 0 && diff > maxDiff) {
                    maxDiff = diff;
                }
            }

        }

        if (currentSum == previousSum || n == 1) {
            System.out.printf("Yes, value=%d", currentSum);
        } else
            System.out.printf("No, maxdiff=%d", maxDiff);


    }

}


