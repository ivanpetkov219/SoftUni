import java.util.Scanner;

public class GraduationPart2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        int ClassCounter = 1;
        double gradeSum = 0;
        int fails = 0;

        while (ClassCounter <= 12) {
            if (fails > 1){
                System.out.printf("%s has been excluded at %d grade", name, ClassCounter);
                break;
            }

                double grade = Double.parseDouble(scanner.nextLine());

            if (grade >= 4) {
                gradeSum += grade;
                ClassCounter++;
                fails = 0;
            }else{
                fails++;
            }


        }
        if (fails <=1) {
            double avgGrade = gradeSum / 12;
            System.out.printf("%s graduated. Average grade: %.2f", name, avgGrade);
        }



    }
}

