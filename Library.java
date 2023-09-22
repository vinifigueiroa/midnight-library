/** This program manages a library system. It allows
* users to borrow and return books, as well as display
* information about the available books
*/

import java.util.*;

public class Library {

    public static void main(String[] args) {

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);


        // Initialize Default Books
        Map<String, Book> shelves = new HashMap<>();

        shelves.put("Harry Potter", new Book("Harry Potter and the Priosioner of Azkaban", "J.K. Rowling", 7, 4));
        shelves.put("Building a Second Brain", new Book("Building a Second Brain", "Tiago Forte", 3, 1));
        shelves.put("Every Day", new Book("Every Day", "David Levithan", 10, 1));
        shelves.put("Getting Things Done", new Book("Getting Things Done", "David Allen", 12, 8));
        shelves.put("Atomic Habits", new Book("Atomic Habits", "James Clear", 2, 3));


        // Intro
        System.out.println("Welcome to the midnight library! What would you like to do?");


        // Menu
        boolean loop = true;
        while (loop) {

            System.out.println("\n\nMENU \n\nA - Add a New Book \nB - Borrow a Book \nC - Consult Available Books \nD - Return a Book \nE - Exit \n \nTYPE THE CORRESPONDING LETTER");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a":
                    Library.AddBook(scanner, shelves);
                    break;

                case "b":
                    Library.BorrowBook(scanner, shelves);
                    break;

                case "c":
                    Library.Consult(scanner, shelves);
                    break;

                case "d":
                    Library.ReturnBook(scanner, shelves);
                    break;

                default:
                    loop = false;
                    break;
            }

        }


        // Close Scanner
        scanner.close();
    }


    static void Consult (Scanner scanner, Map<String, Book> shelves) {

        // Sort Titles
        List<String> bookslist = new ArrayList<>(shelves.keySet());
        Collections.sort(bookslist);

        // Display Titles
        int index = 0;
        for (String key : bookslist) {
            System.out.print(index + " - ");
            shelves.get(key).summary();
            System.out.println("\n");
            index++;
        }

        // Outro
        Library.PressEnter(scanner);
    }


    static void AddBook (Scanner scanner, Map<String, Book> shelves) {


        // Retrieve Titles
        List<String> bookslist = new ArrayList<>(shelves.keySet());


        // New Title
        System.out.println("\n \nType the Title of the Book:");
        String title = scanner.nextLine();


        //Check if it exists
        if (bookslist.contains(title)) {
            System.out.println("\n\nWARNING: " + title + " is on the library already!");
            shelves.get(title).summary();
            Library.PressEnter(scanner);
            return;
        }


        // New Author
        System.out.println("\n \nType the author of the Book:");
        String author = scanner.nextLine();


        // Add Amount
        System.out.println("\n \nHow many books are you adding? Type a number");

        int availableint = 0;
        boolean availableCondition = true;

        while(availableCondition) {

            String available = scanner.nextLine();

            try {
                availableint = Integer.parseInt(available);

                if (availableint < 0) {
                    System.err.println("ERROR: Please enter a positive amount.");

                } else if (availableint == 0) {
                    return;

                } else {
                    availableCondition = false;
                }

            } catch (NumberFormatException e) {
                System.err.println("ERROR: Please enter a number.");
            }

        }


        // Outro
        Book newbook = new Book(title, author, availableint, 0);

        shelves.put(title, newbook);

        System.out.println("\n \nBook Added! Thank you!");
        newbook.summary();
        Library.PressEnter(scanner);

    }

    static void BorrowBook(Scanner scanner, Map<String, Book> shelves) {

        // Sort Titles
        List<String> bookslist = new ArrayList<>(shelves.keySet());
        Collections.sort(bookslist);

        //Display Titles
        int index = 0;
        for (String key : bookslist) {
            System.out.print(index + " - ");
            shelves.get(key).summary();
            System.out.println("\n");
            index++;
        }


        // Select book form the list
        int selectionint = 0;
        Book selected = null;

        boolean selectionCondition = true;
        boolean indexCondition = true;

        System.out.println("\n \nType the corresponding number to the book you want to borrow:");

        while(selectionCondition || indexCondition ) {

            String selection = scanner.nextLine();

            try {
                selectionint = Integer.parseInt(selection);
                selectionCondition = false;


            } catch (NumberFormatException e) {
                System.err.println("ERROR: Please enter a number.");
            }

            try {
                selected = shelves.get(bookslist.get(selectionint));
                indexCondition = false;

            } catch (IndexOutOfBoundsException e) {
                System.err.println("ERROR: Please enter a listed number.\n");
            }
        }


        // Validate amount to borrow
        System.out.println("\n \nHow many of it would you like to borrow? We have " + selected.available() + " available.");

        int quantityint = 0;

        boolean intCondition = true;
        boolean availableCondition = true;

        while (intCondition || availableCondition) {

            String quantity = scanner.nextLine();

            try {
                quantityint = Integer.parseInt(quantity);
                intCondition = false;

                if (quantityint > selected.available() || quantityint < 0) {

                    selected.summary();
                    System.out.println("\nERROR: Please enter a valid amount. Check availability above.");

                } else if (quantityint == 0){
                    return;

                } else {
                    availableCondition = false;
                }

            } catch (NumberFormatException e) {
                System.err.println("ERROR: Please enter a number.");
            }

        }


        // Outro
        selected.changeAvailable(selected.available() - quantityint);
        selected.changeBorrowed(selected.borrowed() + quantityint);

        System.out.println("\n \nBook Borrowed! Enjoy your reading!");
        selected.summary();
        Library.PressEnter(scanner);

    }

    static void ReturnBook(Scanner scanner, Map<String, Book> shelves) {

        // Sort Titles
        List<String> bookslist = new ArrayList<>(shelves.keySet());
        Collections.sort(bookslist);

        //Display Titles
        int index = 0;
        for (String key : bookslist) {
            System.out.print(index + " - ");
            shelves.get(key).summary();
            System.out.println("\n");
            index++;
        }


        // Select book form the list
        int selectionint = 0;
        Book selected = null;

        boolean selectionCondition = true;
        boolean indexCondition = true;

        System.out.println("\n \nType the corresponding number to the book you want to return:");

        while(selectionCondition || indexCondition ) {

            String selection = scanner.nextLine();

            try {
                selectionint = Integer.parseInt(selection);
                selectionCondition = false;


            } catch (NumberFormatException e) {
                System.err.println("ERROR: Please enter a number.");
            }

            try {
                selected = shelves.get(bookslist.get(selectionint));
                indexCondition = false;

            } catch (IndexOutOfBoundsException e) {
                System.err.println("ERROR: Please enter a listed number.\n");
            }
        }


        // Validate amount to return
        System.out.println("\n \nHow many are you returning? Currently, there are " + selected.borrowed() + " units checked out.");

        int quantityint = 0;

        boolean intCondition = true;
        boolean borrowedCondition = true;

        while (intCondition || borrowedCondition) {

            String quantity = scanner.nextLine();

            try {
                quantityint = Integer.parseInt(quantity);
                intCondition = false;

                if (quantityint > selected.borrowed() || quantityint < 0) {

                    selected.summary();
                    System.out.println("\nERROR: Please enter a valid amount. Check the number of books to be returned above.");

                } else if (quantityint == 0){
                    return;

                } else {
                    borrowedCondition = false;
                }

            } catch (NumberFormatException e) {
                System.err.println("ERROR: Please enter a number.");
            }

        }


        // Outro
        selected.changeAvailable(selected.available() + quantityint);
        selected.changeBorrowed(selected.borrowed() - quantityint);

        System.out.println("\n \nBook Returned. Thank you!");
        selected.summary();
        Library.PressEnter(scanner);
    }

    static void PressEnter(Scanner scanner) {
        System.out.println("\nPRESS ENTER TO CONTINUE \n");
        scanner.nextLine();
    }
}

public class Book {

    private String title;
    private String author;
    private int available;
    private int borrowed;

    public Book (String title, String author, int available, int borrowed) {

        this.title = title;
        this.author = author;
        this.available = available;
        this.borrowed = borrowed;
    }


    // Consult Info
    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public int available() {
        return available;
    }

    public int borrowed() {
        return borrowed;
    }


    // Modifiers
    public void changeAvailable(int number) {
        this.available = number;
    }

    public void changeBorrowed(int number) {
        this.borrowed = number;
    }


    // Display Summary
    public void summary() {
        System.out.println(title + " by " + author + " || Available: " + available + " || Borrowed: " + borrowed);
    }

}
