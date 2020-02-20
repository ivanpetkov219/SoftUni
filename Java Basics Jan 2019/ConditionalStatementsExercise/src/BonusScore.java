import java.util.Scanner;

public class BonusScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int points = Integer.parseInt(scanner.nextLine());
        double bonus = 0;

        if (points <= 100){
            bonus = 5;
        } else if(points < 1000){
            bonus = points * 0.20;
        }else if (points > 1000){
            bonus = points * 0.1;
        }else
            bonus = 0;

        double extraBonus = 0;

        if (points % 2 == 0){
            extraBonus = 1;
        }else if (points % 5 == 0){
            extraBonus = 2;
        }else
            extraBonus = 0;

        double finalBonus = bonus + extraBonus;

        System.out.println(finalBonus);
        System.out.println(points + finalBonus);

    }
}
