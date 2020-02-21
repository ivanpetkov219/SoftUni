import java.util.Scanner;

public class PartyProfit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int partySize = Integer.parseInt(scanner.nextLine());
        int days = Integer.parseInt(scanner.nextLine());
        int result = 0;


        for (int i = 1; i <= days ; i++) {
            int foodExpences = 0;
            int earningPerDay = 0;
            int waterExpences = 0;
            if (i % 10 == 0){
                partySize -= 2;
            }
            if (i % 15 == 0){
                partySize += 5;
            }

            earningPerDay += 50;
            foodExpences += 2 * partySize;

            if (i % 3 == 0){
                waterExpences += 3 * partySize;
            }
            if (i % 5 == 0){
                earningPerDay += 20 * partySize;
                if(i % 3 == 0){
                    waterExpences += 2 * partySize;
                }
            }


            result += earningPerDay - waterExpences - foodExpences;

        }

        int profitPerCompanion = result / partySize;


        System.out.printf("%d companions received %d coins each.", partySize, profitPerCompanion );
    }
}
