import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<String> data = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("3:1")) {
            String[] command = input.split("\\s+");
            String action = command[0];
            String tempString = "";

            if (action.equals("merge")) {
                int startIndex = Integer.parseInt(command[1]);
                int endIndex = Integer.parseInt(command[2]);


                if (startIndex < 0) {
                    startIndex = 0;
                }
                if (startIndex > data.size()) {
                    startIndex = data.size() - 1;
                }
                if (endIndex >= data.size()) {
                    endIndex = data.size() - 1;
                }
                int counter = 0;
                for (int i = startIndex; i <= endIndex; i++) {
                    counter++;
                    tempString += data.get(i);
                }
                data.set(startIndex, tempString);

                int indexRange = endIndex - startIndex + 1;


                while (counter != 1) {
                    counter--;
                    if (data.size() == 1) {
                        break;
                    } else {
                        data.remove(startIndex + 1);
                    }
                }
            } else if (action.equals("divide")) {

                int divisionIndex = Integer.parseInt(command[1]);
                int partitions = Integer.parseInt(command[2]);

                String currentString = data.get(divisionIndex);
                data.remove(divisionIndex);
                int lenghtOfPartitions = 0;
                int lastPartitionLenght = 0;


                if (currentString.length() % partitions == 0) {
                    lenghtOfPartitions = currentString.length() / partitions;

                    int counter = 0;
                    for (int i = currentString.length() - 1; i >= 0; i--) {
                        tempString += currentString.charAt(i);
                        counter++;

                        if (counter == lenghtOfPartitions) {
                            String toUploadToList = new StringBuffer(tempString).reverse().toString();
                            data.add(divisionIndex, toUploadToList);
                            counter = 0;
                            tempString = "";
                        }
                    }
                } //else


            }
            tempString = "";
            input = scanner.nextLine();
        }
        for (String index : data
        ) {
            System.out.print(index + " ");
        }
    }
}