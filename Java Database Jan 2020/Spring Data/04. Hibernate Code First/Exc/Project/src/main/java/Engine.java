

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Engine {

    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        String welcomeJava = "**************** JAVA RULZZZ! ***********************\n" +
                "";
//        String welcomeInfo = "Please enter the number of the task you wish to execute:\\n\" +\n" +
//                "                \"Type 1 for Assignment #1 \\\"Gringotts Database\\\",\\n\" +\n" +
//                "                \"Type 2 for Assignment #2 \\\"Sales Database\\\",\\n\" +\n" +
//                "                \"Type 3 for Assignment #3 \\\"University System\\\",\\n\" +\n" +
//                "                \"Type 4 for Assignment #4 \\\"Hospital Database\\\",\\n\" +\n" +
//                "                \"Type 5 for Assignment #5 \\\"Bills Payment System\\\",\\n\" +\n" +
//                "                \"Type 6 for Assignment #6 \\\"Football Betting Database\\\",\\n\" +\n" +
//                "                \"Type q to exit.";
        System.out.println();
        System.out.println(welcomeJava);
//        System.out.println(welcomeInfo);

//        processInput(welcomeInfo);
        System.out.println("Schema is created!");

//        System.out.println("Terminating application!");
    }

    private void processInput(String welcomeInfo) throws IOException {

        //this method has nothing to do with this homework

        String input = reader.readLine();
        while (!input.equals("q")) {

            switch (input) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                default:
                    System.out.printf("Input must be a number between 1 and 6...%nPlease try again!");
                    break;

            }

            System.out.println();
            System.out.println("*****************************");
            System.out.flush();
            System.out.println(welcomeInfo);
            input = reader.readLine();
        }
    }
}
