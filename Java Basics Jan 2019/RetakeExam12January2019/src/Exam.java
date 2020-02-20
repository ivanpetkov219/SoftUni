import java.util.Scanner;

public class Exam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int studentCount = Integer.parseInt(scanner.nextLine());
        double totalGradeSum = 0;
        double topStudentCount = 0;
        double veryGoodStudentCount = 0;
        double goodStudentCount = 0;
        double failStudentCount = 0;

        for (int currentStudent = 1; currentStudent <= studentCount ; currentStudent++) {

            double studentGrade = Double.parseDouble(scanner.nextLine());
            totalGradeSum += studentGrade;

            if (studentGrade < 3.00){
                failStudentCount++;
            }else if (studentGrade < 4.00){
                goodStudentCount++;
            }else if(studentGrade < 5.00){
                veryGoodStudentCount++;
            }else if (studentGrade >= 5.00){
                topStudentCount++;
            }

        }

        double topStudentPercentage = topStudentCount / studentCount * 100;
        double veryGoodStudentPercentage = veryGoodStudentCount / studentCount * 100;
        double goodStudentPercentage = goodStudentCount / studentCount * 100;
        double failStudentPercentage = failStudentCount / studentCount * 100;
        double examAverageGrade = totalGradeSum / studentCount;

        System.out.printf("Top students: %.2f%%%n", topStudentPercentage);
        System.out.printf("Between 4.00 and 4.99: %.2f%%%n", veryGoodStudentPercentage);
        System.out.printf("Between 3.00 and 3.99: %.2f%%%n", goodStudentPercentage);
        System.out.printf("Fail: %.2f%%%n", failStudentPercentage);
        System.out.printf("Average: %.2f", examAverageGrade);


    }
}
