import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileIterator fileIterator;
        String fileName;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            fileName = scanner.next();

            try {
                fileIterator = new FileIterator(fileName);
                break;
            } catch (FileNotFoundException exception) {
                System.out.println(exception.getMessage());
                System.out.println("Введите другое имя файла!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            while (fileIterator.hasNext()) {
                System.out.println(fileIterator.next());
            }
        } catch (NullPointerException exception) {
            System.out.println("OOPs!");
        }
    }
}