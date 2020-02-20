import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter array size:");

        int arraySize = Integer.parseInt(scanner.nextLine());

        String[] array = new String[arraySize];

        for (int currentIndex = 0; currentIndex < arraySize ; currentIndex++) {
            System.out.printf("Enter element for index %d:%n", currentIndex);
            array[currentIndex] = scanner.nextLine();

        }

        for (int index = 0; index < array.length ; index++) {
            System.out.printf("Element[%d] = %s%n", index, array[index]);

        }
    }
}
