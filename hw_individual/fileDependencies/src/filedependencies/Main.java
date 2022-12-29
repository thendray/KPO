package filedependencies;

import filedependencies.controllers.ProcessSession;

public class Main {
    public static void main(String[] args) {
        ProcessSession session = new ProcessSession();

        session.start();
    }
}