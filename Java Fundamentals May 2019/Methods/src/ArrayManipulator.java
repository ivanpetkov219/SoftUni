import java.util.Scanner;

public class ArrayManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] arrayInput = scanner.nextLine().split(" ");

        int[] array = new int[arrayInput.length];

        for (int i = 0; i < arrayInput.length; i++) {
            array[i] = Integer.parseInt(arrayInput[i]);
        }


        String input = scanner.nextLine();

        while (!input.equals("end")) {

            String[] command = input.split(" ");

            if (command[0].equals("exchange")) {
                int exchangeIndex = Integer.parseInt(command[1]);
                exchangeArray(array, exchangeIndex);
            } else if (command[0].equals("max")) {
                if (command[1].equals("even")) {
                    getMaxEven(array);
                } else if (command[1].equals("odd")) {
                    getMaxOdd(array);
                }
            } else if (command[0].equals("min")) {
                if (command[1].equals("even")) {
                    getMinEven(array);
                } else if (command[1].equals("odd")) {
                    getMinOdd(array);
                }
            }else if (command[0].equals("first")) {

                    int countIndexesToShow = Integer.parseInt(command[1]);
                    String numberType = command[2];
                    getFirstEvenOrOdd(array, countIndexesToShow, numberType);


            }
            input = scanner.nextLine();
        }
    }

    static void exchangeArray(int[] array, int exchangeIndex) {
        if (exchangeIndex < array.length) {
            int[] tempArray1 = new int[array.length - exchangeIndex - 1];
            int[] tempArray2 = new int[exchangeIndex + 1];

            for (int i = 0; i < tempArray1.length; i++) {
                tempArray1[i] = array[exchangeIndex + i + 1];
            }
            for (int i = 0; i < tempArray2.length; i++) {
                tempArray2[i] = array[i];
            }
            for (int i = 0; i < tempArray1.length; i++) {
                array[i] = tempArray1[i];
            }
            for (int i = 0; i < tempArray2.length; i++) {
                array[exchangeIndex + i - 1] = tempArray2[i];
            }
        } else {
            System.out.println("Invalid index");
        }
    }
    static void getMaxEven(int[] array) {
        int maxEvenIndex = -1;
        int maxEven = Integer.MIN_VALUE;

        for (int i = 0; i < array.length ; i++) {
            if (array[i] % 2 == 0){
                if(array[i] >= maxEven){
                    maxEven = array[i];
                    maxEvenIndex = i;
                }
            }
        }
        if (maxEvenIndex == -1){
            System.out.println("No matches");
        }else {
            System.out.println(maxEvenIndex);
        }
    }
    static void  getMaxOdd (int[] array){
        int maxOddIndex = -1;
        int maxOdd = Integer.MIN_VALUE;

        for (int i = 0; i < array.length ; i++) {
            if (array[i] % 2 == 1){
                if(array[i] >= maxOdd){
                    maxOdd = array[i];
                    maxOddIndex = i;
                }
            }
        }
        if (maxOddIndex == -1){
            System.out.println("No matches");
        }else {
            System.out.println(maxOddIndex);
        }
    }

    static void getMinEven (int[] array){
        int minEvenIndex = -1;
        int minEven = Integer.MAX_VALUE;

        for (int i = 0; i < array.length ; i++) {
            if (array[i] % 2 == 0){
                if(array[i] <= minEven){
                    minEven = array[i];
                    minEvenIndex = i;
                }
            }
        }
        if (minEvenIndex == -1){
            System.out.println("No matches");
        }else {
            System.out.println(minEvenIndex);
        }

    }

    static void getMinOdd (int[] array){
        int minOddIndex = -1;
        int minOdd = Integer.MAX_VALUE;

        for (int i = 0; i < array.length ; i++) {
            if (array[i] % 2 == 1){
                if(array[i] <= minOdd){
                    minOdd = array[i];
                    minOddIndex = i;
                }
            }
        }
        if (minOddIndex == -1){
            System.out.println("No matches");
        }else {
            System.out.println(minOddIndex);
        }
    }

    static void getFirstEvenOrOdd (int[] array, int count, String evenOrOdd){
        if(evenOrOdd.equals("even")){
            int[] firstEven = new int[count];
            int counter = 0;
            for (int i = 0; i < array.length ; i++) {
                if(array[i] % 2 == 0){
                    firstEven[counter] = array[i];
                    counter++;
                    if (counter == count){
                        break;
                    }
                }
            }
            for (int i = 0; i < firstEven.length ; i++) {
                if ( i == 0){
                    System.out.printf("[%d, ", firstEven[0]);
                }else if (i < firstEven.length - 1){
                    System.out.print(firstEven[i] + ", ");
                }else if (i == firstEven.length - 1){
                    System.out.print(firstEven[i] + "]");
                    System.out.println();
                }
            }
        }//else {





    }

}
