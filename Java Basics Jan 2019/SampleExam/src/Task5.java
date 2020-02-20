import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalPassengers = Integer.parseInt(scanner.nextLine());
        int stops = Integer.parseInt(scanner.nextLine());

        for (int stop = 1; stop <= stops ; stop++) {

            int passengersOff = Integer.parseInt(scanner.nextLine());
            int passengersOn = Integer.parseInt(scanner.nextLine());

            totalPassengers = totalPassengers - passengersOff + passengersOn;
            if (stop % 2 == 0){
                totalPassengers -= 2;
            }else {
                totalPassengers += 2;

            }

        }

        System.out.printf("The final number of passengers is : %d", totalPassengers);




    }
}