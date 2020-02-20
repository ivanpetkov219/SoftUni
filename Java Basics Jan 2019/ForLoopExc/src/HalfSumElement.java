import java.util.Scanner;

public class HalfSumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int maxNumber = Integer.MIN_VALUE;
        double sum = 0;

        for (int number = 1; number <= n; number++) {
            int currentNumber = Integer.parseInt(scanner.nextLine());

            sum+= currentNumber;

            if (currentNumber > maxNumber){
                maxNumber = currentNumber;
            }

        }
        if (sum / 2 == maxNumber){
            System.out.println("Yes");
            System.out.println("Sum = " + maxNumber);
        } else {
            double diff = Math.abs(maxNumber - (sum - maxNumber));
            System.out.println("No");
            System.out.printf("Diff = %.0f", diff);
        }
    }
}
