import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            String[] input = scanner.nextLine().split(" ");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = Integer.parseInt(input[col]);
            }
        }

        int primaryDiagonalSum = 0;

        for (int row = 0; row < matrix.length; row++) {
            primaryDiagonalSum += matrix[row][row];
        }

        int secondaryDiagonalSum = 0;
        int col = 0;
        for (int row = matrix.length - 1; row >= 0; row--) {

                secondaryDiagonalSum += matrix[row][col];
                col++;

        }

        int diff = Math.abs(primaryDiagonalSum - secondaryDiagonalSum);

        System.out.println(diff);

    }
}
