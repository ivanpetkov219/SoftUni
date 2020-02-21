import java.math.BigInteger;
import java.util.Scanner;

public class PokeMon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int  N = Integer.parseInt(scanner.nextLine());
        int M = Integer.parseInt(scanner.nextLine());
        byte Y = Byte.parseByte(scanner.nextLine());

        int pokesCount = 0;
        int nAtStart = N;
        double fiftyPercentOfN = N * 1.0 / N * 50;

        while (N >= M){
            double nPowerPercentage = N * 1.0 / nAtStart * 100;
            if (nPowerPercentage == fiftyPercentOfN && N >= Y){
                N = N / Y;
            }
            if (N >= M) {
                N -= M;
                pokesCount++;
            }
        }

        System.out.println(N);
        System.out.println(pokesCount);

    }
}
