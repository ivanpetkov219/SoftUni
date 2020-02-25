import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] rowsAndColumns = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(rowsAndColumns[0]);
        int columns = Integer.parseInt(rowsAndColumns[1]);

       List<List<String>> matrix = new ArrayList<>();


        while (rows > 0){

            matrix.add(Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList()));
            rows--;
        }

   //    for (int row = 0; row < matrix.size(); row++) {
   //        String[] matrixData = scanner.nextLine().split(" ");
   //        for (int column = 0; column < matrix.; column++) {
   //            matrix.get(row).set(column, matrixData[column]);
   //        }
   //    }

        String input = scanner.nextLine();

        while (!input.equals("END")){
            String[] tokens = input.split(" ");
            String command = tokens[0];

            if (command.equals("swap") && tokens.length == 5) {
                int row1 = Integer.parseInt(tokens[1]);
                int col1 = Integer.parseInt(tokens[2]);
                int row2 = Integer.parseInt(tokens[3]);
                int col2 = Integer.parseInt(tokens[4]);

                if(row1 <= rows  && row2 <= rows && col1 <= columns && col2 <= columns) {

                    String tempString = matrix.get(row1).get(col1);

                    matrix.get(row1).set(col1, matrix.get(row2).get(col2));
                    matrix.get(row2).set(col2, tempString);

                    System.out.println();

                    printMatrix(matrix);
                }


            }else {
                System.out.println("Invalid input!");
            }
            input = scanner.nextLine();
        }



    }

    private static void printMatrix(List<List<String>> matrix) {

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }
}
