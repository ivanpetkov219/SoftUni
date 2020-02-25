package Vehicles;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, VehicleImpl> vehicleMap = new LinkedHashMap<>();

        createVehicle(scanner, vehicleMap);


        int n = Integer.parseInt(scanner.nextLine());

        while(n-- > 0){
            String[] tokens = scanner.nextLine().split("\\s+");

            String command = tokens[0];
            String vehicleType = tokens[1];

            if(command.equals("Drive")){
                double distance = Double.parseDouble(tokens[2]);

                vehicleMap.get(vehicleType).drive(distance);

            }else if (command.equals("Refuel")){
                double liters = Double.parseDouble(tokens[2]);

                vehicleMap.get(vehicleType).refuel(liters);

            }else if (command.equals("DriveEmpty") && vehicleType.equals("Bus")){
                double distance = Double.parseDouble(tokens[2]);
                ((Bus)vehicleMap.get(vehicleType)).driveEmpty(distance);
            }

        }

        printRemainingFuel(vehicleMap);

    }

    private static void printRemainingFuel(Map<String, VehicleImpl> vehicleMap) {

        for (Map.Entry<String, VehicleImpl> entry : vehicleMap.entrySet()) {
            System.out.println(String.format("%s: %.2f", entry.getValue().getClass().getSimpleName(), entry.getValue().getFuelQuantity()));
        }

    }
    private static void createVehicle (Scanner scanner, Map<String, VehicleImpl> vehicleMap){
        for (int i = 0; i < 3; i++) {
            String[] vehicleInfo = scanner.nextLine().split("\\s+");

            String vehicleType = vehicleInfo[0];
            double fuelQuantity = Double.parseDouble(vehicleInfo[1]);
            double consumptionPerKm = Double.parseDouble(vehicleInfo[2]);
            double tankCapacity = Double.parseDouble(vehicleInfo[3]);

            switch (vehicleType){
                case "Car":
                    Car car = new Car(fuelQuantity, consumptionPerKm, tankCapacity);
                    vehicleMap.put("Car", car);
                    break;
                case "Truck":
                    Truck truck = new Truck(fuelQuantity, consumptionPerKm, tankCapacity);
                    vehicleMap.put("Truck", truck);
                    break;
                case "Bus":
                    Bus bus = new Bus(fuelQuantity, consumptionPerKm, tankCapacity);
                    vehicleMap.put("Bus", bus);
                    break;
            }

        }



    }
}
