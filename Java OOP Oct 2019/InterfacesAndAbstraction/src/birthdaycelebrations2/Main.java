package birthdaycelebrations2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Citizen> citizens = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        readInputInformation(scanner, citizens, pets);

        String year = scanner.nextLine();



        checkYearPresenceInCitizens(citizens, year);
        checkYearPresenceInPets(pets, year);

    }

    private static void checkYearPresenceInPets(List<Pet> pets, String year) {
        pets.stream().forEach(pet -> {
            if (pet.getBirthDate().endsWith(year)) {
                System.out.println(pet.getBirthDate());
            }
        });
    }

    private static void checkYearPresenceInCitizens(List<Citizen> citizens, String year) {

        citizens.stream().forEach(citizen -> {
            if (citizen.getBirthDate().endsWith(year)) {
                System.out.println(citizen.getBirthDate());
            }
        });


    }

    private static void readInputInformation(Scanner scanner, List<Citizen> citizens, List<Pet> pets) {
        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");


            createObject(tokens, citizens, pets);

            input = scanner.nextLine();
        }
    }

    private static void createObject(String[] tokens, List<Citizen> citizens, List<Pet> pets) {
        String objectType = tokens[0];
        switch (objectType) {
            case "Citizen":
                Citizen citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                citizens.add(citizen);
                break;
            case "Pet":
                Pet pet = new Pet(tokens[1], tokens[2]);
                pets.add(pet);
                break;
            case "Robot":
                Robot robot = new Robot(tokens[1], tokens[2]);
                break;
        }

    }
}
