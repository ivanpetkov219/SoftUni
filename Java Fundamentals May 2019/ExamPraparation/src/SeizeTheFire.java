import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SeizeTheFire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firesWithCells = scanner.nextLine().split("#");
        int totalWater = scanner.nextInt();
        List<Integer> firesPutOut = new ArrayList<>(){};
        double effort = 0;


        for (int i = 0; i < firesWithCells.length; i++) {
            String[] currentFire = firesWithCells[i].split(" = ");
            String typeOfFire = currentFire[0];
            int valueOfFire = Integer.parseInt(currentFire[1]);
            if (isFireValid(typeOfFire, valueOfFire)) {
                if (haveEnoughWater(totalWater, valueOfFire)) {
                    totalWater -= valueOfFire;
                    effort += valueOfFire * 0.25;

                    firesPutOut.add(valueOfFire);

                }
            }

        }
        int totalFirePutOut = 0;
        System.out.println("Cells:");
        for (Integer index : firesPutOut) {
            totalFirePutOut += index;
            System.out.println("- " + index);
        }

        System.out.printf("Effort: %.2f%n", effort);
        System.out.printf("Total Fire: %d", totalFirePutOut);




    }

    private static boolean haveEnoughWater(int totalWater, int valueOfFire) {
        boolean haveEnoughWater = false;
        if (totalWater >= valueOfFire) {
            haveEnoughWater = true;
        }
        return haveEnoughWater;
    }

    private static boolean isFireValid(String typeOfFire, int valueOfFire) {
        boolean isFireValid = false;
        switch (typeOfFire) {
            case "High":
                if (valueOfFire >= 81 && valueOfFire <= 125) {
                    isFireValid = true;
                }
                break;
            case "Medium":
                if (valueOfFire >= 51 && valueOfFire <= 80) {
                    isFireValid = true;
                }
                break;
            case "Low":
                if (valueOfFire >= 1 && valueOfFire <= 50) {
                    isFireValid = true;
                }
                break;
        }
        return isFireValid;
    }
}
