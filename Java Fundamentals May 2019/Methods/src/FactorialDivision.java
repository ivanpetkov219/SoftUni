import java.math.BigInteger;
import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());

        getDivision(calculateFactorial(firstNumber), calculateFactorial(secondNumber));
    }

    static long calculateFactorial(int a){
        long factorial = 1;

        for (int i = 2; i <= a ; i++) {

            factorial = factorial * i;
        }
        return factorial;
    }

    static void getDivision (long a, long b){
        double division = a * 1.0 / b;
        System.out.printf("%.2f", division);
    }
}
