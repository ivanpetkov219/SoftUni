import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Integer> resources = new LinkedHashMap<>();

        String input = scanner.next();

        while (!input.equals("stop")){
            int value = scanner.nextInt();

            if(!resources.containsKey(input)){
                resources.put(input, value);
            }else {
                int currentValue = resources.get(input);
                resources.put(input, currentValue + value);
            }



            input = scanner.next();
        }

        for (Map.Entry<String, Integer> entry : resources.entrySet()) {
            System.out.println(String.format("%s -> %d", entry.getKey(), entry.getValue()));
        }

    }
}
