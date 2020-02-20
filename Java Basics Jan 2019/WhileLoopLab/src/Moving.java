import java.util.Scanner;

public class Moving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int widthArea = Integer.parseInt(scanner.nextLine());
        int lenghtArea = Integer.parseInt(scanner.nextLine());
        int heightArea = Integer.parseInt(scanner.nextLine());

        int boxAreaInCubicMeters = 1;
        int spaceAreaInCubicMeters = widthArea * lenghtArea * heightArea;
        int sumBoxes = 0;

        String input = scanner.nextLine();

        while (!"Done".equals(input)){
            int boxes = Integer.parseInt(input);
            sumBoxes += boxes;

            if (sumBoxes > spaceAreaInCubicMeters){
                int diff = sumBoxes - spaceAreaInCubicMeters;
                System.out.printf("No more free space! You need %d Cubic meters more.", diff);
                break;
            }
            input = scanner.nextLine();
        }
        if (spaceAreaInCubicMeters > sumBoxes){
            int left = spaceAreaInCubicMeters - sumBoxes;
            System.out.printf("%d Cubic meters left.", left);
        }
    }
}
