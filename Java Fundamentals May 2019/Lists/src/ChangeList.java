import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] command = input.split("\\s+");
            int element = Integer.parseInt(command[1]);
            if (command[0].equals("Delete")) {

                for (int i = 0; i < numbers.size(); i++) {
                    if(numbers.get(i) != -1) {
                        if (numbers.get(i).equals(element)) {
                            numbers.remove(Integer.valueOf(element));
                            i--;
                        }
                    }
                }
            } else if (command[0].equals("Insert")) {
                int position = Integer.parseInt(command[2]);
                if (position >= 0 && position < numbers.size()) {
                    numbers.add(position, element);
                }
            }
            input = scanner.nextLine();
        }

        for (Integer index : numbers) {
            System.out.print(index + " ");
        }
    }
}
