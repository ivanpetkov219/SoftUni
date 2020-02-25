import java.util.Scanner;

public class BookWorm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String initialString = scanner.nextLine();

        int size = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[size][size];

        int wormRow = -1;
        int wormCol = -1;

        for (int row = 0; row < size; row++) {
            String[] currentRow = scanner.nextLine().split("");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = currentRow[col];

                if (currentRow[col].charAt(0) == 'P') {
                    wormRow = row;
                    wormCol = col;
                }
            }
        }

        StringBuilder consumedChars = new StringBuilder();

        consumedChars.append(initialString);

        String command = scanner.nextLine();

        while (!command.equals("end")) {

            switch (command) {
                case "up":
                    wormRow--;
                    if (checkIfWormIsInMatrix(wormRow, wormCol, size)) {
                        matrix[wormRow + 1][wormCol] = "-";
                        moveUpWorm(wormRow, wormCol, matrix, consumedChars);
                    } else {
                        if (consumedChars.length() > 0) {
                            wormRow++;
                            consumedChars.deleteCharAt(consumedChars.length() - 1);
                        }
                    }
                    break;
                case "down":
                    wormRow++;
                    if (checkIfWormIsInMatrix(wormRow, wormCol, size)) {
                        matrix[wormRow - 1][wormCol] = "-";
                        moveDownWorm(wormRow, wormCol, matrix, consumedChars);
                    } else {
                        wormRow--;
                        if (consumedChars.length() > 0) {
                            consumedChars.deleteCharAt(consumedChars.length() - 1);
                        }
                    }
                    break;
                case "left":
                    wormCol--;
                    if (checkIfWormIsInMatrix(wormRow, wormCol, size)) {
                        matrix[wormRow][wormCol + 1] = "-";
                        moveLeftWorm(wormRow, wormCol, matrix, consumedChars);
                    } else {
                        wormCol++;
                        if (consumedChars.length() > 0) {
                            consumedChars.deleteCharAt(consumedChars.length() - 1);
                        }
                    }
                    break;
                case "right":
                    wormCol++;
                    if (checkIfWormIsInMatrix(wormRow, wormCol, size)) {
                        matrix[wormRow][wormCol - 1] = "-";
                        moveRightWorm(wormRow, wormCol, matrix, consumedChars);
                    } else {
                        wormCol--;
                        if (consumedChars.length() > 0) {
                            consumedChars.deleteCharAt(consumedChars.length() - 1);
                        }
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.println(consumedChars);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }

    }

    private static void moveUpWorm(int wormRow, int wormCol, String[][] matrix, StringBuilder sb) {

        String currentChar = matrix[wormRow][wormCol];
        if (Character.isLetter(currentChar.charAt(0))) {
            sb.append(currentChar);
            matrix[wormRow][wormCol] = "P";
        }
    }


    private static void moveDownWorm(int wormRow, int wormCol, String[][] matrix, StringBuilder sb) {


        String currentChar = matrix[wormRow][wormCol];
        if (Character.isLetter(currentChar.charAt(0))) {
            sb.append(currentChar);
            matrix[wormRow][wormCol] = "P";
        }

    }

    private static void moveLeftWorm(int wormRow, int wormCol, String[][] matrix, StringBuilder sb) {

        String currentChar = matrix[wormRow][wormCol];
        if (Character.isLetter(currentChar.charAt(0))) {
            sb.append(currentChar);
            matrix[wormRow][wormCol] = "P";
        }
    }


    private static void moveRightWorm(int wormRow, int wormCol, String[][] matrix, StringBuilder sb) {

        String currentChar = matrix[wormRow][wormCol];
        if (Character.isLetter(currentChar.charAt(0))) {
            sb.append(currentChar);
            matrix[wormRow][wormCol] = "P";
        }
    }


    private static boolean checkIfWormIsInMatrix(int wormRow, int wormCol, int size) {
        boolean isInMatrix = true;
        if (wormRow < 0 || wormRow >= size || wormCol < 0 || wormCol >= size) {
            isInMatrix = false;
        }
        return isInMatrix;
    }


}
