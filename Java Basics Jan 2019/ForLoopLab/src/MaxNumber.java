import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberCount = Integer.parseInt(scanner.nextLine());
        int maxNumber = Integer.MIN_VALUE;

        for (int number = 1; number <= numberCount; number++){
            int currentNumber = Integer.parseInt(scanner.nextLine());

            if (currentNumber > maxNumber){
                maxNumber = currentNumber;
            }

        }
        System.out.println(maxNumber);
    }
}
