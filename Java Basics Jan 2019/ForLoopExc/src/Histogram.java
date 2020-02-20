import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;

        for (int i = 1; i <= n; i++) {
            int number = Integer.parseInt(scanner.nextLine());

            if (number < 200) {
                count1++;
            } else if (number < 400) {
                count2++;
            } else if (number < 600) {
                count3++;
            } else if (number < 800) {
                count4++;
            }else if (number >= 800){
                count5++;
            }
        }

        double percent1 = count1 * 1.0 / n * 100;
        double percent2 = count2 * 1.0 / n * 100;
        double percent3 = count3 * 1.0 / n * 100;
        double percent4 = count4 * 1.0 / n * 100;
        double percent5 = count5 * 1.0 / n * 100;

        System.out.printf("%.2f%%%n", percent1);
        System.out.printf("%.2f%%%n", percent2);
        System.out.printf("%.2f%%%n", percent3);
        System.out.printf("%.2f%%%n", percent4);
        System.out.printf("%.2f%%%n", percent5);
    }
}
