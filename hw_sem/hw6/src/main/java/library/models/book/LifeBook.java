package library.models.book;

import library.models.Author;
import library.models.Date;

import java.util.List;

public class LifeBook extends Book implements Taken {

    private final BookType type = BookType.LIFE;

    public BookType getType() {
        return type;
    }


    public LifeBook(String name, List<Author> authors, String annotation) {
        super(name, authors, annotation);
    }

    @Override
    public String toString() {
        return super.toString() + " | О жизни";
    }
}
