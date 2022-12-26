package fileDependencies.tools;

import java.io.*;

public class FileIterator implements AutoCloseable {
    private String nextLine;
    private final BufferedReader bufferReader;

    /**
     * Create a buffer reader if all good with path to file, else throw FileNotFoundException
     * Then get a first line in file
     * @param path path to file
     * @throws IOException FileNotFound if smth wrong with path and IOEcxeption if smth wrong with readLine from buffer
     */
    public FileIterator(String path) throws IOException {
        File file = new File(path);

        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("no such file");
        } else {
            bufferReader = new BufferedReader(new FileReader(file));
        }

        this.nextLine = bufferReader.readLine();
    }

    /**
     * Constructor who create a buffer reader by getting file
     * @param file - File which is needed to read
     * @throws IOException - if smth goes wrong with readLine from buffer
     */
    public FileIterator(File file) throws IOException {
        bufferReader = new BufferedReader(new FileReader(file));
        this.nextLine = bufferReader.readLine();
    }

    /**
     * Check has the buffer one more line
     * @return true if buffer has new line, else return false
     */
    public boolean hasNext() {
        return nextLine != null;
    }

    /**
     * Get next line from buffer
     * @return String if all is good or throw RunTimeException if smth goes wrong with readline from buffer
     */
    public String next() {
        String nextLine = this.nextLine;

        try {
            this.nextLine = bufferReader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return nextLine;
    }

    public void close() throws IOException {
        bufferReader.close();
    }
}
