import java.util.Scanner;

public class Cake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cakeLenght = Integer.parseInt(scanner.nextLine());
        int cakeWidth = Integer.parseInt(scanner.nextLine());

        int cakeVolume = cakeLenght * cakeWidth;
        int onePieceVolume = 1;
        int totalPieces = 0;

        String input = scanner.nextLine();

        while (!"STOP".equals(input)){
            int numberPieces = Integer.parseInt(input);
            totalPieces += numberPieces;

            if (totalPieces > cakeVolume){
                int needed = totalPieces - cakeVolume;
                System.out.printf("No more cake left! You need %d pieces more.", needed);
                break;
            }
            input = scanner.nextLine();
        }
        if (cakeVolume >= totalPieces) {
            int left = cakeVolume - totalPieces;
            System.out.printf("%d pieces are left.", left);
        }
    }
}
