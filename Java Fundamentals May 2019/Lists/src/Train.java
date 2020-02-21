import java.util.ArrayList;
import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       String[] trainData = scanner.nextLine().split(" ");

       ArrayList<Integer> train = new ArrayList<>();

        for (int i = 0; i < trainData.length; i++) {
            train.add(Integer.parseInt(trainData[i]));
        }

       int carCapacity = Integer.parseInt(scanner.nextLine());

       String input = scanner.nextLine();

       while (!input.equals("end")){
           String[] command = input.split(" ");
           if (command[0].equals("Add")){
               int passengers = Integer.parseInt(command[1]);
               train.add(passengers);
           }else {
               int passengers = Integer.parseInt(command[0]);

               for (int i = 0; i < train.size(); i++) {
                   int totalPassengers = passengers + train.get(i);
                   if (totalPassengers <= carCapacity){
                       train.set(i, totalPassengers);
                       break;
                   }
               }
           }
           input = scanner.nextLine();
       }

        for (int i = 0; i < train.size(); i++) {
            System.out.print(train.get(i) + " ");
        }

    }
}
