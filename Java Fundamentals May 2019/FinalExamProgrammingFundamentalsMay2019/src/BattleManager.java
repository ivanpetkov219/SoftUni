import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BattleManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> peopleAndHealth = new HashMap<>();
        Map<String, Integer> peopleAndEnergy = new HashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("Results")) {
            String[] commands = input.split(":");

            String action = commands[0];

            if (action.equals("Add")) {

                String personName = commands[1];
                int health = Integer.parseInt(commands[2]);
                int energy = Integer.parseInt(commands[3]);

                if (!peopleAndHealth.containsKey(personName)) {
                    peopleAndHealth.put(personName, health);
                    peopleAndEnergy.put(personName, energy);
                } else {
                    peopleAndHealth.put(personName, peopleAndHealth.get(personName) + health);
                }


            } else if (action.equals("Attack")) {

                String attackerName = commands[1];
                String defenderName = commands[2];
                int damage = Integer.parseInt(commands[3]);

                if (peopleAndHealth.containsKey(attackerName) && peopleAndHealth.containsKey(defenderName)) {
                    int newDefenderHealth = peopleAndHealth.get(defenderName) - damage;
                    if (newDefenderHealth <= 0) {
                        peopleAndHealth.remove(defenderName);
                        peopleAndEnergy.remove(defenderName);
                        System.out.println(defenderName + " was disqualified!");
                    } else {
                        peopleAndHealth.put(defenderName, newDefenderHealth);
                    }
                    int newAttackerEnergy = peopleAndEnergy.get(attackerName) - 1;

                    if (newAttackerEnergy == 0) {
                        System.out.println(attackerName + " was disqualified!");
                        peopleAndHealth.remove(attackerName);
                        peopleAndEnergy.remove(attackerName);
                    } else {
                        peopleAndEnergy.put(attackerName, newAttackerEnergy);
                    }
                }
            } else if (action.equals("Delete")) {
                String username = commands[1];

                if (!username.equals("All")) {
                    peopleAndEnergy.remove(username);
                    peopleAndHealth.remove(username);
                } else {
                    peopleAndEnergy.clear();
                    peopleAndHealth.clear();
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("People count: " + peopleAndHealth.size());

        peopleAndHealth.entrySet().stream().sorted((first, second) -> {
            int result = second.getValue().compareTo(first.getValue());

            if (result == 0) {
                result = first.getKey().compareTo(second.getKey());
            }
            return result;
        }).forEach(entry -> {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " - " + peopleAndEnergy.get(entry.getKey()));
        });
    }
}
