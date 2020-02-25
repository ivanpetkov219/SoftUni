package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;

import java.util.Scanner;

public class EngineImpl implements Engine {

    private ChampionshipController controller;
    private Scanner scanner;

    public EngineImpl(ChampionshipController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {

        while (true) {

            String result = null;
            try {
                result = executeCommands();
                if (result.equals("End")) {
                    break;
                }

            } catch (IllegalArgumentException | NullPointerException exeption) {
                result = exeption.getMessage();
            }
            System.out.println(result.trim());
        }
    }

    private String executeCommands() {

        String result = null;

        String[] input = scanner.nextLine().split("\\s+");
        String command = input[0];
        String riderName;
        String race;

        switch (command) {

            case "CreateRider":
                String name = input[1];
                result = this.controller.createRider(name);
                break;
            case "CreateMotorcycle":
                String type = input[1];
                String model = input[2];
                int horsepower = Integer.parseInt(input[3]);
                result = this.controller.createMotorcycle(type, model, horsepower);
                break;
            case "AddMotorcycleToRider":
                riderName = input[1];
                String motorcycleName = input[2];
                result = this.controller.addMotorcycleToRider(riderName, motorcycleName);
                break;
            case "AddRiderToRace":
                String raceName = input[1];
                riderName = input[2];
                result = this.controller.addRiderToRace(raceName, riderName);
                break;
            case "CreateRace":
                race = input[1];
                int laps = Integer.parseInt(input[2]);
                result = this.controller.createRace(race, laps);
                break;
            case "StartRace":
                race = input[1];
                result = this.controller.startRace(race);
                break;
            case "End":
                result = "End";
        }
        return result;
    }
}
