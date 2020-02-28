

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
        String welcomeInfo = "******************************************************\n" +
                "Please enter the number of the task you wish to execute:\n" +
                "Type 2 for Assignment #2 \"Remove Objects\",\n" +
                "Type 3 for Assignment #3 \"Contains Employee\",\n" +
                "Type 4 for Assignment #4 \"Employees with Salary Over 50 000\",\n" +
                "Type 5 for Assignment #5 \"Employees from Department\",\n" +
                "Type 6 for Assignment #6 \"Adding a New Address and Updating Employee\",\n" +
                "Type q to exit.";
        System.out.println(welcomeInfo);

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
