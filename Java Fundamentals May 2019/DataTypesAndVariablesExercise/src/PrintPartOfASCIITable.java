import java.text.CharacterIterator;
import java.util.Scanner;

public class PrintPartOfASCIITable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startIndex = scanner.nextInt();
        int ednIndex = scanner.nextInt();

        for (char i = (char) startIndex; i <= ednIndex; i++ ){

            System.out.print((char) i + " ");
        }
    }
}
