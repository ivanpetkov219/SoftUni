import java.util.Scanner;

public class Walking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalSteps = 0;

        while (totalSteps < 10000){
            String input = scanner.nextLine();

            if ("Going home".equals(input)) {
                int goingHomeSteps = Integer.parseInt(scanner.nextLine());
                totalSteps += goingHomeSteps;
                break;
            }
            int inputSteps = Integer.parseInt(input);
            totalSteps += inputSteps;
        }

        if (totalSteps >= 10000){
            System.out.println("Goal reached! Good job!");
        }else {
            int diff = 10000 - totalSteps;
            System.out.println(diff + " more steps to reach goal.");
        }

    }
}
