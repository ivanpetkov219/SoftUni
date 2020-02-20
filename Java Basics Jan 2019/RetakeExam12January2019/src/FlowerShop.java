import java.util.Scanner;

public class FlowerShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int chrysanthemumsCount = Integer.parseInt(scanner.nextLine());
        int rosesCount = Integer.parseInt(scanner.nextLine());
        int tulipsCount = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        char holiday = scanner.nextLine().charAt(0);

        double chrysanthemumsPrice = 0;
        double rosesPrice = 0;
        double tulipsPrice = 0;
        double totalFlowersCount = chrysanthemumsCount + rosesCount + tulipsCount;


        if ("Spring".equals(season) || "Summer".equals(season)){
            chrysanthemumsPrice = 2;
            rosesPrice = 4.10;
            tulipsPrice = 2.50;
        }else if ("Autumn".equals(season) || "Winter".equals(season)){
            chrysanthemumsPrice = 3.75;
            rosesPrice = 4.50;
            tulipsPrice = 4.15;
        }

        double bouquetPrice = chrysanthemumsCount * chrysanthemumsPrice + rosesCount * rosesPrice + tulipsCount * tulipsPrice;

        if ('Y' == holiday){
            bouquetPrice *= 1.15;
        }

        if ("Spring".equals(season) && tulipsCount > 7){
            bouquetPrice *= 0.95;
        }
        if ("Winter".equals(season) && rosesCount >= 10){
            bouquetPrice *= 0.90;
        }
        if (totalFlowersCount > 20){
            bouquetPrice *= 0.80;
        }

        double finalPrice = bouquetPrice + 2;

        System.out.printf("%.2f", finalPrice);




    }
}
