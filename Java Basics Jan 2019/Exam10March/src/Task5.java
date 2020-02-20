import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int visitors = Integer.parseInt(scanner.nextLine());

        int backTrainers = 0;
        int chestTrainers = 0;
        int legsTrainers = 0;
        int absTrainers = 0;
        int shakeBuyers = 0;
        int barBuyers = 0;
        int totalTrainers = 0;
        int totalBuyers = 0;

        for (int currentVisitor = 1; currentVisitor <= visitors; currentVisitor++) {
            String action = scanner.nextLine();

            switch (action) {
                case "Back":
                    backTrainers++;
                    totalTrainers++;
                    break;
                case "Chest":
                    chestTrainers++;
                    totalTrainers++;
                    break;
                case "Legs":
                    legsTrainers++;
                    totalTrainers++;
                    break;
                case "Abs":
                    absTrainers++;
                    totalTrainers++;
                    break;
                case "Protein shake":
                    shakeBuyers++;
                    totalBuyers++;
                    break;
                case "Protein bar":
                    barBuyers++;
                    totalBuyers++;
                    break;
            }


        }

        double percentTrainers = totalTrainers * 1.0 / visitors * 100;
        double percentBuyers = totalBuyers * 1.0 / visitors * 100;

        System.out.printf("%d - back%n", backTrainers);
        System.out.printf("%d - chest%n", chestTrainers);
        System.out.printf("%d - legs%n", legsTrainers);
        System.out.printf("%d - abs%n", absTrainers);
        System.out.printf("%d - protein shake%n", shakeBuyers);
        System.out.printf("%d - protein bar%n", barBuyers);
        System.out.printf("%.2f%% - work out%n", percentTrainers);
        System.out.printf("%.2f%% - protein", percentBuyers);

    }
}
