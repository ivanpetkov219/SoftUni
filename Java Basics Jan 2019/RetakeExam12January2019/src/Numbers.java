import java.util.Scanner;

public class Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String numberInString = "" + number;

        int firstNumber = Integer.parseInt("" + numberInString.charAt(0));
        int secondNumber = Integer.parseInt("" + numberInString.charAt(1));
        int thirdNumber = Integer.parseInt("" + numberInString.charAt(2));

        int N = firstNumber + secondNumber;
        int M = firstNumber + thirdNumber;


        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= M ; j++) {

                if ( number % 5 == 0){
                    number -= firstNumber;
                }else if ( number % 3 == 0){
                    number -= secondNumber;
                }else {
                    number += thirdNumber;
                }
                System.out.print(number + " ");

            }
            System.out.println();
        }


    }
}
