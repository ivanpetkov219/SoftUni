import java.util.Scanner;

public class OddOrEvenPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        double maxEven = -1000000000.0;
        double minEven = 1000000000.0;
        double sumEven = 0;

        double maxOdd = -1000000000.0;
        double minOdd = 1000000000.0;
        double sumOdd = 0;


        for (int number = 1; number <= n; number++) {
            double currentNumber = Double.parseDouble(scanner.nextLine());

            if (number % 2 == 0){
                sumEven += currentNumber;
                if(currentNumber > maxEven){
                    maxEven = currentNumber;
                }
                if (currentNumber < minEven){
                    minEven = currentNumber;
                }

            }else {
                sumOdd += currentNumber;
                if(currentNumber > maxOdd){
                    maxOdd = currentNumber;
                }
                if (currentNumber < minOdd){
                    minOdd = currentNumber;
                }
            }
        }
        //,
        //"OddMin=" + { минимална стойност на числата на нечетни позиции } / {“No”},
        //"OddMax=" + { максимална стойност на числата на нечетни позиции } / {“No”},
        //"EvenSum=" + { сума на числата на четни позиции },
        //"EvenMin=" + { минимална стойност на числата на четни позиции } / {“No”},
        //"EvenMax=" + { максимална стойност на числата на четни позиции } / {“No”}


        System.out.printf("OddSum=%.2f,%n", sumOdd);
        if (minOdd < 1000000000.0 ){
            System.out.printf("OddMin=%.2f,%n", minOdd);
        }else{
            System.out.println("OddMin=No,");
        }
        if (maxOdd > -1000000000.0 ){
            System.out.printf("OddMax=%.2f,%n", maxOdd);
        }else{
            System.out.println("OddMax=No,");
        }

        System.out.printf("EvenSum=%.2f,%n", sumEven);
        if (minEven < 1000000000.0 ){
            System.out.printf("EvenMin=%.2f,%n", minEven);
        }else{
            System.out.println("EvenMin=No,");
        }
        if (maxEven > -1000000000.0 ){
            System.out.printf("EvenMax=%.2f%n", maxEven);
        }else{
            System.out.println("EvenMax=No");
        }

    }
}
