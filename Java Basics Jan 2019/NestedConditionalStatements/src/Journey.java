import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();

        double expences = 0;

        if (budget <= 100) {
            System.out.println("Somewhere in Bulgaria");
            switch (season) {
                case "summer":
                    expences = budget * 0.30;
                    System.out.printf("Camp - %.2f", expences);
                    break;
                case "winter":
                    expences = budget * 0.70;
                    System.out.printf("Hotel - %.2f", expences);
                    break;
            }

        } else if (budget <= 1000) {
            System.out.println("Somewhere in Balkans");
            switch (season) {
                case "summer":
                    expences = budget * 0.40;
                    System.out.printf("Camp - %.2f", expences);
                    break;
                case "winter":
                    expences = budget * 0.80;
                    System.out.printf("Hotel - %.2f", expences);
                    break;
            }
        } else if (budget > 1000) {
            expences = budget * 0.9;
            System.out.println("Somewhere in Europe");
            System.out.printf("Hotel - %.2f", expences);
        }

    }
}

