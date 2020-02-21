import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstNumber = scanner.nextLine();
        int secondNumber = scanner.nextInt();
        int transferDigit = 0;

        StringBuilder result = new StringBuilder();
        List<String> finalResult = new ArrayList<>();

        for (int i = firstNumber.length() - 1; i >= 0; i--) {
            int currentDigit = firstNumber.charAt(i) - '0';
            int multipliedNumber = currentDigit * secondNumber + transferDigit;


            if(multipliedNumber > 9){
                String numberInString = multipliedNumber + "";
                transferDigit = numberInString.charAt(0) - '0';
                char secondDigit = numberInString.charAt(1);
                result.append(secondDigit);

            }else {
                result.append(multipliedNumber);
                transferDigit = 0;
            }
            if(i == 0 && transferDigit != 0){
                result.append(transferDigit);
            }
        }
        result.reverse();

       String string = result.toString();




        if(!firstNumber.equals("0") && secondNumber != 0) {
            trimLeadingZerosAndPrint(result);
        }else
            System.out.println(0);

    }

    private static void trimLeadingZerosAndPrint(StringBuilder result) {
        boolean isZero = true;


        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 0 && isZero) {
                continue;
            }else {
                isZero = false;
                System.out.print(result.charAt(i));
            }
        }


    }
}
