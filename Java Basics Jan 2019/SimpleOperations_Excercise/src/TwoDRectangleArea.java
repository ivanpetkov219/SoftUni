import java.util.Scanner;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.parseDouble;

public class TwoDRectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x1 = Double.parseDouble(scanner.nextLine());
        double y1 = Double.parseDouble(scanner.nextLine());
        double x2 = Double.parseDouble(scanner.nextLine());
        double y2 = Double.parseDouble(scanner.nextLine());

        double sideA = Math.abs(x1 - x2);
        double sideB = Math.abs(y1 - y2);

        double area = sideA * sideB;

        double perimeter = 2 * (sideA + sideB);

        System.out.printf("%.2f%n", area);
        System.out.printf("%.2f", perimeter);
    }
}
