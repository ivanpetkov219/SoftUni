import java.util.Scanner;

public class TriplesOfLatinLetters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        int startChar = 97;
        int endChar = startChar + number;

        for (int i = startChar; i < endChar; i++ ){
            for (int j = startChar; j < endChar; j++){
                for (int k = startChar; k < endChar ; k++) {

                    System.out.printf("%c%c%c%n", i, j, k);
                }
            }
        }
    }
}
