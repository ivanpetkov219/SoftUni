import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double width = Double.parseDouble(scanner.nextLine());
        double lenght = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());
        double averageHeightAstronaut = Double.parseDouble(scanner.nextLine());

        double roomSize = 2 * 2 * (averageHeightAstronaut + 0.40);
        double shipSize = width * lenght * height;

        double numberAstronauts = Math.floor(shipSize / roomSize);

        if ( numberAstronauts < 3){
            System.out.println("The spacecraft is too small.");
        }else if (numberAstronauts <= 10){
            System.out.printf("The spacecraft holds %.0f astronauts.", numberAstronauts);
        }else if (numberAstronauts > 10) {
            System.out.println("The spacecraft is too big.");
        }






    }
}