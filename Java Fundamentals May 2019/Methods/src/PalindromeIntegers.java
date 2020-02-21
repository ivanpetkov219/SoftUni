import java.util.Scanner;

public class PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        boolean isPalindrome = true;
        while (!input.equals("END")) {
            // int number = Integer.parseInt(input);
            isPalindrome = true;

            for (int i = 0; i < input.length() / 2; i++) {
                if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                    isPalindrome = false;
                }

                System.out.println(isPalindrome);
                input = scanner.nextLine();


            }


        }
    }
}