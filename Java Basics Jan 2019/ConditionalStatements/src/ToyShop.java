import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double tripPrice = Double.parseDouble(scanner.nextLine());
        int numberPuzzles = Integer.parseInt(scanner.nextLine());
        int numberDalls = Integer.parseInt(scanner.nextLine());
        int numberBears = Integer.parseInt(scanner.nextLine());
        int numberMinions = Integer.parseInt(scanner.nextLine());
        int numberTrucks = Integer.parseInt(scanner.nextLine());

        double numberToys = numberPuzzles + numberDalls + numberBears + numberMinions + numberTrucks;
        double orderPrice = (numberPuzzles * 2.60) + (numberDalls * 3) + (numberBears * 4.10) + (numberMinions * 8.20) + (numberTrucks * 2);

        if (numberToys >= 50){
            orderPrice = orderPrice * 0.75;
        }
        double rent = orderPrice * 0.10;

        double finalProfit = orderPrice - rent;


        if (finalProfit >= tripPrice){
            System.out.printf("Yes! %.2f lv left.", finalProfit - tripPrice);
        }else
            System.out.printf("Not enough money! %.2f lv needed.", tripPrice - finalProfit);

    }
}
