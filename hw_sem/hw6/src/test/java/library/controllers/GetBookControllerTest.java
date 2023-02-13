package library.controllers;

import library.models.Author;
import library.models.Library;
import library.models.book.Book;
import library.models.book.ScientificBook;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetBookControllerTest {

    @Test
    void get_books_by_name_which_not_in_library_expected_empty_list() {
        String bookName = "A";

        Author mainAuthor = new Author("Александр", "Кучук");
        Book book = new ScientificBook("Дом, в котором...учат java", List.of(new Author[]{mainAuthor}), "Читать всем!");
        Library library = new Library();
        library.addBookToCollection(book);

        List<Book> bookList = GetBookController.getBooksByName(bookName, library);

        assertEquals(Collections.EMPTY_LIST, bookList);
    }

    @Test
    void get_books_by_name_which_in_library_in_one_copy_expected_list_size_1_with_this_book_with_same_name() {
        String bookName = "Дом, в котором...учат java";

        Author mainAuthor = new Author("Александр", "Кучук");
        Book book = new ScientificBook("Дом, в котором...учат java", List.of(new Author[]{mainAuthor}), "Читать всем!");
        Library library = new Library();
        library.addBookToCollection(book);

        List<Book> bookList = GetBookController.getBooksByName(bookName, library);
        List<Book> expectedList = List.of(new Book[] {book});

        assertEquals(expectedList, bookList);
    }

    @Test
    void get_books_by_name_which_in_library_in_many_variants_expected_list_with_books_with_same_name_and_diff_authors() {
        String bookName = "Дом, в котором...учат java";

        Author mainAuthor = new Author("Александр", "Кучук");
        Book book = new ScientificBook("Дом, в котором...учат java", List.of(new Author[]{mainAuthor}), "Читать всем!");
        Library library = new Library();
        library.addBookToCollection(book);

        List<Book> bookList = GetBookController.getBooksByName(bookName, library);
        List<Book> expectedList = List.of(new Book[] {book});

        assertEquals(expectedList, bookList);
    }
}