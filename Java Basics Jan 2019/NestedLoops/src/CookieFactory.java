import java.util.Scanner;

public class CookieFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int batchNumber = Integer.parseInt(scanner.nextLine());
        boolean hasFlour = false;
        boolean hasSugar = false;
        boolean hasEggs = false;

        for (int currentBatch = 1; currentBatch <= batchNumber ; currentBatch++) {
            String input = scanner.nextLine();
            while (!"Bake!".equals(input)){

                if ("flour".equals(input)) {
                    hasFlour = true;
                } else if ("sugar".equals(input)) {
                    hasSugar = true;
                } else if ("eggs".equals(input)) {
                    hasEggs = true;
                }

                input = scanner.nextLine();
            }
            if (hasEggs && hasFlour && hasSugar){
                System.out.printf("Baking batch number %d...%n", currentBatch);
                hasEggs = false;
                hasFlour = false;
                hasSugar = false;
            }else {
                System.out.println("The batter should contain flour, eggs and sugar!");
                currentBatch--;

            }

        }
    }
}
