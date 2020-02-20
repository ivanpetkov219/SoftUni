import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pricePerformer = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        int totalGuests = 0;
        int totalPrice = 0;
        int currentPrice = 0;

        while (!"The restaurant is full".equals(input)) {
            int guests = Integer.parseInt(input);

            if (guests < 5){
                currentPrice = guests * 100;
            }else {
                currentPrice = guests * 70;
            }

            totalPrice += currentPrice;
            totalGuests += guests;
            input = scanner.nextLine();
        }



        if (totalPrice >= pricePerformer){
            int leftMoney = totalPrice - pricePerformer;
            System.out.printf("You have %d guests and %d leva left.", totalGuests, leftMoney);
        }else {
            System.out.printf("You have %d guests and %d leva income, but no singer.", totalGuests, totalPrice);
        }


    }
}