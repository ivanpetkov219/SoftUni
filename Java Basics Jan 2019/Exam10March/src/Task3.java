import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tournamentStage = scanner.nextLine();
        String ticketType = scanner.nextLine();
        int numberTickets = Integer.parseInt(scanner.nextLine());
        char tropheyPicture = scanner.nextLine().charAt(0);

        double ticketPrice = 0;
        boolean pictureWanted = false;

        if ('Y' == tropheyPicture){
            pictureWanted = true;
        }

        if ("Quarter final".equals(tournamentStage)) {
            switch (ticketType) {
                case "Standard":
                    ticketPrice = 55.50;
                    break;
                case "Premium":
                    ticketPrice = 105.20;
                    break;
                case "VIP":
                    ticketPrice = 118.90;
                    break;
            }
        } else if ("Semi final".equals(tournamentStage)) {
            switch (ticketType) {
                case "Standard":
                    ticketPrice = 75.88;
                    break;
                case "Premium":
                    ticketPrice = 125.22;
                    break;
                case "VIP":
                    ticketPrice = 300.40;
                    break;
            }
        } else if ("Final".equals(tournamentStage)) {
            switch (ticketType) {
                case "Standard":
                    ticketPrice = 110.10;
                    break;
                case "Premium":
                    ticketPrice = 160.66;
                    break;
                case "VIP":
                    ticketPrice = 400;
                    break;
            }
        }

        double totalPrice = numberTickets * ticketPrice;

        if (totalPrice > 4000){
            totalPrice *= 0.75;
            pictureWanted = false;
        }else if (totalPrice > 2500){
            totalPrice *= 0.90;
        }

        if (totalPrice <= 4000 && pictureWanted){
            totalPrice += numberTickets * 40;
        }

        System.out.printf("%.2f", totalPrice);




    }
}
