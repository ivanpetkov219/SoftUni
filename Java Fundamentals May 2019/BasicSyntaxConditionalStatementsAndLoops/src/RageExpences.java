import java.util.Scanner;

public class RageExpences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lostGames = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        //total = headset + mouse + keyboard + display
        int headsetBroken = 0;
        int mouseBroken = 0;
        int keyboardBroken = 0;
        int displayBroken = 0;
        for (int currentGame = 1; currentGame <= lostGames; currentGame++){

            if (currentGame % 2 == 0){
                headsetBroken++;
            }
            if (currentGame % 3 == 0){
                mouseBroken++;
            }
            if (currentGame % 6 == 0){
                keyboardBroken++;
            }
            if (currentGame % 12 == 0){
                displayBroken++;
            }

        }

        double rageCosts = (headsetBroken * headsetPrice) + (mouseBroken * mousePrice) + (keyboardBroken * keyboardPrice) + (displayBroken * displayPrice);

        System.out.printf("Rage expenses: %.2f lv.", rageCosts);
    }
}
