import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> resources = new LinkedHashMap<>();



        String input = scanner.nextLine();

        while (!input.equals("stop")){

                int quantity = Integer.parseInt(scanner.nextLine());
                resources.putIfAbsent(input, 0);
                resources.put(input, resources.get(input) + quantity);


            input = scanner.nextLine();

        }

        for (String resource : resources.keySet()) {
            System.out.printf("%s -> %d%n", resource, resources.get(resource));
        }
    }
}
