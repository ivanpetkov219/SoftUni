import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        executeAccordingToInput(reader, welcomeInfo);


    }

    private void executeAccordingToInput(BufferedReader reader, String welcomeInfo) throws IOException {
        String input = reader.readLine();
        while (!"q".equals(input)) {

            switch (input) {
                case "2":
                    System.out.println("Executing task 2...");
                    this.removeObjectsEx();
                    System.out.println("Task 2 completed!");
                    break;
                case "3":
                    try {
                        System.out.println("Executing task 3...");
                        this.containsEmployeeExc();
                        System.out.println("Task 3 completed!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.println("Executing task 4...");
                    this.getEmployeeWithSalaryOver50000Exc();
                    System.out.println("Task 4 completed!");
                    break;
                case "5":
                    System.out.println("Executing task 5...");
                    this.getEmployeeFromDepartmentsExc();
                    System.out.println("Task 5 completed!");
                    break;
                case "6":
                    System.out.println("Executing task 6...");
                    try {
                        this.addingNewAddressAndAddToEmployeeExc();
                        System.out.println("Task 6 completed!");
                    } catch (NoResultException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "7":
                    System.out.println("Executing task 7...");

                    this.findAddressesWithEmployeeCountExc();

                    System.out.println("Task 7 completed!");
                    break;
                case "8":
                    System.out.println("Executing task 8...");

                    try {
                        this.getEmployeeWithProjectExc();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    System.out.println("Task 8 completed!");
                    break;
                case "9":
                    System.out.println("Executing task 9...");

                    this.findLatest10ProjectsExc();

                    System.out.println("Task 9 completed!");
                    break;
                case "10":
                    System.out.println("Executing task 10...");

                    this.increaseSalariesExc();

                    System.out.println("Task 10 completed!");
                    break;
                case "11":
                    System.out.println("Executing task 11...");

                    this.removeTownsExc();

                    System.out.println("Task 11 completed!");
                    break;
                case "12":
                    System.out.println("Executing task 12...");

                    this.findEmployeesByFirstName();

                    System.out.println("Task 12 completed!");
                    break;
                case "13":
                    System.out.println("Executing task 13...");

                    this.employeesMaximumSalaries();

                    System.out.println("Task 13 completed!");
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

    private void employeesMaximumSalaries() {

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "JOIN e.department AS d " +
                        "GROUP BY e.department.name " +
                        "HAVING MAX (e.salary) NOT BETWEEN 30000 AND 70000", Employee.class).getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %.2f%n", e.getDepartment().getName(), e.getSalary());
                });

    }

    private void findEmployeesByFirstName() throws IOException {
        System.out.println("Please enter first name pattern:");
        String pattern = reader.readLine();
        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%")
                .getResultList();

        employees.stream().forEach(e -> {
            System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary());
        });
    }

    private void removeTownsExc() throws IOException {

        System.out.println("Enter town name:");
        String townName = reader.readLine();

        Town town = this.entityManager.createQuery("SELECT t FROM Town AS t " +
                "WHERE t.name = :name", Town.class)
                .setParameter("name", townName).getSingleResult();

        List<Address> addresses = this.entityManager.createQuery("SELECT a FROM Address AS a WHERE a.town.name = :name", Address.class)
                .setParameter("name", townName).getResultList();

        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.address IN(:addresses)", Employee.class).setParameter("addresses", addresses).getResultList();

        this.entityManager.getTransaction().begin();

        employees.stream().forEach(e -> e.setAddress(null));

        addresses.stream().forEach(this.entityManager::remove);
        this.entityManager.remove(town);


        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

        System.out.printf("%d " + (addresses.size() == 1 ? "address" : "addresses") + " in %s deleted%n", addresses.size(), townName);


    }

    private void increaseSalariesExc() {
        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', " +
                "'Information Services')", Employee.class).getResultList();

        this.entityManager.getTransaction().begin();
        employees.stream().forEach(this.entityManager::detach);

        employees.stream().forEach(employee -> {
            employee.setSalary(employee.getSalary().multiply(new BigDecimal(1.12)));
        });

        employees.stream().forEach(e -> {
            System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
        });

        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }

    private void findLatest10ProjectsExc() {
        this.entityManager.createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class).setMaxResults(10)
                .getResultList().stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("Project name: %s\n" +
                            " \tProject Description: %s\n" +
                            " \tProject Start Date:%s\n" +
                            " \tProject End Date: %s\n", p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate());
                });

    }

    private void getEmployeeWithProjectExc() throws IOException {
        System.out.println("Enter employee id:");
        int empId = Integer.parseInt(reader.readLine());

        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class)
                .setParameter("id", empId).getSingleResult();

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(p -> {
            System.out.printf("\t%s%n", p.getName());
        });


    }

    private void findAddressesWithEmployeeCountExc() {
        this.entityManager.createQuery("SELECT a FROM Address AS a ORDER BY a.employees.size DESC", Address.class)
                .getResultList().stream().limit(10).forEach(a -> {
            System.out.printf("%s, %s - %d employees%n", a.getText(), a.getTown().getName(), a.getEmployees().size());
        });
    }

    private void removeObjectsEx() {

        List<Town> towns = this.entityManager.createQuery("SELECT t FROM Town AS t " +
                "WHERE length(t.name) > 5", Town.class).getResultList();

        this.entityManager.getTransaction().begin();

        towns.forEach(this.entityManager::detach);

        towns.forEach(t -> t.setName(t.getName().toLowerCase()));

        towns.forEach(this.entityManager::merge);

        this.entityManager.flush();

        this.entityManager.getTransaction().commit();

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

    } //


    private void getEmployeeWithSalaryOver50000Exc() {
        this.entityManager
                .createQuery("select e From Employee AS e where e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s%n", e.getFirstName()));
    }

    private void getEmployeeFromDepartmentsExc() {

        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "where e.department.name = 'Research and Development' " +
                        "ORDER BY e.salary, e.id ", Employee.class)
                .getResultList();

        employees.stream().forEach(e -> {
            System.out.printf("%s %s from Research and Development - $%.2f%n", e.getFirstName(),
                    e.getLastName(), e.getSalary());
        });
    }

    private void addingNewAddressAndAddToEmployeeExc() throws IOException {
        System.out.println("Enter employee last name:");
        String lastName = reader.readLine();

        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "where e.lastName = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();

        Address address = this.createAddress("Vitoshka 15");

        this.entityManager.getTransaction().begin();
        this.entityManager.detach(employee);
        employee.setAddress(address);
        this.entityManager.merge(employee);

        this.entityManager.flush();
        this.entityManager.getTransaction().commit();


    }

    private Address createAddress(String text) {
        Address address = new Address();
        address.setText(text);
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();


        return address;

    }


}
