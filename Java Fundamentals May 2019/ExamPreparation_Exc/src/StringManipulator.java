import java.util.Scanner;

public class StringManipulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String result = "";

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] commands = input.split("\\s+");

            String command = commands[0];
            if (command.equals("Add")) {

                result += commands[1];

            } else if (command.equals("Upgrade")) {

                char currentChar = commands[1].charAt(0);

                result = result.replace(currentChar, (char) (currentChar + 1));

            } else if (command.equals("Print")) {

                System.out.println(result);

            } else if (command.equals("Index")) {

                char currentChar = commands[1].charAt(0);

                boolean indexIsPresent = false;
                for (int i = 0; i < result.length(); i++) {
                    if (currentChar == result.charAt(i)) {
                        indexIsPresent = true;
                        System.out.print(i + " ");
                    }
                    if (i == result.length() - 1 && !indexIsPresent) {
                        System.out.println("None");
                    }
                }
                System.out.println();
            } else if (command.equals("Remove")) {

                result = result.replaceAll(commands[1], "");

            }
            input = scanner.nextLine();
        }
    }
}
