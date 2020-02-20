import java.util.Scanner;

public class Volleyball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String yearType = scanner.nextLine();
        int celebrationDaysInYear = Integer.parseInt(scanner.nextLine());
        int weekendsAtHomeTowm = Integer.parseInt(scanner.nextLine());

        int weekendsInSofia = 48 - weekendsAtHomeTowm;
        double gamesInSofia = weekendsInSofia * 0.75;
        double gamesInHomeTown = weekendsAtHomeTowm;
        double gamesInCelebrationDays = celebrationDaysInYear * 2.0 / 3;

        double totalGames = gamesInSofia + gamesInHomeTown + gamesInCelebrationDays;

        if ("leap".equals(yearType)){
            totalGames *= 1.15;
        }

        System.out.printf("%.0f", Math.floor(totalGames));
    }
}
