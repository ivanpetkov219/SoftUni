import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int commandsCount = Integer.parseInt(scanner.nextLine());

        ArrayList<String> birthdayList = new ArrayList<>();

        String input = scanner.nextLine();

        while (commandsCount != 0){
            String[] command = input.split("\\s+");
            String name = command[0];
            if (command[2].equals("not")){
                if(birthdayList.contains(name)){
                    birthdayList.remove(name);
                }else{
                    System.out.printf("%s is not in the list!%n", name);
                }
            }else {
                if (birthdayList.contains(name)){
                    System.out.printf("%s is already in the list!%n", name);
                }else{
                    birthdayList.add(name);
                }
            }
            commandsCount--;
            if(commandsCount == 0){
                break;
            }
            input = scanner.nextLine();
        }
        for (int i = 0; i < birthdayList.size(); i++) {
            System.out.println(birthdayList.get(i));
        }
    }
}
