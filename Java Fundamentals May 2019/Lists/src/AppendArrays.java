import java.util.*;
import java.util.stream.Collectors;

public class AppendArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> numbersWithSpaces = Arrays.stream(scanner.nextLine().split("\\|+")).collect(Collectors.toList());
        List<Integer> finalList = new ArrayList<>();


       Collections.reverse(numbersWithSpaces);

        String result = String.join(" ", numbersWithSpaces);

        while (result.contains(" ")){
            result = result.replace(" ", "");
        }


        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i) + " ");
        }

    }
}
