import java.util.Scanner;

public class EqualSumsLeftRightPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());

        for (int i = firstNumber; i <= secondNumber ; i++) {

            String currentNumber = "" + i;

            int leftSum = 0;
            int rightSum = 0;

            int firstDigit = Integer.parseInt("" + currentNumber.charAt(0));
            int secondDigit = Integer.parseInt("" + currentNumber.charAt(1));
            int thirdDigit = Integer.parseInt("" + currentNumber.charAt(2));
            int fourthDigit = Integer.parseInt("" + currentNumber.charAt(3));
            int fifthDigit = Integer.parseInt("" + currentNumber.charAt(4));

            leftSum = firstDigit + secondDigit;
            rightSum = fourthDigit + fifthDigit;

            if (leftSum == rightSum){
                System.out.print(i +" ");
            }else {
                if (leftSum > rightSum){
                    rightSum += thirdDigit;
                }else {
                    leftSum += thirdDigit;
                }
                if (leftSum == rightSum){
                    System.out.print(i + " ");
                }
            }

        }
    }
}
