import entities.Employee;

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
                "Type 7 for Assignment #7 \"Addresses with Employee Count\",\n" +
                "Type 8 for Assignment #8 \"Get Employee with Project\",\n" +
                "Type 9 for Assignment #9 \"Find Latest 10 Projects\",\n" +
                "Type 10 for Assignment #10 \"Increase Salaries\",\n" +
                "Type 11 for Assignment #11 \"Remove Towns\",\n" +
                "Type 12 for Assignment #12 \"Find Employees by First Name\",\n" +
                "Type 13 for Assignment #13 \"Employees Maximum Salaries\",\n" +
                "Type q to exit.";
        System.out.println(welcomeInfo);

        String input = reader.readLine();
        while (!input.equals("q")) {

            switch (input) {
                case "2":
                    this.removeObjectsEx();
                    break;
                case "3":
                    try {
                        this.containsEmployeeExc();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
//                this.getEmployeeWithSalaryOver50000();
                    break;
                case "5":
                this.getEmployeeFromDepartmentsExc();
                    break;
                case "6":
                try {
                    this.addingNewAddressAndAddToEmployee();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
                case "13":
                    break;
                default:
                    System.out.printf("Input must be a number between 2 and 13...%nPlease try again!");
                    break;

            }

            System.out.println();
            System.out.println("*****************************");
            System.out.flush();
            System.out.println(welcomeInfo);
            input = reader.readLine();
        }


    }

    private void removeObjectsEx() {

    }

    private void containsEmployeeExc() throws IOException {
        System.out.println("Enter employee full name:");
        String fullName = reader.readLine();

        try {
            Employee employee = this.entityManager.createQuery("select e from Employee AS e " +
                    "where concat(e.firstName, ' ', e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName)
                    .getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException nre) {
            System.out.println("No");
        }

    }


    private void getEmployeeWithSalaryOver50000Exc() {
        this.entityManager
                .createQuery("select e From Employee AS e where e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s%n", e.getFirstName()));
    }

    private void getEmployeeFromDepartmentsExc() {

        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "where e.department.name = 'Research and Development'", Employee.class)
                .getResultList();

        //TODO PRINT F
    }

    private void addingNewAddressAndAddToEmployee() throws IOException {
        System.out.println("Enter employee last name:");
        String lastName = reader.readLine();

        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "where e.lastName = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();


    }


}
