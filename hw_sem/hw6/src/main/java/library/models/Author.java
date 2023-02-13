package library.models;

import library.models.book.Book;

import java.io.Serializable;

public class Author implements Serializable {
    String firstName;

    String secondName;

//    Date birthday;

    public Author(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
//        this.birthday = birthday;
    }

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

        Author otherAuthor = (Author) obj;

        if (!this.firstName.equals(otherAuthor.firstName)) {
            return false;
        }

        if (!this.secondName.equals(otherAuthor.secondName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() + secondName.hashCode();
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }
}
