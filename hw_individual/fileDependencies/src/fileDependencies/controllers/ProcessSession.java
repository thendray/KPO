package fileDependencies.controllers;

import static fileDependencies.tools.InputInformation.inputRootFolderPath;

public class ProcessSession {

    private static final String GREETING_MESSAGE = """
            Hello, my friend!
            Это программа <Файловые зависимости>
            Приятного пользования!
            """;

    private static final String FAREWELL_MESSAGE = """
            С вами было приятно работать!
            До новых встреч.
            """;

    public void start() {

        System.out.println(GREETING_MESSAGE);

        String rootPath;

        try {
            rootPath = inputRootFolderPath();
        } catch (IllegalArgumentException exception) {
            System.out.println("УВЫ! Кажется мы не поняли друг-друга.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------------------------");
        }

        // do()

        end();
    }

    public void end() {
        System.out.println(FAREWELL_MESSAGE);
    }
}
