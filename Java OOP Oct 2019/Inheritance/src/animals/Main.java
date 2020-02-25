package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String typeOfAnimal = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();

        while (!typeOfAnimal.equals("Beast!")) {

            String[] data = scanner.nextLine().split("\\s+");

            String name = data[0];
            int age = Integer.parseInt(data[1]);
            String gender = data[2];

            Animal animal;
            try {
                if ("Dog".equals(typeOfAnimal)) {
                    animal = new Dog(name, age, gender);
                } else if ("Frog".equals(typeOfAnimal)) {
                    animal = new Frog(name, age, gender);
                } else if ("Cat".equals(typeOfAnimal)) {
                    animal = new Cat(name, age, gender);
                } else if ("Tomcat".equals(typeOfAnimal)) {
                    animal = new Tomcat(name, age);
                } else {
                    animal = new Kitten(name, age);
                }
                animals.add(animal);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }


            typeOfAnimal = scanner.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());

        }




    }
}
