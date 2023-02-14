package library.models.fabric;


import library.models.Author;
import library.models.book.HumorBook;
import library.models.book.LifeBook;
import library.models.book.ScientificBook;

import java.util.List;

public class BookFactory {

    /**
     * Create Scientific book
     * @param name - book name
     * @param authors - list of authors
     * @param annotation - annotation
     * @return - Scientific book
     */
    public ScientificBook createScientificBook(String name, Author[] authors, String annotation) {
        return new ScientificBook(name, List.of(authors), annotation);
    }

    /**
     * Create Humor book
     * @param name - book name
     * @param authors - list of authors
     * @param annotation - annotation
     * @return - Humor book
     */
    public HumorBook createHumorBook(String name, Author[] authors, String annotation) {
        return new HumorBook(name, List.of(authors), annotation);
    }

    /**
     * Create Life book
     * @param name - book name
     * @param authors - list of authors
     * @param annotation - annotation
     * @return - Life book
     */
    public LifeBook createLifeBook(String name, Author[] authors, String annotation) {
        return new LifeBook(name, List.of(authors), annotation);
    }
}
