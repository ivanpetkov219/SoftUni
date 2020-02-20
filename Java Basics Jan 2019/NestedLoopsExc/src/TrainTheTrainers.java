import java.util.Scanner;

public class TrainTheTrainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jury = Integer.parseInt(scanner.nextLine());

        String input = scanner.nextLine();
        int counter = 0;
        double finalSum = 0;

        while (!"Finish".equals(input)){
            double averageGrade = 0;
            double gradeSum = 0;

            for (int i = 1; i <= jury ; i++) {
                double grade = Double.parseDouble(scanner.nextLine());
                gradeSum += grade;
            }
            averageGrade = gradeSum / jury;
            System.out.printf("%s - %.2f.%n", input, averageGrade);

            finalSum += averageGrade;
            counter++;

            input = scanner.nextLine();
        }
        double finalAverage = finalSum / counter;

        System.out.printf("Student's final assessment is %.2f.", finalAverage);

    }
}
