package library.models.user;

import library.models.book.Book;
import library.models.book.BookType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private final String firstName;
    private final String secondName;
    private final int id;

    private final String libraryCardIdentifier;

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    private final List<Book> takenBooks;

    public String getLibraryCardIdentifier() {
        return libraryCardIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getId() {
        return id;
    }



    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.id = ID.getUserId();
        this.libraryCardIdentifier = createIdentifier();
        this.takenBooks = new ArrayList<>();
    }

    private String createIdentifier() {
        char startLetter = 'A';
        StringBuilder identifier = new StringBuilder();
        String userId = String.valueOf(id);

        for (char digit : userId.toCharArray()) {
            identifier.append((char)(startLetter + (digit - '0')));
        }

        return identifier.toString();
    }

    public void takeBook(Book book) {
        takenBooks.add(book);
    }

    public void putBack(Book book) {
        takenBooks.remove(book);
    }
}
