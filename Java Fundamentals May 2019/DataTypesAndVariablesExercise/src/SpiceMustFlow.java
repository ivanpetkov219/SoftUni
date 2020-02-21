import java.util.Scanner;

public class SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startingYield = scanner.nextInt();
        int daysWorked = 0;

        int totalYield = 0;
        while (startingYield >= 100){
            daysWorked++;
            totalYield += startingYield - 26;


            startingYield -= 10;
        }

        if (totalYield > 26) {
            totalYield -= 26;
        }else
            totalYield = 0;
        System.out.println(daysWorked);
        System.out.println(totalYield);
    }
}
