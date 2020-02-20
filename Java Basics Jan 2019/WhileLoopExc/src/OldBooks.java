import java.util.Scanner;

public class OldBooks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String theBook = scanner.nextLine();
        int libraryCapacity = Integer.parseInt(scanner.nextLine());
        int counter = 1;
        String searchedBooks = scanner.nextLine();
        while (!searchedBooks.equals(theBook)){

            if (counter >= libraryCapacity){
                System.out.printf("The book you search is not here!%nYou checked %d books.", libraryCapacity);
                break;
            }
                counter++;
            searchedBooks = scanner.nextLine();
        }

        if (searchedBooks.equals(theBook)){
            System.out.printf("You checked %d books and found it.", --counter);
        }

    }
}
