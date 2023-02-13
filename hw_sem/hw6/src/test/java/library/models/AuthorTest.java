package library.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    @DisplayName("Проверяем на равенство двух авторов. Ожидаем, что они будут равны")
    void authors_equal_test_expecting_result_true() {
        Author author1 = new Author("A", "A");
        Author author2 = new Author("A", "A");

        assertEquals(author1, author2);
    }

    @Test
    @DisplayName("Проверяем на равенство двух авторов. Ожидаем, что они будут не равны")
    void authors_equal_test_expecting_result_false() {
        Author author1 = new Author("A", "A");
        Author author2 = new Author("а", "а");

        assertNotEquals(author1, author2);
    }


}