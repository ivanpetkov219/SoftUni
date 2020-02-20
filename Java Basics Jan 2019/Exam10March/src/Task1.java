import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int yearTax = Integer.parseInt(scanner.nextLine());

        double shoes = yearTax * 0.60;
        double outfit = shoes * 0.80;
        double ball = outfit * 0.25;
        double accessories = ball * 0.20;

        double totalPrice = yearTax + shoes + outfit + ball + accessories;

        System.out.printf("%.2f", totalPrice);
    }
}
