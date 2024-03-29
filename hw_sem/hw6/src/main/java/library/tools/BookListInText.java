package library.tools;

import library.models.book.Book;

import java.util.List;
import java.util.Map;

public class BookListInText {

    /**
     * Form a pretty text from list of books
     * @param booksList list of books
     * @return string with text
     */
    public static String getBookListInText(List<Book> booksList) {
        StringBuilder result = new StringBuilder();
        int index = 1;

        for (Book book : booksList) {
            result.append(index);
            result.append(". ");
            result.append(book.toString());
            result.append("\n");
            ++index;
        }


        return result.toString();
    }

    /**
     * Form a pretty text from list of books
     * @param bookMap map of books and quantities
     * @return string with text
     */
    public static String getBookListInText(Map<Book, Integer> bookMap) {
        StringBuilder result = new StringBuilder();
        int index = 1;

        for (var entry : bookMap.entrySet()) {
            result.append(index);
            result.append(". ");
            result.append(entry.getKey().toString());
            result.append(". Количество: ");
            result.append(entry.getValue());
            result.append("\n");
            ++index;
        }

        return result.toString();
    }
}
