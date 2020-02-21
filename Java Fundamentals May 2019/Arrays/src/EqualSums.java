import java.util.Scanner;

public class EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split(" ");
        int[] numbers = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }

        for (int i = 0; i < numbers.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            for (int l = 0; l < i; l++) {
                leftSum += numbers[l];
            }
            for (int r = i + 1; r < numbers.length ; r++) {
                rightSum += numbers[r];
            }

            if(leftSum == rightSum){
                System.out.println(i);
                break;
            }else if (i == numbers.length -1)
                System.out.println("no");
        }
    }
}
