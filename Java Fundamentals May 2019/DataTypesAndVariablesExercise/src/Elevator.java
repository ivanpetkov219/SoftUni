import java.util.Scanner;

public class Elevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int peopleNumber = scanner.nextInt();
        int elevatorCapacity = scanner.nextInt();

        int courses = (int)Math.ceil(peopleNumber * 1.0/ elevatorCapacity);

        System.out.println(courses);
    }
}
