import java.util.Scanner;

public class LeftAndRightSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberCount = Integer.parseInt(scanner.nextLine());
        int leftSum = 0;
        int rightSum = 0;


        for (int i = 1; i <= numberCount * 2  ; i++) {
            int currentNumber = Integer.parseInt(scanner.nextLine());

            if (i <= numberCount){
                leftSum += currentNumber;
            }else {
                rightSum +=currentNumber;
            }
        }

        int result = leftSum + rightSum;

        if (leftSum == rightSum){
            System.out.printf("Yes, sum = %d", leftSum);
        }else{
            int diff = Math.abs(leftSum - rightSum);
            System.out.printf("No, diff = %d", diff);
        }

    }
}
