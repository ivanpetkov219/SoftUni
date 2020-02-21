import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> timeOfTasks = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] command = input.split("\\s+");

            String action = command[0];

            if (action.equals("Complete")) {
                int index = Integer.parseInt(command[1]);

                completeTask(timeOfTasks, index);

            } else if (action.equals("Change")) {
                int index = Integer.parseInt(command[1]);
                int time = Integer.parseInt(command[2]);

                changeTimeOfTask(timeOfTasks, index, time);

            } else if (action.equals("Drop")) {
                int index = Integer.parseInt(command[1]);

                dropTask(timeOfTasks, index);

            } else if (action.equals("Count")) {
                String secondAction = command[1];
                if (secondAction.equals("Completed")) {

                    countAndPrintCompletedTasks(timeOfTasks);

                } else if (secondAction.equals("Incomplete")) {

                    countAndPrintIncompleteTasks(timeOfTasks);


                } else if (secondAction.equals("Dropped")) {

                    countAndPrintDroppedTasks(timeOfTasks);

                }
            }
            input = scanner.nextLine();
        }

        for (Integer index : timeOfTasks) {
            if (index != 0 && index != -1 && index >= -1 && index <= 5) {
                System.out.print(index + " ");
            }
        }
    }

    private static void countAndPrintDroppedTasks(List<Integer> timeOfTasks) {
        int counter = 0;
        for (Integer index : timeOfTasks) {
            if (index == -1) {
                counter++;
            }

        }

        System.out.println(counter);
    }

    private static void countAndPrintIncompleteTasks(List<Integer> timeOfTasks) {
        int counter = 0;
        for (Integer index : timeOfTasks) {
            if (index != 0 && index != -1) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void countAndPrintCompletedTasks(List<Integer> timeOfTasks) {
        int counter = 0;
        for (Integer index : timeOfTasks) {
            if (index == 0) {
                counter++;
            }

        }
        System.out.println(counter);
    }

    private static void dropTask(List<Integer> timeOfTasks, int index) {
        if (isValidIndex(timeOfTasks, index)) {
            timeOfTasks.set(index, -1);
        }
    }

    private static void changeTimeOfTask(List<Integer> timeOfTasks, int index, int time) {
        if (isValidIndex(timeOfTasks, index)) {
            timeOfTasks.set(index, time);
        }

    }

    private static void completeTask(List<Integer> timeOfTasks, int index) {

        if (isValidIndex(timeOfTasks, index)) {
            timeOfTasks.set(index, 0);
        }
    }

    private static boolean isValidIndex(List<Integer> timeOfTasks, int index) {
        boolean isValidIndex = false;
        if (index >= 0 && index < timeOfTasks.size()) {
            isValidIndex = true;
        }
        return isValidIndex;
    }

}
