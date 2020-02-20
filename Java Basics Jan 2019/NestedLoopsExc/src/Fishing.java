import java.util.Scanner;

public class Fishing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxFishes = Integer.parseInt(scanner.nextLine());
        int counter = 0;
        double moneySpent = 0;


        String input = scanner.nextLine();

        while (!"Stop".equals(input)){
            double fishWeight = Double.parseDouble(scanner.nextLine());
            double fishPrice = 0;
            int nameSum = 0;

            for (int i = 0; i < input.length() ; i++) {

                nameSum += input.charAt(i);
            }
            fishPrice = nameSum * 1.0 / fishWeight;

            counter++;
            if (counter % 3 == 0){
                moneySpent -= fishPrice;
            }else {
                moneySpent += fishPrice;
            }

            if (counter >= maxFishes){
                System.out.println("Lyubo fulfilled the quota!");
                break;
            }
            input = scanner.nextLine();
        }

        if (moneySpent >= 0){
            System.out.printf("Lyubo lost %.2f leva today.", moneySpent);

        }else {
            System.out.printf("Lyubo's profit from %d fishes is %.2f leva.", counter, Math.abs(moneySpent));
        }
    }
}
