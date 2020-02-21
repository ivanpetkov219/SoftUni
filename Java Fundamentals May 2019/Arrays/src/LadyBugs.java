import java.math.BigDecimal;
import java.util.Scanner;

public class LadyBugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        short sizeOfField = Short.parseShort(scanner.nextLine());
        int[] field = new int[sizeOfField];

        String[] initalIndexesArray = scanner.nextLine().split("\\s+");

        for (String index : initalIndexesArray) {
            int i = Integer.parseInt(index);
            if (i >= 0 && i < field.length) {
                field[i] = 1;
            }
        }

        String input = scanner.nextLine();
        while (!"end".equals(input)) {

            String[] actions = input.split("\\s+");

            int actionIndex = Integer.parseInt(actions[0]);
            String direction = actions[1];
            int flyLenght = Integer.parseInt(actions[2]);



            if (actionIndex >= 0 && actionIndex < field.length && field[actionIndex] != 0) {
                field[actionIndex] = 0;
                int targetIndex;

                switch (direction) {
                    case "right":
                        targetIndex = actionIndex + flyLenght;

                        if (targetIndex >= 0 && targetIndex < field.length) {
                            while (field[targetIndex] == 1) {
                                if (targetIndex < 0 || targetIndex >= field.length) {
                                    break;
                                }
                                if (flyLenght >= 0){
                                targetIndex++;
                                }else
                                    targetIndex--;
                            }

                                field[targetIndex] = 1;

                        }
                        break;
                    case "left":
                        targetIndex = actionIndex - flyLenght;

                        if (targetIndex >= 0 && targetIndex < field.length) {

                            while (field[targetIndex] == 1) {
                                if (targetIndex < 0 || targetIndex >= field.length) {
                                    continue;
                                }
                                if (flyLenght >= 0){
                                    targetIndex--;
                                }else
                                    targetIndex++;
                            }

                                field[targetIndex] = 1;

                        }
                        break;
                }
            }
            input = scanner.nextLine();
        }
        for (int index : field) {
            System.out.print(index + " ");
        }
    }
}
