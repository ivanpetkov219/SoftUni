import java.util.Scanner;

public class SumSeconds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstSeconds = Integer.parseInt(scanner.nextLine());
        int secondSeconds = Integer.parseInt(scanner.nextLine());
        int thirdSeconds = Integer.parseInt(scanner.nextLine());

        int totalTime = firstSeconds + secondSeconds +thirdSeconds;

        int minutes = totalTime / 60;
        int seconds = totalTime % 60;

        if (seconds < 10){
            System.out.println(minutes + ":0" + seconds);
        }else
            System.out.println(minutes + ":" + seconds);


    }
}
