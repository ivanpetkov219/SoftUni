import java.util.Scanner;

public class ThreeBrothers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstBrotherHours = Double.parseDouble(scanner.nextLine());
        double secondBrotherHours = Double.parseDouble(scanner.nextLine());
        double thirdBrotherHours = Double.parseDouble(scanner.nextLine());
        double fatherFishingTimeHours = Double.parseDouble(scanner.nextLine());

        double cleaningTime = 1/ (1/firstBrotherHours + 1/secondBrotherHours + 1/thirdBrotherHours);
        double cleaningTimeWithBreaks = cleaningTime *1.15;

        System.out.printf("Cleaning time: %.2f%n", cleaningTimeWithBreaks);


        if (cleaningTimeWithBreaks <= fatherFishingTimeHours){
            double timeLeft = Math.floor(fatherFishingTimeHours - cleaningTimeWithBreaks);
            System.out.printf("Yes, there is a surprise - time left -> %.0f hours.",timeLeft);
        }else {
            double timeNeeded = Math.ceil(cleaningTimeWithBreaks - fatherFishingTimeHours);
            System.out.printf("No, there isn't a surprise - shortage of time -> %.0f hours.",timeNeeded);
        }

    }
}
