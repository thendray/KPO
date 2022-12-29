package filedependencies.tools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputInformation {

    private static final int MISTAKE_LIMIT = 5;

    private static final String EXPLANATION_FOR_PATH_INPUT = """
            Для начала работы программы необходимо ввести полный путь до начальной директивы(папки).
            При достижении лимита на некорректный ввод программа завершится.
            Вводите каждую директиву через *. Программа сама подставит сепараторы.
            Пример: C:*Users*Andrey*Documents*test
            
            """;

    private static final String NOT_EXIST_INPUT_MESSAGE = """
            К сожалению, введенный путь некорректен - такой директивы не существует!
            Попробуйте снова!
            Осталось %d попыток из 5.
            
            """;

    private static final String WRONG_PATH_INPUT_MESSAGE = """
            К сожалению, введенный путь некорректен.
            Убедитесь, что ввели имена каталогов верно и в них нет запрещенных символов.
            Попробуйте снова!
            Осталось %d попыток из 5.
            """;


    /**
     * input strting folder path. User has 5 tries to do it.
     * @return - starting path
     * @throws IllegalArgumentException - throws if starting path is not exist or is a file
     */
    public static String inputRootFolderPath() throws IllegalArgumentException {

        System.out.println(EXPLANATION_FOR_PATH_INPUT);

        String rootPath;
        Scanner in = new Scanner(System.in);
        int mistakeCounter = 0;

        while (true) {

            System.out.print("Введите полный путь:_");

            rootPath = in.nextLine();
            Path path;
            SplitterAndJoiner splitterAndJoiner = new SplitterAndJoiner(rootPath);
            rootPath = splitterAndJoiner.splitAndJoin("\\*", File.separator);

            try {
                path = Paths.get(rootPath);
            } catch (InvalidPathException exception) {
                ++mistakeCounter;
                System.out.printf((WRONG_PATH_INPUT_MESSAGE), MISTAKE_LIMIT - mistakeCounter);
                System.out.println(exception.getMessage());
                System.out.println("\n");
                continue;
            }

            if (Files.exists(path)) {
                System.out.println("Спасибо, данная директива найдена!\nПриступаю к работе.\n");
                break;
            } else {
                ++mistakeCounter;
                System.out.printf((NOT_EXIST_INPUT_MESSAGE) + "%n", MISTAKE_LIMIT - mistakeCounter);
            }

            if (mistakeCounter == MISTAKE_LIMIT) {
                throw new IllegalArgumentException("Корректный путь так и не был введен!");
            }

        }

        return rootPath;
    }

}
