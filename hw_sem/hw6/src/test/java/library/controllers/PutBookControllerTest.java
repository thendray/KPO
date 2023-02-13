package library.controllers;

import library.models.Author;
import library.models.Library;
import library.models.book.Book;
import library.models.book.ScientificBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PutBookControllerTest {

    @Test
    @DisplayName("Проверяем, что если книги с таким названием нет в списке то вернется пустой список")
    void return_book_by_name_if_there_is_no_books_with_same_name_expect_empty_list() {
        String bookName = "A";

        Author mainAuthor = new Author("Александр", "Кучук");
        Book book = new ScientificBook("Дом, в котором...учат java", List.of(new Author[]{mainAuthor}), "Читать всем!");
        List<Book> bookListTest = List.of(new Book[]{book});


        List<Book> bookListGetByTesting = PutBookController.returnBookByName(bookName, bookListTest);

        assertEquals(Collections.EMPTY_LIST, bookListGetByTesting);
    }

    @Test
    @DisplayName("Проверяем, что если книги с таким названием есть в списке то вернется список с элементом из этих книг")
    void return_book_by_name_if_there_some_books_with_same_name_expect_list_with_this_books() {

        Author mainAuthor = new Author("Александр", "Кучук");
        Book book = new ScientificBook("Дом, в котором...учат java", List.of(new Author[]{mainAuthor}), "Читать всем!");
        List<Book> bookListTestExpected = List.of(new Book[]{book});


        List<Book> bookListGetByTesting = PutBookController.returnBookByName(
                "Дом, в котором...учат java", bookListTestExpected);

        assertEquals(bookListTestExpected, bookListGetByTesting);
    }
}