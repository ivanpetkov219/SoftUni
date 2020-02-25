import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            String[] input = scanner.nextLine().split(" ");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = Integer.parseInt(input[col]);
            }
        }
        int maxSum = Integer.MIN_VALUE;

        int row = 1;
        int column = 1;

        int maxRow = 0;
        int maxColumn = 0;

        for (int r = row; r < matrix.length - 1 ; r++) {
            for (int c = column; c < matrix[r].length - 1 ; c++) {
                int sum = getSum(matrix, r, c);

                if(sum > maxSum){
                    maxSum = sum;
                    maxRow = r;
                    maxColumn = c;

                }
            }
        }
        System.out.println("Sum = " + maxSum);

        printMaxMatrix(matrix, maxRow, maxColumn);
    }

    private static void printMaxMatrix(int[][] matrix, int maxRow, int maxColumn) {
        maxRow--;
        maxColumn--;

        for (int i = maxRow; i < maxRow + 3; i++) {
            for (int j = maxColumn; j < maxColumn + 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static int getSum(int[][] matrix, int r, int c) {
        int sum = 0;
        r--;
        c--;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3 ; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
