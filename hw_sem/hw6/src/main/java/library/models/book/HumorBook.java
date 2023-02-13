package library.models.book;

import library.models.Author;
import library.models.Date;

import java.util.List;

public class HumorBook extends Book implements Taken {

    private final BookType type = BookType.HUMOR;

    public BookType getType() {
        return type;
    }

    public HumorBook(String name, List<Author> authors, String annotation) {
        super(name, authors, annotation);
    }

    @Override
    public String toString() {
        return super.toString() + " | Юмор";
    }
}
