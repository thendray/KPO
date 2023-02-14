package library.models.book;

import library.models.Author;
import library.models.Date;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    private final List<Author> authors;

    private final String name;

    private final String annotation;


    /**
     * Getting list of book authors
     * @return list of authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * getting name of book
     * @return book name
     */
    public String getName() {
        return name;
    }


    /**
     * Getting book annotation
     * @return book annotation
     */
    public String getAnnotation() {
        return annotation;
    }

    public Book(String name, List<Author> authors, String annotation) {
        this.name = name;
        this.authors = authors;
        this.annotation = annotation;
    }

    /**
     * Book equals if names are equals and annotations are equals and all authors are equal
     * @param obj - object which comparing
     * @return true or false - means equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Book otherBook = (Book)obj;

        if (!this.name.equals(otherBook.name)) {
            return false;
        }
        if (!this.annotation.equals(otherBook.annotation)) {
            return false;
        }
        if (this.authors.size() != otherBook.authors.size()) {
            return false;
        }

        for (int i = 0; i < authors.size(); ++i) {
            if (!authors.get(i).equals(otherBook.authors.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + annotation.hashCode() + authors.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append(" | ");

        for (var author : authors) {
            result.append(author);
            result.append(", ");
        }

        result.delete(result.length() - 2, result.length());

        return result.toString();
    }
}

