import java.util.Scanner;

public class WaterOverflow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        byte fillings = scanner.nextByte();

        int sum = 0;
        for (int i = 1; i <= fillings ; i++) {
            int currentFilling = scanner.nextInt();
            sum += currentFilling;
            if (sum > 255){
                sum -= currentFilling;
                System.out.println("Insufficient capacity!");
            }

        }
        System.out.print(sum);
    }
}
