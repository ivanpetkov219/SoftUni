import java.util.Scanner;

public class FishingBoat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        int fisherman = Integer.parseInt(scanner.nextLine());

        double rent = 0;

        if ("Spring".equals(season)){
            rent = 3000;
        }else if ("Summer".equals(season) || "Autumn".equals(season)){
            rent = 4200;
        }else if ("Winter".equals(season)){
            rent = 2600;
        }

        if (fisherman <=6){
            rent *= 0.9;
        }else if (fisherman <=11){
            rent *= 0.85;
        }else {
            rent *= 0.75;
        }

        if (("Spring".equals(season) || "Summer".equals(season) || "Winter".equals(season)) && fisherman % 2 == 0){
            rent *= 0.95;
        }

        if (budget >= rent){
            double moneyLeft = budget - rent;
            System.out.printf("Yes! You have %.2f leva left.", moneyLeft);
        }else {
            double moneyNeeded = rent - budget;
            System.out.printf("Not enough money! You need %.2f leva.", moneyNeeded);
        }
    }
}