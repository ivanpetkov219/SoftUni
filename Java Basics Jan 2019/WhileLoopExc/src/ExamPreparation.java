import java.util.Scanner;

public class ExamPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxFails = Integer.parseInt(scanner.nextLine());
        int totalGrades = 0;
        int gradeCounter = 0;
        int fails = 0;
        String lastTask = "";


        String task = scanner.nextLine();
        while (!"Enough".equals(task)) {
            int grade = Integer.parseInt(scanner.nextLine());
            if (grade <= 4) {
                fails++;
            }

            if (fails >= maxFails) {
                System.out.printf("You need a break, %d poor grades.", fails);
                break;
            }


            totalGrades += grade;
            gradeCounter++;




            lastTask = task;
            task = scanner.nextLine();

        }
        if ("Enough".equals(task)) {
            double avgGrade = totalGrades * 1.0 / gradeCounter;
            System.out.printf("Average score: %.2f%n", avgGrade);
            System.out.printf("Number of problems: %d%n", gradeCounter);
            System.out.printf("Last problem: %s", lastTask);
        }
    }
}
