package library.models;

import library.models.book.Book;
import library.models.book.LifeBook;
import library.models.book.ScientificBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {


    @Test
    @DisplayName("Проверяем способность генерировать книги. Изначально сгенерированный список книг не может быть пустым!")
    void generate_books_with_minimum_quantity_of_books() {

        Library library = new Library();
        library.generateBooks();

        assertNotEquals(Collections.EMPTY_MAP, library.getBookCollection());

    }

    @Test
    @DisplayName("Проверяем добавление книги, которой раньше не было. Ожидаем, что создастся ключ со значением 1")
    void add_book_to_collection_which_has_not_had_this_book_before() {
        Library library = new Library();
        Book book = new Book("A", List.of(new Author[]{new Author("A", "B")}), "...");

        Map<Book, Integer> books = new HashMap<>();
        books.put(book, 1);

        library.addBookToCollection(book);

        assertEquals(books, library.getBookCollection());
    }

    @Test
    @DisplayName("Проверяем, что если книга уже есть в коллекции, то ее счетчик количества увеличивается на один")
    void add_book_to_collection_which_has_had_this_book_before() {
        Library library = new Library();
        Book book = new Book("A", List.of(new Author[]{new Author("A", "B")}), "...");

        Map<Book, Integer> books = new HashMap<>();
        books.put(book, 2);

        library.addBookToCollection(book);
        library.addBookToCollection(book);

        assertEquals(books, library.getBookCollection());
    }

    @Test
    @DisplayName("Проверяем, что книгу можно взять из библиотеки")
    void someone_takes_book_which_library_has() {
        Library library = new Library();
        Book book = new Book("A", List.of(new Author[]{new Author("A", "B")}), "...");
        library.addBookToCollection(book);
        library.someoneTakesBook(book);

        Map<Book, Integer> books = new HashMap<>();
        books.put(book, 0);

        assertEquals(books, library.getBookCollection());
    }
}