import java.util.Scanner;

public class TicketCombination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int combinationNumber = Integer.parseInt(scanner.nextLine());
        int count = 0;

        for (char stadiumLetter = 'B'; stadiumLetter <= 'L' ; stadiumLetter += 2) {
            for (char sectorLetter = 'f'; sectorLetter >= 'a' ; sectorLetter--) {
                for (char entryLetter = 'A'; entryLetter <= 'C' ; entryLetter++) {
                    for (int row = 1; row <= 10 ; row++) {
                        for (int seat = 10; seat >= 1; seat--) {
                            count++;

                            if (count == combinationNumber){
                                System.out.printf("Ticket combination: %c%c%c%d%d%n", stadiumLetter, sectorLetter, entryLetter, row, seat);
                                int prize = (int)stadiumLetter + (int)sectorLetter + (int)entryLetter + row + seat;
                                System.out.printf("Prize: %d lv.", prize);
                            }



                        }

                    }

                }

            }

        }
    }
}
