package fileDependencies.controllers;

import fileDependencies.models.CycleException;
import fileDependencies.models.FileGraph;
import fileDependencies.tools.CreateNewFile;
import fileDependencies.tools.FileIterator;
import fileDependencies.tools.SplitterAndJoiner;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fileDependencies.tools.CycleChecker.hasGraphCycle;
import static fileDependencies.tools.TopologicalSort.topologicalSort;

public class Process {

    private final String rootPath;
    private final FileGraph graph;
    private final List<File> files;

    private String resultFilePath;

    public String getResultFilePath() {
        return resultFilePath;
    }

    public Process(String path) {
        rootPath = path;
        graph = new FileGraph();
        files = new ArrayList<>();
    }

    private final static String WRONG_WITH_READING_MESSAGE = """
            Возникли проблемы при чтении файла(ов)!
            К сожалению работа программы на этом закончена.
            Проверьте данные и попробуйте снова.
            """;

    private final static String WRONG_WITH_FILE_PATH_MESSAGE = """
            Не существует файлов указанных в протоколе <require>!
            Проверьте данные и запустите программу занаво!
            Ниже представлены пути к файлам, которых не удалоь найти.
            """;

    public void processStart() throws IOException, CycleException {

        getFilesFromFolder(rootPath);

        try {
            readFilesAndFindDependencies();
        } catch (IOException ioException) {
            System.out.println(WRONG_WITH_READING_MESSAGE);
            throw ioException;
        } catch (IllegalArgumentException exception) {
            System.out.println(WRONG_WITH_FILE_PATH_MESSAGE);
            throw exception;
        }

        Map<String, Boolean> filesInCycle = hasGraphCycle(graph);


        if (filesInCycle == null){
            List<String> filesInOrder = topologicalSort(graph);

            CreateNewFile creator = new CreateNewFile(filesInOrder);
            try {
                resultFilePath = rootPath + File.separator + "result.txt";
                creator.createNewFile(resultFilePath);
            } catch (IOException exception) {
                throw new UncheckedIOException(exception);
            }

        } else {
            System.out.println("\nПри работе с файлами обнаружен цикл!!!");
            System.out.println("Ниже представлен перечень файлов, образующих цикл:");
            for (String file : filesInCycle.keySet()) {
                if (filesInCycle.get(file)){
                    System.out.println(file);
                }
            }
            throw new CycleException();
        }
    }


    private void readFilesAndFindDependencies() throws IOException, IllegalArgumentException {
        String currentLine;
        List<String> requires;

        for (File currentFile : files) {

            try (FileIterator fileIterator = new FileIterator(currentFile)) {

                while (fileIterator.hasNext()) {
                    currentLine = fileIterator.next();
                    requires = getRequires(currentLine);

                    List<String> filePathsWhichDoNotExist = filePathsCheck(requires);

                    if (!filePathsWhichDoNotExist.isEmpty()) {
                        StringBuilder filePaths = new StringBuilder();
                        for (String filePath : filePathsWhichDoNotExist) {
                            filePaths.append(filePath);
                            filePaths.append("\n");
                        }
                        throw new IllegalArgumentException(filePaths.toString());
                    }

                    if (!requires.isEmpty()) {
                        graph.add(currentFile.getPath(), requires);

                        for (String require : requires) {
                            if (!graph.isExist(require)) {
                                graph.add(require, new ArrayList<>());
                            }
                        }
                    }
                }
            }
        }
    }

    private List<String> filePathsCheck(List<String> filePaths) {
        List<String> wrongPaths = new ArrayList<>();

        for (String filePath : filePaths) {

            try {
                Paths.get(filePath);
            } catch (InvalidPathException exception) {
                wrongPaths.add(filePath);
            }
        }

        return wrongPaths;
    }

    private List<String> getRequires(String currentLine) {
        String patternString = "(?<=require ').*(?=')";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(currentLine);

        List<String> requires = new ArrayList<>();
        String matchString;
        SplitterAndJoiner splitterAndJoiner;

        while (matcher.find()) {
            matchString = currentLine.substring(matcher.start(), matcher.end());
            splitterAndJoiner = new SplitterAndJoiner(matchString);
            matchString = splitterAndJoiner.splitAndJoin("\\*", File.separator);

            requires.add(String.format("%s%s%s",rootPath, File.separator, matchString));
        }

        return requires;
    }


    private void getFilesFromFolder(String folderPath) {
        File currentFolder = new File(folderPath);

        String[] folderContent = currentFolder.list();
        int length = 0;

        if (folderContent != null) {
            length = folderContent.length;
        }

        File currentFile;
        String currentFilePath = folderPath + File.separator;

        for (int i = 0; i < length; ++i) {

            currentFile = new File(currentFilePath + folderContent[i]);

            if (currentFile.isFile()) {
                files.add(currentFile);
            } else {
                getFilesFromFolder(currentFilePath + folderContent[i]);
            }
        }
    }
}
