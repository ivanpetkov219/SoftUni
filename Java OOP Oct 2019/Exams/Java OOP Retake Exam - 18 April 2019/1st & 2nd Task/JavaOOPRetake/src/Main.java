import core.ManagerControllerImpl;
import core.interfaces.ManagerController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ManagerController managerController = new ManagerControllerImpl();

        String[] input = scanner.nextLine().split("\\s+");



        while (!input[0].equals("Exit")) {
            String command = input[0];

            switch (command) {
                case "AddPlayer":
                    System.out.println(managerController.addPlayer(input[1], input[2]));
                    break;
                case "AddCard":
                    System.out.println(managerController.addCard(input[1], input[2]));
                    break;
                case "AddPlayerCard":
                    System.out.println(managerController.addPlayerCard(input[1], input[2]));
                    break;
                case "Fight":
                    System.out.println(managerController.fight(input[1], input[2]));
                    break;
                case "Report":
                    System.out.println(managerController.report());
                    break;
            }
            input = scanner.nextLine().split("\\s+");
        }


    }
}
