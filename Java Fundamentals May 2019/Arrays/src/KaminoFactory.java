import java.util.Scanner;

public class KaminoFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int DNAlenght = Integer.parseInt(scanner.nextLine());
        int[] bestDNA = new int[DNAlenght];

        int bestDNASequence = 0;
        int maxSum = 0;
        int maxRepetitions = 0;
        int leftMostStartingIndex = 0;
        int sequenceNumber = 0;

        String input = scanner.nextLine();
        while (!input.equals("Clone them!")) {
            sequenceNumber++;


            String[] inputArray = input.split("\\!+");

            int[] currentSequence = new int[inputArray.length];

            for (int i = 0; i < inputArray.length; i++) {
                currentSequence[i] = Integer.parseInt(inputArray[i]);
            }

            int currentRepetitions = 0;
            int currentBestRepetitions = 0;
            int leftMostCounter = 0;
            int currentSum = 0;


            for (int i = 0; i < currentSequence.length; i++) {
                if (currentSequence[i] == 0) {
                    currentRepetitions = 0;
                    continue;
                } else if (currentSequence[i] == 1) {
                    currentRepetitions++;
                    currentSum++;

                    if(currentRepetitions > currentBestRepetitions) {
                        currentBestRepetitions = currentRepetitions;
                                           leftMostCounter = i - currentBestRepetitions + 1;
                    }
                }
                if (currentBestRepetitions > maxRepetitions || currentBestRepetitions == maxRepetitions && leftMostCounter < leftMostStartingIndex ||
                        currentBestRepetitions == maxRepetitions && leftMostCounter == leftMostStartingIndex && currentSum > maxSum) {

                    leftMostStartingIndex = leftMostCounter;
                    maxSum = currentSum;
                    maxRepetitions = currentBestRepetitions;
                    bestDNASequence = sequenceNumber;

                    for (int j = 0; j < currentSequence.length; j++) {
                        bestDNA[j] = currentSequence[j];
                    }
                }
            }
            input = scanner.nextLine();
        }
        System.out.printf("Best DNA sample %d with sum: %d.%n", bestDNASequence, maxSum);

        for (int i = 0; i < bestDNA.length; i++) {
            System.out.print(bestDNA[i] + " ");
        }
    }
}



