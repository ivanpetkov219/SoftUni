import java.util.Scanner;

public class WorldSwimmingRecord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double recordInSeconds = Double.parseDouble(scanner.nextLine());
        double distanceToSwimInMeters = Double.parseDouble(scanner.nextLine());
        double timeToSwimOneMeterInSeconds = Double.parseDouble(scanner.nextLine());

        double timeToSwim = distanceToSwimInMeters * timeToSwimOneMeterInSeconds;
        double resistanceInSeconds = Math.floor(distanceToSwimInMeters / 15 ) * 12.5;
        double entireTimeToSwim = timeToSwim + resistanceInSeconds;

        if (entireTimeToSwim < recordInSeconds){
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.",entireTimeToSwim);
        }else if (entireTimeToSwim >= recordInSeconds){
            double overTheRecordSeconds = entireTimeToSwim - recordInSeconds;
            System.out.printf("No, he failed! He was %.2f seconds slower.", overTheRecordSeconds);
        }



    }
}
