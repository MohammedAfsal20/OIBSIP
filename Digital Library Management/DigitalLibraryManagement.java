import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private final String title;
    private final String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("You've borrowed \"" + title + "\" by " + author);
        } else {
            System.out.println("This book is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("You've returned \"" + title + "\" by " + author);
        } else {
            System.out.println("This book wasn't borrowed.");
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + !isBorrowed;
    }
}

class Library {
    private final ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Available books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void searchBook(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

public class DigitalLibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\nDigital Library Management System");
                System.out.println("1. Add a Book");
                System.out.println("2. List All Books");
                System.out.println("3. Search for a Book");
                System.out.println("4. Borrow a Book");
                System.out.println("5. Return a Book");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        library.addBook(new Book(title, author));
                    }
                    case 2 -> library.listBooks();
                    case 3 -> {
                        System.out.print("Enter book title to search: ");
                        String title = scanner.nextLine();
                        library.searchBook(title);
                    }
                    case 4 -> {
                        System.out.print("Enter book title to borrow: ");
                        String title = scanner.nextLine();
                        library.borrowBook(title);
                    }
                    case 5 -> {
                        System.out.print("Enter book title to return: ");
                        String title = scanner.nextLine();
                        library.returnBook(title);
                    }
                    case 6 -> System.out.println("Exiting the system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);
        }
    }
}
