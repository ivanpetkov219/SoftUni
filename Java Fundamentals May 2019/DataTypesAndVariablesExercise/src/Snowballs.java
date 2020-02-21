import java.util.Scanner;

public class Snowballs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        byte numberOfSnowBalls = scanner.nextByte();
        double bestSnowBallValue = Double.MIN_VALUE;
        short bestSnowBallSnow = Short.MIN_VALUE;
        short bestSnowBallTime = Short.MIN_VALUE;
        byte bestSnowBallQuality = Byte.MIN_VALUE;

        double currentBallValue = 0;
        for (int i = 1; i <= numberOfSnowBalls; i++) {
            short snowBallSnow = scanner.nextShort();
            short snowBallTime = scanner.nextShort();
            byte snowBallQuality = scanner.nextByte();

            currentBallValue = Math.pow((snowBallSnow * 1.0 / snowBallTime), snowBallQuality);

            if (currentBallValue > bestSnowBallValue) {
                bestSnowBallValue = currentBallValue;
                bestSnowBallQuality = snowBallQuality;
                bestSnowBallSnow = snowBallSnow;
                bestSnowBallTime = snowBallTime;
            }

        }
        System.out.printf("%d : %d = %.0f (%d)",
                bestSnowBallSnow, bestSnowBallTime, bestSnowBallValue, bestSnowBallQuality);
    }
}
