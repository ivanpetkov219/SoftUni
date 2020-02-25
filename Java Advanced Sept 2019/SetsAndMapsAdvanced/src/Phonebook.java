import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> phonebook = new HashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("search")){
            String[] data = input.split("-");
            String name = data[0];
            String number = data[1];


            phonebook.putIfAbsent(name, number);

            phonebook.put(name, number);
            input = scanner.nextLine();
        }

        String searchName = scanner.nextLine();

        while (!searchName.equals("stop")){

            if(phonebook.containsKey(searchName)){
                phonebook.get(searchName);

                System.out.printf("%s -> %s%n", searchName, phonebook.get(searchName));

            }else {
                System.out.printf("Contact %s does not exist.%n", searchName);
            }



            searchName = scanner.nextLine();
        }
    }
}
