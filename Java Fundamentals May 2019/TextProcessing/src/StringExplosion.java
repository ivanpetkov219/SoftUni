import java.util.Scanner;

public class StringExplosion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        StringBuilder result = new StringBuilder();
        int strenght = 0;

        for (int i = 0; i < input.length() ; i++) {
            char currentChar = input.charAt(i);

            if(currentChar != '>'){
                if(strenght == 0) {
                    result.append(currentChar);
                }else {
                    strenght--;
                }
            }else {
                result.append(currentChar);
                int range = (input.charAt(i + 1) - '0') - 1;
                strenght += range;
                i++;
            }
        }
        System.out.println(result);
    }
}
