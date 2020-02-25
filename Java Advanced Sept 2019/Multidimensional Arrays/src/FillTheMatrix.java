import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        int matrixSize = Integer.parseInt(input[0]);
        String pattern = input[1];

        int[][] matrix = new int[matrixSize][matrixSize];

        int number = 1;

        switch (pattern) {
            case "A":
                for (int col = 0; col < matrixSize; col++) {
                    for (int row = 0; row < matrixSize; row++) {
                        matrix[row][col] = number++;
                    }
                }
                break;
            case "B":
                int counter = 0;

                for (int col = 0; col < matrixSize; col++) {
                    if (col % 2 == 0) {
                        for (int row = 0; row < matrixSize; row++) {
                            matrix[row][col] = number++;
                        }
                    } else {
                        for (int row = matrixSize -1; row >= 0; row--) {
                            matrix[row][col] = number++;
                        }
                    }
                }

                break;
        }

        print(matrix);

    }

    private static void print(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
