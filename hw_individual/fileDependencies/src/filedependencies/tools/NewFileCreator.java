package filedependencies.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewFileCreator {

    private final List<String> filePathsToCombine;

    public NewFileCreator(List<String> filePathsToCombine) {
        this.filePathsToCombine = new ArrayList<>(filePathsToCombine);
    }


    /**
     * create a new file and write some text in this file
     * @param pathWhereCreate - path for creating file
     * @throws IOException - throws if smth goes wrong with reading files and writing
     */
    public void createNewFile(String pathWhereCreate) throws IOException {

        List<String> linesForWrite = new ArrayList<>();

        for (String path : this.filePathsToCombine) {
            try (FileIterator fileIterator = new FileIterator(path)) {
                while (fileIterator.hasNext()) {
                    linesForWrite.add(fileIterator.next());
                }
            }
        }

        System.out.println("Результат работы программы в консоль: ");
        System.out.println(String.join("\n", linesForWrite));

        try (FileWriter fileWriter = new FileWriter(pathWhereCreate, false)) {
            fileWriter.write(String.join("\n", linesForWrite));
        } catch (IOException exception) {
            System.out.println("Возникли проблемы при записи информации в файл!");
            throw exception;
        }
    }
}
