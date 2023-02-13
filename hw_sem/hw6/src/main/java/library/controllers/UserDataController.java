package library.controllers;

import library.models.user.User;
import library.tools.FileIterator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataController {

    private final static String DATAFILE = "src" + File.separator + "main" + File.separator +
            "java" + File.separator + "library" + File.separator + "data" + File.separator + "users.json";

    @SuppressWarnings("unchecked")
    public static List<User> getUsersFromData() {

        List<User> libraryUsers = new ArrayList<>();
        try {

            FileInputStream fileInputStream = new FileInputStream(DATAFILE);

            var userData = fileInputStream.read();
            fileInputStream.close();

            if (userData != -1) {
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATAFILE)))
                {
                    var local = ois.readObject();
                    if (local != null){
                        libraryUsers = (ArrayList<User>)local;
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return libraryUsers;
    }

}
