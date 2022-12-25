package fileDependencies.tools;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputInformation {

    private static final int MISTAKE_LIMIT = 5;

    private static final String EXPLANATION_FOR_PATH_INPUT = """
            Для начала работы программы неоходимо ввести полный путь до начальной дерективы(папки).
            При достижении лимита на некорректный ввод программа завершится\n.
            """;

    private static final String WRONG_INPUT_MESSAGE = """
            К сожалению, введеный путь некоректен!
            Попробуйте снова!
            Осталось %d попыток из 5.\n
            """;


    public static String inputRootFolderPath() throws IllegalArgumentException {

        System.out.println(EXPLANATION_FOR_PATH_INPUT);

        String rootPath;
        Scanner in = new Scanner(System.in);
        int mistakeCounter = 0;

        while (true) {

            System.out.print("Введите полный путь:_");

            rootPath = in.nextLine();
            Path path = Paths.get(rootPath);

            if (Files.exists(path)) {
                System.out.println("Спасибо, данная деректива найдена!\nПриступаю к работе.");
                break;
            } else {
                ++mistakeCounter;
                System.out.println(String.format(WRONG_INPUT_MESSAGE, MISTAKE_LIMIT - mistakeCounter));
            }

            if (mistakeCounter == MISTAKE_LIMIT) {
                throw new IllegalArgumentException("Корректный путь так и не был введен!");
            }

        }

        return rootPath;
    }

}
