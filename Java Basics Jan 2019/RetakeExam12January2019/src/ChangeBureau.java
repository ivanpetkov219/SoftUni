import java.util.Scanner;

public class ChangeBureau {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bitcoin = Integer.parseInt(scanner.nextLine());
        double chineese = Double.parseDouble(scanner.nextLine());
        double commission = Double.parseDouble(scanner.nextLine());

        double bitcoinInBGN = bitcoin * 1168;
        double chineeseInBGN = (chineese * 0.15) * 1.76;

        double totalInEUR = (bitcoinInBGN + chineeseInBGN) / 1.95;

        totalInEUR -= totalInEUR * (commission / 100);

        System.out.printf("%.2f", totalInEUR);


    }
}
