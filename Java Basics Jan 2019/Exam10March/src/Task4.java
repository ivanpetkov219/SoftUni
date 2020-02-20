import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String player = scanner.nextLine();
        String input = scanner.nextLine();

        int startingPoints = 301;
        int successShots = 0;
        int unsuccessShots = 0;

        while (!"Retire".equals(input)) {

            int points = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case "Single":
                    break;
                case "Double":
                    points *= 2;
                    break;
                case "Triple":
                    points *= 3;
                    break;
            }

            if (points <= startingPoints){
                startingPoints -= points;
                successShots++;
            }else {
                unsuccessShots++;
            }

            if (startingPoints == 0){
                break;
            }


            input = scanner.nextLine();
        }

        if ("Retire".equals(input) && startingPoints != 0){
            System.out.printf("%s retired after %d unsuccessful shots.", player, unsuccessShots);
        }else if (startingPoints == 0){
            System.out.printf("%s won the leg with %d shots.", player, successShots);
        }

    }
}
