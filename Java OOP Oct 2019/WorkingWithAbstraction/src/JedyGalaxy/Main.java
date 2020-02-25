package JedyGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] dimensions = IntegerParser.parseIntegerArray(scanner.nextLine());


        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] galaxy = new int[rows][cols];

        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                galaxy[i][j] = value++;
            }
        }

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] playerStartPosition = IntegerParser.parseIntegerArray(command);

            int[] evil = IntegerParser.parseIntegerArray(scanner.nextLine());

            int evilXPosition = evil[0];
            int evilYPosition = evil[1];

            while (evilXPosition >= 0 && evilYPosition >= 0) {
                if (evilXPosition >= 0 && evilXPosition < galaxy.length && evilYPosition >= 0 && evilYPosition < galaxy[0].length) {
                    galaxy[evilXPosition][evilYPosition] = 0;
                }
                evilXPosition--;
                evilYPosition--;
            }

            int playerXPosition = playerStartPosition[0];
            int playerYPosition = playerStartPosition[1];

            while (playerXPosition >= 0 && playerYPosition < galaxy[1].length) {
                if (playerXPosition >= 0 && playerXPosition < galaxy.length && playerYPosition >= 0 && playerYPosition < galaxy[0].length) {
                    sum += galaxy[playerXPosition][playerYPosition];
                }

                playerYPosition++;
                playerXPosition--;
            }

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }
}

