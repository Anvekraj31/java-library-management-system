import java.time.LocalDate;

public class Book {

    String title;
    String author;
    boolean issued;
    String issuedTo;
    LocalDate dueDate;

    public Book(String t, String a) {
        title = t;
        author = a;
        issued = false;
        issuedTo = "";
        dueDate = null;
    }
}