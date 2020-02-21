import java.util.Scanner;

public class NxNMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        getMatrix(number);

    }

    static void getMatrix(int a){
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a ; j++) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
