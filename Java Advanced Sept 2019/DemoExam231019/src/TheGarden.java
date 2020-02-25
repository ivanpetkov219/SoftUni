import java.awt.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TheGarden {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());

        String[][] garden = new String[rows][];

        Map<String, Integer> results = new LinkedHashMap<>();

        results.put("C", 0);
        results.put("P", 0);
        results.put("L", 0);
        results.put("Harmed Vegetables", 0);


        readMatrix(garden, scanner, rows);

        String input = scanner.nextLine();

        while (!input.equals("End of Harvest")) {

            String[] tokens = input.split("\\s+");

            String command = tokens[0];
            int row = Integer.parseInt(tokens[1]);
            int col = Integer.parseInt(tokens[2]);

            if (validateCoordinates(row, col, garden)) {
                if (command.equals("Harvest")) {

                    harvestVegetable(results, garden, row, col);

                } else if (command.equals("Mole")) {

                    String direction = tokens[3];

                    harmVegetables(garden, row, col, direction, results);

                }
            }
            input = scanner.nextLine();
        }

        printGarden(garden);

        System.out.println("Carrots: " + results.get("C"));
        System.out.println("Potatoes: " + results.get("P"));
        System.out.println("Lettuce: " + results.get("L"));
        System.out.printf("Harmed vegetables: %d", results.get("Harmed Vegetables"));






    }

    private static void printGarden(String[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void harmVegetables(String[][] matrix, int row, int col, String direction, Map<String, Integer> result) {

        switch (direction){
            case "up":
                while (row >= 0 && row < matrix.length){
                    if(!matrix[row][col].equals(" ")) {
                        matrix[row][col] = " ";
                        result.put("Harmed Vegetables", result.get("Harmed Vegetables") + 1);
                    }
                        row -= 2;
                }
                break;
            case "down":
                while (row >= 0 && row < matrix.length){
                    if(!matrix[row][col].equals(" ")) {
                        matrix[row][col] = " ";
                        result.put("Harmed Vegetables", result.get("Harmed Vegetables") + 1);
                    }
                    row += 2;
                }
                break;
            case "left":
                while (col >= 0 && col < matrix[row].length){
                    if(!matrix[row][col].equals(" ")) {
                        matrix[row][col] = " ";
                        result.put("Harmed Vegetables", result.get("Harmed Vegetables") + 1);
                    }
                    col -= 2;
                }
                break;
            case "right":
                while (col >= 0 && col < matrix[row].length){
                    if(!matrix[row][col].equals(" ")) {
                        matrix[row][col] = " ";
                        result.put("Harmed Vegetables", result.get("Harmed Vegetables") + 1);
                    }
                    col += 2;
                }
                break;
        }

    }

    private static boolean validateCoordinates(int row, int col, String[][] matrix) {
        boolean isValidCoordinates = false;
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length){
            isValidCoordinates = true;
        }

        return isValidCoordinates;
    }

    private static void harvestVegetable(Map<String, Integer> map, String[][] matrix, int row, int col) {

        String symbol = matrix[row][col];

        switch (symbol){
            case "C":
                map.put(symbol, map.get(symbol) + 1);
                break;
            case "P":
                map.put(symbol + "", map.get(symbol) + 1);
                break;
            case "L":
                map.put(symbol + "", map.get(symbol) + 1);
                break;
            default:
                break;
        }

        matrix[row][col] = " ";

    }

    private static void readMatrix(String[][] matrix, Scanner scanner, int rows) {

        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("\\s+");
        }
    }


}
