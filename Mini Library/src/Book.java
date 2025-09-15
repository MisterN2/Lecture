import java.util.ArrayList;


public class Book {
    private String title;
    private String author;
    private int year;
    private String status;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = "available";
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", status=" + status +
                '}';
    }
}


class Library {
    static int totalBooks = 0;
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<String> actions = new ArrayList<>();

    public static void addBook(Book book) {
        books.add(book);
        totalBooks++;
        System.out.println("Book added: " + book.getTitle());
    }

    public static void addAction(String action) {
        actions.add(action);
    }

    public static void showAllBooks() {
        System.out.println("=== All books in library ===");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public static void showActions() {
        System.out.println("=== User actions log ===");
        for (String act : actions) {
            System.out.println(act);
        }
    }
}


class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }

    public void borrowBook(Book book) {
        if (book.getStatus().equals("available")) {
            book.setStatus("borrowed");
            borrowedBooks.add(book);
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.setStatus("available");
            borrowedBooks.remove(book);
            System.out.println(name + " returned " + book.getTitle());
        } else {
            System.out.println(name + " doesn't have " + book.getTitle());
        }
    }


    public void showBorrowedBooks() {
        System.out.println(name + " has borrowed:");
        if (borrowedBooks.isEmpty()) {
            System.out.println("  (no books)");
        } else {
            for (Book b : borrowedBooks) {
                System.out.println("  " + b.getTitle());
            }
        }
    }
}


class Librarian {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    
    public void registerBook(String title) {
        System.out.println(name + " registered a new book with title: " + title);
        Library.addAction(name + " registered a new book with title: " + title);
    }

    public void registerBook(Book book) {
        System.out.println(name + " registered book: " + book.toString());
        Library.addAction(name + " registered book: " + book.toString());
    }

    public void registerAction(User user, Book book, String action) {
        String log = "LOG: " + user.getName() + " " + action + " " + book.getTitle();
        System.out.println(log);
        Library.addAction(log);
    }
}


class Main {
    public static void main(String[] args) {
        Book b1 = new Book("Java basics", "John Smith", 2018);
        Book b2 = new Book("Data structures", "Jane Smith", 2021);

        Library.addBook(b1);
        Library.addBook(b2);

        User u1 = new User("Alice");
        User u2 = new User("Bob");
        Librarian librarian = new Librarian("Mr. Clark");

        librarian.registerBook("Algorithms");
        librarian.registerBook(b1);

        u1.borrowBook(b1);
        librarian.registerAction(u1, b1, "borrowed");

        u2.borrowBook(b1);
        librarian.registerAction(u2, b1, "tried to borrow");

        u1.returnBook(b1);
        librarian.registerAction(u1, b1, "returned");

       
        Library.showAllBooks();

        Library.showActions();

        u1.showBorrowedBooks();
        u2.showBorrowedBooks();
    }
}
