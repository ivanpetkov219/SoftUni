import java.util.Scanner;

public class TopInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       int number = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= number ; i++) {

            if (getSum(i) % 8 == 0 && hasOneOddDigit(i)){
                System.out.println(i);
            }
        }
    }
    static int getSum (int input){
        int sum = 0;
        int result = 0;
        while ( input > 0){
            result = input % 10;
            sum += result;
            input /= 10;
        }
        return sum;
    }
    static boolean hasOneOddDigit(int input){
        boolean hasOddDigit = false;
        int result = 0;
        while (input > 0){
            result = input % 10;

            if(result % 2 == 1){
                hasOddDigit = true;
                break;
            }
            input /= 10;
        }
        return hasOddDigit;
    }
}
