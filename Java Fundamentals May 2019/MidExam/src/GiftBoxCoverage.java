import java.util.Scanner;

public class GiftBoxCoverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double sideSize = Double.parseDouble(scanner.nextLine());
        int sheetOfPaperNumber = Integer.parseInt(scanner.nextLine());
        double sheetOfPaperArea = Double.parseDouble(scanner.nextLine());

        double areaCovered = 0;

        double boxArea = sideSize * sideSize * 6;
        for (int i = 1; i <= sheetOfPaperNumber ; i++) {
            if(i % 3 != 0){
                areaCovered += sheetOfPaperArea;
            }else {
                areaCovered += sheetOfPaperArea * 0.25;
            }

        }
        double resultInPercent = areaCovered / boxArea * 100;

        System.out.printf("You can cover %.2f%% of the box.", resultInPercent);
    }
}
