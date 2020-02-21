import java.util.Scanner;

public class AddAndSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());
        int thirdNumber = Integer.parseInt(scanner.nextLine());

        int finalScore = getDiff(getSum(firstNumber, secondNumber), thirdNumber);

        System.out.println(finalScore);


    }
    static int getSum(int a, int b){
        int result = a + b;
        return result;
    }

    static int getDiff(int a, int b){
        int result = a - b;
        return result;
    }
}
