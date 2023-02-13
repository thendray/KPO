package library.models;

import library.models.book.Book;
import library.models.book.HumorBook;
import library.models.book.LifeBook;
import library.models.book.ScientificBook;
import library.tools.BookGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private final Map<Book, Integer> bookCollection = new HashMap<>();

    public Map<Book, Integer> getBookCollection() {
        return bookCollection;
    }

    public void generateBooks() {
        Author mainAuthor = new Author("Александр", "Кучук");
        Book book = new ScientificBook("Дом, в котором...учат java", List.of(new Author[]{mainAuthor}), "Читать всем!");
        Author author = new Author("Андрей", "Шилов");
        Book book1 = new LifeBook("Как жить, когда беды с башкой", List.of(new Author[]{author}), "...");
        List<Author> authors = List.of(new Author[]{mainAuthor, author});
        Book book2 = new LifeBook("Как простить просрочку дедлайна на примере студента", authors, "О милосердии");

        addBookToCollection(book);
        addBookToCollection(book1);
        addBookToCollection(book2);

        List<Book> generatedBooks = new ArrayList<>();

        try {
            generatedBooks = BookGenerator.getGeneratedBooks();
        } catch (IOException exception) {
            generatedBooks.add(new Book("Как отлавливать ошибки", List.of(author), "..."));
        }

        for (Book generatedBook : generatedBooks) {
            addBookToCollection(generatedBook);
        }
    }

    public void addBookToCollection(Book book) {
        if (bookCollection.containsKey(book)) {
            bookCollection.put(book, bookCollection.get(book) + 1);
        } else {
            bookCollection.put(book, 1);
        }
    }

    public void someoneTakesBook(Book book) {
        bookCollection.put(book, bookCollection.get(book) - 1);
    }


}
