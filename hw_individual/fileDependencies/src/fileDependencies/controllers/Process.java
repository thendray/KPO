package fileDependencies.controllers;

import fileDependencies.models.FileGraph;
import fileDependencies.tools.FileIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Process {

    private final String rootPath;
    private FileGraph graph;
    private List<File> files;

    public Process(String path) {
        rootPath = path;
        graph = new FileGraph();
        files = new ArrayList<>();
    }

    public void processStart() throws IOException {

        getFilesFromFolder(rootPath);

        for (File f : files) {
            System.out.println(f.getName());
        }

        readFilesAndFindDependencies();

        System.out.println(graph.toString());

    }

    private void readFilesAndFindDependencies() throws IOException {
        String currentLine;
        List<String> requires;

        for (File currentFile : files) {

            try (FileIterator fileIterator = new FileIterator(currentFile)) {

                while (fileIterator.hasNext()) {
                    currentLine = fileIterator.next();
                    requires = getRequires(currentLine);

                    System.out.println(requires.size() + "=======" + currentLine);

                    if (!requires.isEmpty()) {
                        graph.add(currentFile.getPath(), requires);
                    }
                }
            }
        }
    }

    private List<String> getRequires(String currentLine) {
        String patternString = "require '.+'";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(currentLine);

        List<String> requires = new ArrayList<>();

        while (matcher.find()) {
            requires.add(currentLine.substring(matcher.start(), matcher.end()));
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
