package library.controllers;

import library.models.Library;
import library.models.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetBookController {

    public static List<Book> getBooksByName(String bookName, Library library) {

        Map<Book, Integer> books = library.getBookCollection();
        List<Book> booksWithSameName = new ArrayList<>();

        for (var entry : books.entrySet()) {
            Book book = entry.getKey();

            if (book.getName().equals(bookName) && entry.getValue() > 0) {
                booksWithSameName.add(book);
            }
        }

        return booksWithSameName;
    }

}
