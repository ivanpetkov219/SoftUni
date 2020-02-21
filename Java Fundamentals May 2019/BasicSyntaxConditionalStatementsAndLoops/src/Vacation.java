import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int people = Integer.parseInt(scanner.nextLine());
        String groupType = scanner.nextLine();
        String dayOfWeek = scanner.nextLine();

        double totalPrice = 0;
        double pricePerPerson = 0;

        switch (groupType) {
            case "Students":
                switch (dayOfWeek) {
                    case "Friday":
                        pricePerPerson = 8.45;
                        break;
                    case "Saturday":
                        pricePerPerson = 9.80;
                        break;
                    case "Sunday":
                        pricePerPerson = 10.46;
                        break;
                }
                break;


            case "Business":
                switch (dayOfWeek) {
                    case "Friday":
                        pricePerPerson = 10.90;
                        break;
                    case "Saturday":
                        pricePerPerson = 15.60;
                        break;
                    case "Sunday":
                        pricePerPerson = 16.00;
                        break;
                }
                break;
            case "Regular":
                switch (dayOfWeek) {
                    case "Friday":
                        pricePerPerson = 15.00;
                        break;
                    case "Saturday":
                        pricePerPerson = 20.00;
                        break;
                    case "Sunday":
                        pricePerPerson = 22.50;
                        break;
                }
                break;
        }

        totalPrice = people * pricePerPerson;

        if ("Students".equals(groupType) && people >= 30) {
            totalPrice *= 0.85;
        }

        if ("Business".equals(groupType) && people >= 100) {
            totalPrice = pricePerPerson * (people - 10);
        }

        if ("Regular".equals(groupType) && people >= 10 && people <= 20) {
            totalPrice *= 0.95;
        }


        System.out.printf("Total price: %.2f", totalPrice);
    }
}
