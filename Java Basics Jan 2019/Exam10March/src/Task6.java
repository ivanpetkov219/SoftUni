import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        int winCount = 0;
        int lostCount = 0;
        int totalGamesCount = 0;

        while (!"End of tournaments".equals(input)){
            int games = Integer.parseInt(scanner.nextLine());

            for (int currentGame = 1; currentGame <= games ; currentGame++) {
                totalGamesCount++;

                int pointsTeamDesi = Integer.parseInt(scanner.nextLine());
                int pointsOtherTeam = Integer.parseInt(scanner.nextLine());
                int diff = Math.abs(pointsTeamDesi - pointsOtherTeam);

                if (pointsTeamDesi > pointsOtherTeam){
                    winCount++;
                    System.out.printf("Game %d of tournament %s: win with %d points.%n", currentGame, input, diff);
                }else if (pointsOtherTeam > pointsTeamDesi){
                    lostCount++;
                    System.out.printf("Game %d of tournament %s: lost with %d points.%n", currentGame, input, diff);
                }
            }
            input = scanner.nextLine();
        }
        double percentWins = winCount * 1.0 / totalGamesCount * 100;
        double percentLost = lostCount * 1.0 / totalGamesCount * 100;

        System.out.printf("%.2f%% matches win%n", percentWins);
        System.out.printf("%.2f%% matches lost", percentLost);


    }
}
