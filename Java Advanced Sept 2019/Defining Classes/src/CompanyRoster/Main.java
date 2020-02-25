package CompanyRoster;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Employee> departments = new HashMap<>();

        int lines = Integer.parseInt(scanner.nextLine());

        while (lines-- >0){

            String[] input = scanner.nextLine().split("\\s+");
            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String currentDepartment = input[3];
            String email = "n/a";
            int age = -1;

            if(input.length >= 5){
                if(input[4].length() > 2){
                email = input[4];
                }else {
                    age = Integer.parseInt(input[4]);
                }
            }

            if(input.length >= 6){
                age = Integer.parseInt(input[5]);
            }

            Employee employee = new Employee(name, salary, position, email, age);



            departments.

        }


        System.out.println();



    }
}
