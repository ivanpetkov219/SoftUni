import java.util.Scanner;

public class CharactersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char firstChar = scanner.nextLine().charAt(0);
        char secondChar = scanner.nextLine().charAt(0);

        getCharSequence(firstChar, secondChar);

    }

    static void getCharSequence(char a, char b){

        if(a < b) {
            for (int i = a + 1; i < b; i++) {
                System.out.print((char) i + " ");
            }
        }else {
            for (int i = b + 1; i < a; i++) {
                System.out.print((char) i + " ");
            }
        }
    }
}
