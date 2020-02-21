import java.util.Scanner;

public class TheHuntingGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = scanner.nextInt();
        int playersCount = scanner.nextInt();
        double groupEnergy = scanner.nextDouble();
        double waterPerPersonPerDay = scanner.nextDouble();
        double foodPerPersonPerDay = scanner.nextDouble();

        double totalFood = days * playersCount * foodPerPersonPerDay;
        double totalWater = days * playersCount * waterPerPersonPerDay;

        for (int i = 1; i <= days ; i++) {
            double energyLoss = scanner.nextDouble();
            groupEnergy -= energyLoss;
            if(groupEnergy <= 0){
                break;
            }
            if(i % 2 == 0){
                groupEnergy *= 1.05;
                totalWater *= 0.70;
            }
            if (i % 3 == 0){
                groupEnergy *= 1.10;
                totalFood -= totalFood / playersCount;
            }



        }


        if(groupEnergy > 0){
            System.out.printf("You are ready for the quest. You will be left with - %.2f energy!", groupEnergy);
        }else {
            System.out.printf("You will run out of energy. You will be left with %.2f food and %.2f water.", totalFood, totalWater);
        }
    }
}
