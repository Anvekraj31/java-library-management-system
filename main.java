import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class main {

    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();

    static void registerUser(Scanner sc) {
        System.out.print("Enter user name: ");
        String name = sc.nextLine();

        users.add(new User(name));
        System.out.println("User registered");
    }

    static void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        bookList.add(new Book(title, author));
        System.out.println("Book added");
    }

    static void showBooks() {
        if (bookList.size() == 0) {
            System.out.println("No books available");
            return;
        }

        for (int i = 0; i < bookList.size(); i++) {
            Book b = bookList.get(i);

            System.out.print((i + 1) + ". " + b.title + " - " + b.author);

            if (b.issued) {
                System.out.println(" (Issued to " + b.issuedTo + ")");
            } else {
                System.out.println(" (Available)");
            }
        }
    }

    static void issueBook(Scanner sc) {
        showBooks();

        System.out.print("Enter book number: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n > 0 && n <= bookList.size()) {
            Book b = bookList.get(n - 1);

            if (b.issued) {
                System.out.println("Already issued");
            } else {
                System.out.print("Enter user name: ");
                String user = sc.nextLine();

                b.issued = true;
                b.issuedTo = user;
                b.dueDate = LocalDate.now().plusDays(7);

                System.out.println("Book issued");
                System.out.println("Due date: " + b.dueDate);
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    static void returnBook(Scanner sc) {
        showBooks();

        System.out.print("Enter book number: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n > 0 && n <= bookList.size()) {
            Book b = bookList.get(n - 1);

            if (!b.issued) {
                System.out.println("Not issued");
            } else {
                LocalDate today = LocalDate.now();

                if (today.isAfter(b.dueDate)) {
                    long days = ChronoUnit.DAYS.between(b.dueDate, today);
                    long fine = days * 10;
                    System.out.println("Late return. Fine = " + fine);
                } else {
                    System.out.println("Returned on time");
                }

                b.issued = false;
                b.issuedTo = "";
                b.dueDate = null;

                System.out.println("Book returned");
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    static void searchBook(Scanner sc) {
        System.out.print("Enter title or author: ");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Book b : bookList) {
            if (b.title.toLowerCase().contains(key) ||
                b.author.toLowerCase().contains(key)) {

                System.out.println(b.title + " - " + b.author);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Menu");
            System.out.println("1. Register User");
            System.out.println("2. Add Book");
            System.out.println("3. View Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Book");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                registerUser(sc);
            } else if (ch == 2) {
                addBook(sc);
            } else if (ch == 3) {
                showBooks();
            } else if (ch == 4) {
                issueBook(sc);
            } else if (ch == 5) {
                returnBook(sc);
            } else if (ch == 6) {
                searchBook(sc);
            } else if (ch == 7) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}