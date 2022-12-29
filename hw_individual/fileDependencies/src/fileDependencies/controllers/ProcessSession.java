package fileDependencies.controllers;

import fileDependencies.models.CycleException;

import java.io.IOException;
import java.io.UncheckedIOException;

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
            end();
            return;
        }

        Process process = new Process(rootPath);

        try {
            process.processStart();
        } catch (CycleException exception) {
            end();
            return;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            end();
            return;
        } catch (UncheckedIOException exception) {
            System.out.println("При работе с финальным файлом возникли проблемы!\nОзнакомьтесь:");
            System.out.println(exception.getMessage());
            end();
            return;
        } catch (IOException exception) {
            System.out.println("\nОзнакомьтесь с ошибкой подробнее: ");
            System.out.println(exception.getMessage());
            end();
            return;
        }

        System.out.println("Ответ сформирован и записан в файл!");
        System.out.println("Путь к файлу: " + process.getResultFilePath());
        end();
    }

    public void end() {
        System.out.println(FAREWELL_MESSAGE);
    }
}
