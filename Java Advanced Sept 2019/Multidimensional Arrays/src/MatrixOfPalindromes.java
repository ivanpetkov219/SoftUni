import java.util.Scanner;

public class MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        char[][] matrix = new char[rows][cols];

        char rowStartLetter = 'a';
        char colStartLetter = 'a';




        for (int row = 0; row < matrix.length; row++) {
            if (row != 0){
                rowStartLetter++;
                colStartLetter = rowStartLetter;
            }
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%c%c%c ", rowStartLetter, colStartLetter++, rowStartLetter);
            }
            System.out.println();

        }
       print(matrix);

    }

    private static void print(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
