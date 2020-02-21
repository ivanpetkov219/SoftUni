import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LastStop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("END")){
            String[] command = input.split("\\s+");
            String action = command[0];

            if (action.equals("Change")){
                int paintingNumber = Integer.parseInt(command[1]);
                int changedNumber = Integer.parseInt(command[2]);

                changeNumber(numbers, paintingNumber, changedNumber);

            }else if (action.equals("Hide")){
                int paintingNumber = Integer.parseInt(command[1]);

                hideNumber(numbers, paintingNumber);

            }else if (action.equals("Switch")){
                int firstNumber = Integer.parseInt(command[1]);
                int secondNumber = Integer.parseInt(command[2]);

                    switchNumbers(numbers, firstNumber, secondNumber );

            }else if (action.equals("Insert")){
                int place = Integer.parseInt(command[1]);
                int paintingNumber = Integer.parseInt(command[2]);

                insertNumber(numbers, place, paintingNumber);

            }else if (action.equals("Reverse")){

                reverseList(numbers);

            }
            input = scanner.nextLine();
        }

        for (Integer index: numbers ) {
            System.out.print(index + " ");
        }
    }

    public static void changeNumber(List<Integer> numbers, int paintingNumber, int changedNumber) {
      int index = numbers.indexOf(paintingNumber);

      if(index != -1){
          numbers.set(index, changedNumber);
      }

    }
    public  static void hideNumber(List<Integer> numbers, int hideNumber){
        for (int i = 0; i < numbers.size(); i++) {
            int currentNumber = numbers.get(i);
            if(currentNumber == hideNumber){
                numbers.remove(i);
                i--;
            }
        }
    }
    public static void switchNumbers (List<Integer> numbers, int numberOne, int numberTwo){
        int indexNumber1 = numbers.indexOf(numberOne);
        int indexNumber2 = numbers.indexOf(numberTwo);

        if(indexNumber1 != -1 && indexNumber2 != -1) {
            numbers.set(indexNumber1, numberTwo);
            numbers.set(indexNumber2, numberOne);
        }

    }
    public static void insertNumber (List<Integer> numbers, int index, int number){

            if (index >= 0 && index < numbers.size() -2){
                numbers.add(index + 1, number);
            }
    }

    public static void reverseList (List<Integer> numbers){

        Collections.reverse(numbers);
    }

}
