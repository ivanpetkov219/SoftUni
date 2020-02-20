import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sushiType = scanner.nextLine();
        String restaurant = scanner.nextLine();
        int portions = Integer.parseInt(scanner.nextLine());
        char delivery = scanner.nextLine().charAt(0);
        boolean deliveryWanted = false;
        double price = 0;
        double totalPrice = 0;

        if (delivery == 'Y') {
            deliveryWanted = true;
        } else if (delivery == 'N') {
            deliveryWanted = false;
        }

        switch (restaurant) {
            case "Sushi Zone":
                if ("sashimi".equals(sushiType)){
                    price = 4.99;
                }else if ("maki".equals(sushiType)){
                    price = 5.29;
                }else if ("uramaki".equals(sushiType)){
                    price = 5.99;
                }else if ("temaki".equals(sushiType)){
                    price = 4.29;
                }
                break;
            case "Sushi Time":
                if ("sashimi".equals(sushiType)){
                    price = 5.49;
                }else if ("maki".equals(sushiType)){
                    price = 4.69;
                }else if ("uramaki".equals(sushiType)){
                    price = 4.49;
                }else if ("temaki".equals(sushiType)){
                    price = 5.19;
                }
                break;
            case "Sushi Bar":
                if ("sashimi".equals(sushiType)){
                    price = 5.25;
                }else if ("maki".equals(sushiType)){
                    price = 5.55;
                }else if ("uramaki".equals(sushiType)){
                    price = 6.25;
                }else if ("temaki".equals(sushiType)){
                    price = 4.75;
                }
                break;
            case "Asian Pub":
                if ("sashimi".equals(sushiType)){
                    price = 4.50;
                }else if ("maki".equals(sushiType)){
                    price = 4.80;
                }else if ("uramaki".equals(sushiType)){
                    price = 5.50;
                }else if ("temaki".equals(sushiType)){
                    price = 5.50;
                }
                break;
            default:
                System.out.printf("%s is invalid restaurant!", restaurant);
                break;

        }

        if (price > 0){

            totalPrice = price * portions;

            if (deliveryWanted){
                totalPrice *= 1.20;
            }

            System.out.printf("Total price: %.0f lv.", Math.ceil(totalPrice));
        }


    }
}