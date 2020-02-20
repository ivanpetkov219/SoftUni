import java.util.Scanner;

public class TailoringWorkshop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tablesNumber = Integer.parseInt(scanner.nextLine());
        double tableLenght = Double.parseDouble(scanner.nextLine());
        double tableWidth = Double.parseDouble(scanner.nextLine());

        double coverArea = tablesNumber * ((tableLenght + 2*0.30) * (tableWidth + 2*0.30));
        double boxArea = tablesNumber * ((tableLenght * 0.5) * (tableLenght * 0.5));

        double priceUSD = coverArea * 7  + boxArea * 9;
        double priceBGN = priceUSD * 1.85;

        System.out.printf("%.2f" + " USD", priceUSD);
        System.out.println();
        System.out.printf("%.2f" + " BGN", priceBGN);
    }
}
