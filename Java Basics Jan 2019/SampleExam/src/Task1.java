import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double foodPerDay = Double.parseDouble(scanner.nextLine());
        double souvenirsPerDay = Double.parseDouble(scanner.nextLine());
        double hotelPerDay = Double.parseDouble(scanner.nextLine());

        double gasLiters = 4.2 * 7;
        double gasPrice = gasLiters * 1.85;

        double foodPrice = 3 * foodPerDay;
        double souvenirPrice = 3 * souvenirsPerDay;

        double hotelPriceFirstDay = hotelPerDay * 0.9;
        double hotelPriceSecondDay = hotelPerDay * 0.85;
        double hotelPriceThirdDay = hotelPerDay * 0.8;

        double totalPrice = gasPrice + foodPrice + souvenirPrice + hotelPriceFirstDay + hotelPriceSecondDay + hotelPriceThirdDay;

        System.out.printf("Money needed: %.2f", totalPrice);
    }
}
