import java.util.Scanner;

public class PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budjet = Double.parseDouble(scanner.nextLine());
        int countOfStudents = Integer.parseInt(scanner.nextLine());
        double lightsaberPrice = Double.parseDouble(scanner.nextLine());
        double robePrice = Double.parseDouble(scanner.nextLine());
        double beltPrice = Double.parseDouble(scanner.nextLine());

        // тотал цена = цена за всички сейбъри + цена за всички роби + цена за всички колани

        double lightSabersTotalCount = Math.ceil(countOfStudents * 1.1);
        double lightsaberTotalPrice = lightsaberPrice * lightSabersTotalCount;

        double robeTotalPrice = robePrice * countOfStudents;

        int freeBelts = countOfStudents / 6;
        double beltsTotalPrice = beltPrice * (countOfStudents - freeBelts);

        double totalPrice = beltsTotalPrice + robeTotalPrice + lightsaberTotalPrice;

        if (budjet >= totalPrice){
            System.out.printf("The money is enough - it would cost %.2flv.", totalPrice);
        }else {
            double moneyNeeded = totalPrice - budjet;
            System.out.printf("Ivan Cho will need %.2flv more.", moneyNeeded);
        }



    }
}
