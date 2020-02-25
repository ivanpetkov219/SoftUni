import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();


        String[] sizes = scanner.nextLine().split("\\s+");
        int firstSetSize = Integer.parseInt(sizes[0]);
        int secondSetSize = Integer.parseInt(sizes[1]);



        for (int i = 0; i < firstSetSize; i++) {
            int currentEntry = scanner.nextInt();
            firstSet.add(currentEntry);
        }

        for (int i = 0; i < secondSetSize; i++) {
            int currentEntry = scanner.nextInt();
            secondSet.add(currentEntry);
        }

        for (Integer number : firstSet) {
            if(secondSet.contains(number)){
                System.out.print(number + " ");
            }
        }
    }
}
