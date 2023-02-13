package library;

import library.models.Date;
import library.models.user.ID;
import library.view.LibraryVisitSession;
import library.view.managers.LibraryManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryVisitSession session = new LibraryVisitSession();
        session.start();

    }
}