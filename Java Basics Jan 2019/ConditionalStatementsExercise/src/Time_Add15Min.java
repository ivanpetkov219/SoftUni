import java.util.Scanner;

public class Time_Add15Min {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputHour = Integer.parseInt(scanner.nextLine());
        int inputMinutes = Integer.parseInt(scanner.nextLine());

        int inputTimeInMinutesAdded15Minutes = inputHour * 60 + inputMinutes + 15;

        int finalHour = inputTimeInMinutesAdded15Minutes / 60;
        int finalMinutes = inputTimeInMinutesAdded15Minutes % 60;

        if (finalHour == 24){
            finalHour = 0;
        }

        if (finalMinutes < 10){
            System.out.printf("%d:0%d", finalHour, finalMinutes);
        }else
            System.out.printf("%d:%d", finalHour, finalMinutes);
    }
}
