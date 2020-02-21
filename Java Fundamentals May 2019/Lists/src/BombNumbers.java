import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        byte bombNumber = scanner.nextByte();
        byte power = scanner.nextByte();
        int powerRange = power * 2 + 1;


        for (int i = 0; i < numbers.size() ; i++) {
            int counter = 0;
            int currentDigit = numbers.get(i);
            if (currentDigit == bombNumber){
                for (int j = i - power; j <=  i + power ; j++) {
                    if(j >= 0 && j < numbers.size()) {
                        numbers.remove(j);
                        counter++;
                        j--;
                        if (counter == powerRange){
                            break;
                        }
                    }
                }
                i = -1;

            }
        }

        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }

        System.out.println(sum);

    }
}
