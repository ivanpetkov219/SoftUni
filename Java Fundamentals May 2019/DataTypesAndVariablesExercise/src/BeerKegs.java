import java.util.Scanner;

public class BeerKegs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfKegs = Integer.parseInt(scanner.nextLine());
        String kegName = "";
        String currentKeg = "";
        double biggestKeg = Double.MIN_VALUE;

        for (int i = 1; i <= numberOfKegs; i++) {
            currentKeg = scanner.nextLine();
            double radiusOfKeg = Double.parseDouble(scanner.nextLine());
            int heightOfKeg = Integer.parseInt(scanner.nextLine());
            double kegVolume = Math.PI * (radiusOfKeg * radiusOfKeg) * heightOfKeg;

            if (kegVolume >= biggestKeg) {
                biggestKeg = kegVolume;
                kegName = currentKeg;
            }


        }

        System.out.println(kegName);
    }
}
