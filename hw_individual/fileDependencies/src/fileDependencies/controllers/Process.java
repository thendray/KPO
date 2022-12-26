package fileDependencies.controllers;

import java.io.File;
import java.util.List;

public class Process {

    private final String rootPath;

    private List<File> files;

    public Process(String path) {
        rootPath = path;
    }

    public void processStart() {

        getFilesFromFolder(rootPath);

        readFilesAndFindDependencies();

    }

    private void readFilesAndFindDependencies() {

        for (File currentFile : files) {

        }

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
