package fileDependencies.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreateNewFile {

    private final List<String> filePathsToCombine;

    public CreateNewFile(List<String> filePathsToCombine) {
        this.filePathsToCombine = filePathsToCombine;
    }


    public void createNewFile(String pathWhereCreate) throws IOException {

        Path pathNewFile = Paths.get(pathWhereCreate);

        List<String> linesForWrite = new ArrayList<>();


        for (String path : filePathsToCombine) {
            try (FileIterator fileIterator = new FileIterator(path)) {
                while (fileIterator.hasNext()) {
                    linesForWrite.add(fileIterator.next());
                }
            }
        }

        try {
            Files.write(pathNewFile, linesForWrite, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            System.out.println("Возникли проблемы при записи информации в файл!");
            throw exception;
        }
    }

}
