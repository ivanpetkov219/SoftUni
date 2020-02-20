import java.util.Scanner;

public class Choreography {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int steps = Integer.parseInt(scanner.nextLine());
        int dancers = Integer.parseInt(scanner.nextLine());
        int days = Integer.parseInt(scanner.nextLine());

       double stepsPerDay = steps * 1.0 / days;
       double percentageStepsPerDay = Math.ceil((stepsPerDay / steps) * 100);
       double stepsPerDancer = percentageStepsPerDay / dancers;


        if (percentageStepsPerDay <= 13){
            System.out.printf("Yes, they will succeed in that goal! %.2f", stepsPerDancer);
            System.out.println("%.");

        }else if (percentageStepsPerDay > 13){
            System.out.printf("No, they will not succeed in that goal! Required %.2f", stepsPerDancer);
            System.out.println("% steps to be learned per day.");
        }


    }
}
