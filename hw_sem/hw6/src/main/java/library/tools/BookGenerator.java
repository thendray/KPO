package library.tools;

import library.models.Author;
import library.models.book.Book;
import library.models.fabric.BookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookGenerator {

    private static final String FILE_WITH_AUTHORS = "src" + File.separator + "main" + File.separator +
            "java" + File.separator + "library" + File.separator + "data" + File.separator + "AuthorsForGeneration.txt";

    private static final String FILE_WITH_BOOK_NAMES = "src" + File.separator + "main" + File.separator +
            "java" + File.separator + "library" + File.separator + "data" + File.separator + "BookNamesForGeneration.txt";

    private static final int MIN_QUANTITY = 25;
    private static final int MAX_QUANTITY = 31;
    private static final int BOOK_TYPES = 3;
    private static final int MAX_QUANTITY_AUTHORS = 2;

    public static List<Book> getGeneratedBooks() throws IOException {

        BookFactory bookFactory = new BookFactory();

        List<Book> books = new ArrayList<>();

        List<String> authorsNames = new ArrayList<>();
        List<String> booksNames = new ArrayList<>();

        FileIterator iteratorForAuthors = new FileIterator(FILE_WITH_AUTHORS);
        FileIterator iteratorForBooks = new FileIterator(FILE_WITH_BOOK_NAMES);

        while (iteratorForAuthors.hasNext()) {
            authorsNames.add(iteratorForAuthors.next());
        }

        while (iteratorForBooks.hasNext()) {
            booksNames.add(iteratorForBooks.next());
        }

        int randomQuantityOfBooks = (int) (MIN_QUANTITY + Math.random() * (MAX_QUANTITY - MIN_QUANTITY + 1));
        int randomForBookType;
        int randomForAuthor;
        int randomForBookName;
        int randomForQuantityAuthors;
        String bookName;
        Set<Author> uniqueAuthors;
        Author[] authors;
        String[] personNameAndSurname;
        String annotation = "Без аннотации";
        Book book;

        while (randomQuantityOfBooks > 0) {
            randomForBookType = (int) ( Math.random() * BOOK_TYPES );
            randomForBookName = (int) (Math.random() * booksNames.size());
            randomForQuantityAuthors = (int) (1 + Math.random() * MAX_QUANTITY_AUTHORS);
            uniqueAuthors = new HashSet<>();

            while (randomForQuantityAuthors > 0) {
                randomForAuthor = (int) (Math.random() * authorsNames.size());

                personNameAndSurname = authorsNames.get(randomForAuthor).split(" ");
                uniqueAuthors.add(new Author(personNameAndSurname[1], personNameAndSurname[0]));

                randomForQuantityAuthors -= 1;
            }

            bookName = booksNames.get(randomForBookName);
            authors = new Author[uniqueAuthors.size()];

            int i = 0;
            for (var uniqueAuthor : uniqueAuthors) {
                authors[i] = uniqueAuthor;
                i += 1;
            }

            if (randomForBookType == 0) {
                book = bookFactory.createHumorBook(bookName, authors, annotation);
            } else if (randomForBookType == 1) {
                book = bookFactory.createScientificBook(bookName, authors, annotation);
            } else {
                book = bookFactory.createLifeBook(bookName, authors, annotation);
            }

            books.add(book);
            randomQuantityOfBooks -= 1;
        }

        return books;
    }

}
