package library.controllers;

import library.models.user.User;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ExitController {

    private static final String DATAFILE = "src/main/java/library/data/users.json";

    /**
     * Save users in json file and finish the program
     * Throws RuntimeException if FileStream throws exceptions and there is som problems with serialization
     * @param libraryUsers - list of users who has got the library card
     */
    public static void exit(List<User> libraryUsers) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATAFILE)))
        {
            oos.writeObject(libraryUsers);
        }
        catch(Exception ex){
            throw new RuntimeException("Проблемы с сохранением данных!");
        }
    }

}
