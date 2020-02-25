import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> mailList = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("stop")){

            String email = scanner.nextLine();

            if (checkMailValidity(email)){
                mailList.put(input, email);
            }
            input = scanner.nextLine();
        }

        for (String person : mailList.keySet()) {
            System.out.printf("%s -> %s%n", person, mailList.get(person));
        }
    }

    private static boolean checkMailValidity(String email) {
        boolean isMailValid = true;

        if (email.endsWith("com") || email.endsWith("us") || email.endsWith("uk")){
            isMailValid = false;
        }
        return isMailValid;
    }
}
