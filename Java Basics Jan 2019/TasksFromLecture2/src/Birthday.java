import java.util.Scanner;

public class Birthday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = Integer.parseInt(scanner.nextLine());
        int w = Integer.parseInt(scanner.nextLine());
        int h = Integer.parseInt(scanner.nextLine());
        double sandPercent = Double.parseDouble(scanner.nextLine());

        int volume = l * w * h;
        double totalLiters = volume * 0.001;
        double attributesVolume = sandPercent * 0.01;
        double result = totalLiters*(1 - attributesVolume);
        System.out.printf("%.3f", result);
        }
    }
