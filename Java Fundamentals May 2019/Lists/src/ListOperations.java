import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("End")){
            String[] command = input.split("\\s+");
            String operation = command[0];
                if(operation.equals("Add")) {
                    int element = Integer.parseInt(command[1]);
                    addNumberToList(numbers, element);
                }else if(operation.equals("Insert")) {
                    int element = Integer.parseInt(command[1]);
                    int index = Integer.parseInt(command[2]);
                    if(index >= 0 && index < numbers.size()){
                        numbers.add(index, element);
                    }else {
                        System.out.println("Invalid index");
                    }

                }else if (operation.equals("Remove")){
                    int index = Integer.parseInt(command[1]);
                    if(index >= 0 && index < numbers.size()){
                        numbers.remove(index);
                    }else {
                        System.out.println("Invalid index");
                    }
                }else if (operation.equals("Shift")){
                    String directionOfShifting = command[1];
                    int countsToShift = Integer.parseInt(command[2]);

                    shiftLeftOrRight(numbers, directionOfShifting, countsToShift);
                }
            input = scanner.nextLine();
        }
       for (Integer index : numbers) {
           System.out.print(index + " ");
       }
    }

    static void addNumberToList (List<Integer> list, int number){

        list.add(number);
    }
    static  void shiftLeftOrRight (List<Integer> list, String direction, int shifts){
        if (direction.equals("left")){
            for (int i = 1; i <= shifts ; i++) {
                int tempIndex = list.get(0);
                list.remove(0);
                list.add(tempIndex);
            }
        }else {
            for (int i = 1; i <= shifts ; i++) {
                int tempIndex = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(0, tempIndex);
            }
        }

    }
}
