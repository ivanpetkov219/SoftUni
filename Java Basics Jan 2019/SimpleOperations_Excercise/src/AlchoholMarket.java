import java.util.Scanner;

public class AlchoholMarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double whiskeyPrice = Double.parseDouble(scanner.nextLine());
        double beerLiters = Double.parseDouble(scanner.nextLine());
        double wineLiters = Double.parseDouble(scanner.nextLine());
        double brandyLiters = Double.parseDouble(scanner.nextLine());
        double whiskeyLiters = Double.parseDouble(scanner.nextLine());

        double brandyPrice = whiskeyPrice*0.5;
        double winePrice = brandyPrice*0.6;
        double beerPrice = brandyPrice*0.2;

        double totalPrice = (whiskeyPrice * whiskeyLiters)+ (beerLiters * beerPrice)+ (wineLiters * winePrice)+(brandyLiters * brandyPrice);

        System.out.printf("%.2f", totalPrice);

    }
}
