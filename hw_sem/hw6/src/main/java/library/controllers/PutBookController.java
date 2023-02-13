package library.controllers;

import library.models.book.Book;

import java.util.ArrayList;
import java.util.List;

public class PutBookController {

    public static List<Book> returnBookByName(String bookName, List<Book> userBooks) {
        List<Book> booksWithSameName = new ArrayList<>();

        for (Book book : userBooks) {
            if (book.getName().equals(bookName)) {
                booksWithSameName.add(book);
            }
        }

        return booksWithSameName;
    }
}
