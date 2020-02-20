import java.util.Scanner;

public class CleverLilly {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        double machinePrice = Double.parseDouble(scanner.nextLine());
        int toyPrice = Integer.parseInt(scanner.nextLine());

        int savedMoney = 0;
        int toyNumber = 0;
        int stolenMoney = 0;

        for (int currentAge = 1; currentAge <= age; currentAge++) {

            if (currentAge % 2 == 0) {
                stolenMoney++;
                savedMoney += stolenMoney * 10;
            } else {
                toyNumber++;
            }


        }
        int totalMoney = savedMoney + toyNumber * toyPrice - stolenMoney;

        if (totalMoney >= machinePrice){
            double left = totalMoney - machinePrice;
            System.out.printf("Yes! %.2f", left);
        }else {
            double needed = machinePrice - totalMoney;
            System.out.printf("No! %.2f", needed);
        }
    }
}
