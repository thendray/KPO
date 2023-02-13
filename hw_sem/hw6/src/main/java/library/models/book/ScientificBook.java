package library.models.book;

import library.models.Author;
import library.models.Date;

import java.util.List;

public class ScientificBook extends Book {

    private final BookType type = BookType.SCIENTIFIC;

    public BookType getType() {
        return type;
    }


    public ScientificBook(String name, List<Author> authors, String annotation) {
        super(name, authors, annotation);
    }

    @Override
    public String toString() {
        return super.toString() + " | Наука";
    }
}
