import java.util.Scanner;

public class CharityCampaign {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysOfCampaign = Integer.parseInt(scanner.nextLine());
        int bakersNumber = Integer.parseInt(scanner.nextLine());
        int cakesNumber = Integer.parseInt(scanner.nextLine());
        int wafflesNumber = Integer.parseInt(scanner.nextLine());
        int pancakesNumber = Integer.parseInt(scanner.nextLine());

        double sumPerBakerPerDay = (cakesNumber * 45) + (wafflesNumber * 5.80) + (pancakesNumber * 3.20);

        double sumPerDay = sumPerBakerPerDay * bakersNumber;

        double expences = sumPerDay / 8;

        double totalProfit = sumPerDay * daysOfCampaign - (expences * daysOfCampaign);

        System.out.printf("%.2f", totalProfit);

    }
}
