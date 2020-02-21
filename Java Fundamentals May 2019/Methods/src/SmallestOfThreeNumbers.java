import java.util.Scanner;

public class SmallestOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());
        int thirdNumber = Integer.parseInt(scanner.nextLine());

        int result = getSmallerNumber(getSmallerNumber(firstNumber, secondNumber), thirdNumber);

        System.out.println(result);



    }

    static int getSmallerNumber(int a, int b){
        if (a > b){
            return b;
        }else
            return a;
    }
}
