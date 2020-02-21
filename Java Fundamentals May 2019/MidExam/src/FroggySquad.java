import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FroggySquad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> names = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        String input = scanner.next();

        while (!input.equals("Print")){

            String action = input;
            if(action.equals("Join")){
                String name = scanner.next();
                names.add(name);
            }else if(action.equals("Jump")){
                String name = scanner.next();
                int index = scanner.nextInt();

                jumpFrog(names, index, name);


            }else if(action.equals("Dive")){
                int index = scanner.nextInt();

                if(isValidIndex(names, index)){
                    names.remove(index);
                }

            }else if(action.equals("First")){
                int count = scanner.nextInt();

                getFirstNames(names, count);

            }else if(action.equals("Last")){
                int count = scanner.nextInt();

                getLastNames(names, count);

            }

            input = scanner.next();
        }

        String printOrder = scanner.next();

        printNames(names,printOrder);
    }

    private static void getLastNames(List<String> names, int count) {
        if (count > names.size()){
            for (String name: names) {
                System.out.print(name + " ");
            }
            System.out.println();

        }else if(count >= 0 && count <= names.size()){
            for (int i = names.size() - count; i < names.size() ; i++) {
                System.out.print(names.get(i) + " ");
            }
            System.out.println();
        }

    }

    private static void getFirstNames(List<String> names, int count) {
        if (count > names.size()){
            for (String name: names) {
                System.out.print(name + " ");
            }
            System.out.println();

        }else if (count >= 0 && count <= names.size()){
            for (int i = 0; i < count ; i++) {
                System.out.print(names.get(i) + " ");
            }
            System.out.println();
        }
    }


    private static void jumpFrog(List<String> names, int index, String name) {
        if(isValidIndex(names, index)){
            names.add(index, name);
        }

    }

    private static boolean isValidIndex(List<String> names, int index) {
        boolean isValidIndex = false;

        if (index >= 0 && index < names.size()){
            isValidIndex = true;
        }

        return isValidIndex;
    }

    private static void printNames(List<String> names, String printOrder) {

        System.out.print("Frogs:");
        if (printOrder.equals("Normal")){
            for (String name: names) {
                System.out.print(" " + name);
            }

        }else if (printOrder.equals("Reversed")){
            Collections.reverse(names);
            for (String name: names) {
                System.out.print(" " + name);
            }

        }
    }
}
