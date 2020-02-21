import java.util.Scanner;

public class TriangleOfNumbers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        int count = 1;

        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d ", count);

            }
            count++;
            System.out.println();
        }
    }
}
