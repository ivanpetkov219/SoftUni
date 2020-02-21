import com.sun.jdi.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Race {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Integer> participants = new HashMap<>();

        String[] inputNames = scanner.nextLine().split(", ");

        for (String inputName : inputNames) {
            participants.put(inputName, 0);
        }
        String input = scanner.nextLine();
        String regexName = "[A-Za-z]+";
        String regexDigits = "[\\d]";

        while (!input.equals("end of race")){

            Pattern patternName = Pattern.compile(regexName);
            Pattern patternSum = Pattern.compile(regexDigits);

            Matcher nameMatcher = patternName.matcher(regexName);
            Matcher digitMatcher = patternSum.matcher(regexDigits);






            input = scanner.nextLine();
        }













        for (Map.Entry<String, Integer> entry : participants.entrySet()) {
            System.out.println(entry);
        }
    }
}
