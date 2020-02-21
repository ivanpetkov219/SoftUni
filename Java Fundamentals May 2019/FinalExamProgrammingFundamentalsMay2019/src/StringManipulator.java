import java.util.Scanner;

public class StringManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();

        String input = scanner.nextLine();

        while (!input.equals("Done")) {

            String[] commands = input.split("\\s+");
            String action = commands[0];

            if (action.equals("Change")) {
                char charToReplace = commands[1].charAt(0);
                char newChar = commands[2].charAt(0);

                string = string.replace(charToReplace, newChar);

                System.out.println(string);

            } else if (action.equals("Includes")) {
                String checkString = commands[1];

                if (string.contains(checkString)) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
            } else if (action.equals("End")) {
                String compareString = commands[1];

                if (string.endsWith(compareString)) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
            } else if (action.equals("Uppercase")) {

                string = string.toUpperCase();

                System.out.println(string);

            } else if (action.equals("FindIndex")) {

                char charToFind = commands[1].charAt(0);

                for (int i = 0; i < string.length(); i++) {
                    char currentChar = string.charAt(i);

                    if (currentChar == charToFind) {
                        System.out.println(i);
                        break;
                    }
                }
            } else if (action.equals("Cut")) {
                int startIndex = Integer.parseInt(commands[1]);
                int lenght = Integer.parseInt(commands[2]);

                int endIndex = startIndex + lenght - 1;

                for (int i = startIndex; i <= endIndex; i++) {
                    char currentChar = string.charAt(i);
                    System.out.print(currentChar);
                }
                System.out.println();
            }
            input = scanner.nextLine();
        }
    }
}
