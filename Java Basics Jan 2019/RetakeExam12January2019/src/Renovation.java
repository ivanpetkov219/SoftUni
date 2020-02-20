import java.util.Scanner;

public class Renovation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budjet = Double.parseDouble(scanner.nextLine());
        double floorWidth = Double.parseDouble(scanner.nextLine());
        double floorLenght = Double.parseDouble(scanner.nextLine());
        double triangleSide = Double.parseDouble(scanner.nextLine());
        double triangleHeight = Double.parseDouble(scanner.nextLine());
        double tilePrice = Double.parseDouble(scanner.nextLine());
        double handymanPrice = Double.parseDouble(scanner.nextLine());

        double floorSize = floorLenght * floorWidth;
        double tileSize = (triangleHeight * triangleSide) / 2;

        double tilesNeeded = Math.ceil(floorSize / tileSize) + 5;
        double totalTilesPrice = tilesNeeded * tilePrice;

        double totalPrice = totalTilesPrice + handymanPrice;

        if (budjet >= totalPrice){
            double left = budjet - totalPrice;
            System.out.printf("%.2f lv left.", left);
        }else {
            double needed = totalPrice - budjet;
            System.out.printf("You'll need %.2f lv more.", needed);
        }


    }
}
