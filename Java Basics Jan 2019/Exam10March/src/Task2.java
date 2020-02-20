import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int controlMinutes = Integer.parseInt(scanner.nextLine());
        int controlSeconds = Integer.parseInt(scanner.nextLine());
        double gutterLenght = Double.parseDouble(scanner.nextLine());
        int secondsFor100meters = Integer.parseInt(scanner.nextLine());

        int controlInSeconds = controlMinutes * 60 + controlSeconds;
        double delay = gutterLenght / 120 * 2.5;

        double totalTime = gutterLenght / 100 * secondsFor100meters - delay;

        if (totalTime <= controlInSeconds){
            System.out.println("Marin Bangiev won an Olympic quota!");
            System.out.printf("His time is %.3f.", totalTime);
        }else {
            double needed = totalTime - controlInSeconds;
            System.out.printf("No, Marin failed! He was %.3f second slower.", needed);
        }


    }
}
