import java.util.Scanner;

public class Scholarship {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double income = Double.parseDouble(scanner.nextLine());
        double averageGrades = Double.parseDouble(scanner.nextLine());
        double minimumSalary = Double.parseDouble(scanner.nextLine());

        double socialScholarship = Math.floor(minimumSalary * 0.35);
        double excelentGradeScholarship = Math.floor(averageGrades * 25);
        boolean socialIsGreater = socialScholarship > excelentGradeScholarship;


        if (income > minimumSalary && averageGrades < 5.50) {
            System.out.println("You cannot get a scholarship!");
        } else if (income < minimumSalary) {
            if (averageGrades > 4.50 && averageGrades < 5.50) {
                System.out.printf("You get a Social scholarship %.0f BGN",socialScholarship);
            } else if (averageGrades >= 5.50 && socialIsGreater) {
                System.out.printf("You get a Social scholarship %.0f BGN", socialScholarship);
            } else if (averageGrades >= 5.50 && !socialIsGreater) {
                System.out.printf("You get a scholarship for excellent results %.0f BGN", excelentGradeScholarship);
            }
        }else if (income > minimumSalary && averageGrades >= 5.50){
            System.out.printf("You get a scholarship for excellent results %.0f BGN", excelentGradeScholarship);
        }

    }

}


