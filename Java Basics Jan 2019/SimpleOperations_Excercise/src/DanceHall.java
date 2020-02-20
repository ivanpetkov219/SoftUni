import java.util.Scanner;

public class DanceHall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double hallLenght = Double.parseDouble(scanner.nextLine());
        double hallWidth = Double.parseDouble(scanner.nextLine());
        double wardrobeSide = Double.parseDouble(scanner.nextLine());

        double hallArea = (hallLenght*100) * (hallWidth*100);
        double wardrobeArea = (wardrobeSide*100) * (wardrobeSide*100);
        double benchArea = (hallArea*0.1);
        double freeArea = hallArea - wardrobeArea - benchArea;

        double dancersNumber = freeArea / 7040;

        System.out.printf("%.0f", Math.floor(dancersNumber));

        }
    }
