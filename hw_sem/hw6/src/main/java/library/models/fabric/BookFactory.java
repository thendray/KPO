package library.models.fabric;


import library.models.Author;
import library.models.book.HumorBook;
import library.models.book.LifeBook;
import library.models.book.ScientificBook;

import java.util.List;

public class BookFactory {

    public ScientificBook createScientificBook(String name, Author[] authors, String annotation) {
        return new ScientificBook(name, List.of(authors), annotation);
    }

    public HumorBook createHumorBook(String name, Author[] authors, String annotation) {
        return new HumorBook(name, List.of(authors), annotation);
    }

    public LifeBook createLifeBook(String name, Author[] authors, String annotation) {
        return new LifeBook(name, List.of(authors), annotation);
    }
}
