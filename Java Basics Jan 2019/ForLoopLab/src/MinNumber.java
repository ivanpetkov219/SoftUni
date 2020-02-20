import java.util.Scanner;

public class MinNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberCount = Integer.parseInt(scanner.nextLine());
        int minNumber = Integer.MAX_VALUE;

        for (int number = 1; number <= numberCount; number++){
            int currentNumber = Integer.parseInt(scanner.nextLine());

            if (currentNumber < minNumber){
                minNumber = currentNumber;
            }

        }
        System.out.println(minNumber);
    }
}
