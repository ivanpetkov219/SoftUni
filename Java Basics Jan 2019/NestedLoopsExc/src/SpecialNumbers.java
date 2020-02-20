import java.util.Scanner;

public class SpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());



        for (int i = 1; i <= 9 ; i++) {
            for (int j = 1; j <= 9 ; j++) {
                for (int k = 1; k <= 9 ; k++) {
                    for (int l = 1; l <= 9 ; l++) {

                        boolean dividedByFirstNumber = false;
                        boolean dividedBySecondNumber = false;
                        boolean dividedByThirdNumber = false;
                        boolean dividedByFourthNumber = false;

                        if ( number % i == 0) {
                            dividedByFirstNumber = true;
                        }
                        if (number % j == 0) {
                            dividedBySecondNumber = true;
                        }
                        if (number % k == 0) {
                            dividedByThirdNumber = true;
                        }
                        if (number % l == 0) {
                            dividedByFourthNumber = true;
                        }

                        if (dividedByFirstNumber && dividedBySecondNumber && dividedByThirdNumber && dividedByFourthNumber){

                            System.out.printf("%d%d%d%d ", i, j, k, l);

                        }

                    }

                }

            }

        }
    }
}
