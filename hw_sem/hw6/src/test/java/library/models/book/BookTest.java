package library.models.book;

import library.models.Author;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {


    @Test
    @DisplayName("Проверяем на равенство две книги. Ожидаем, что созданные книги будут равны")
    void test_book_equals_expecting_result_true() {
        Author author1 = new Author("A", "B");
        Author author2 = new Author("A", "B");
        Book book1 = new Book("A", List.of(new Author[]{author1}), "...");
        Book book2 = new Book("A", List.of(new Author[]{author2}), "...");

        assertEquals(book1, book2);
    }


    @Test
    @DisplayName("Проверяем на равенство две книги. Ожидаем," +
            " что созданные книги будут не равны, так как у их авторов разные имена")
    void test_book_equals_expecting_result_false() {
        Author author = new Author("A", "B");
        Author author1 = new Author("B", "A");
        Book book1 = new Book("A", List.of(new Author[]{author}), "...");
        Book book2 = new Book("A", List.of(new Author[]{author1}), "...");

        assertNotEquals(book1, book2);
    }




}