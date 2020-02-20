import java.util.Scanner;

public class CelsiusToFahrenheit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double celsius = Double.parseDouble(scanner.nextLine());
        double fahrenheit = 32 + celsius * 1.800;
        System.out.printf("%.2f", fahrenheit);
    }
}
