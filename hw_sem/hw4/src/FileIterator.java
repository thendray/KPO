import java.io.*;
import java.util.Iterator;

class FileIterator implements Iterator<String> {
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
        try {
            nextLine = bufferReader.readLine();
        } catch (RuntimeException ex) {
            bufferReader.close();
        }
    }

    /**
     * Check has the buffer one more line
     * @return true if buffer has new line, else return false
     */
    @Override
    public boolean hasNext() {
        return nextLine != null;
    }

    /**
     * Get next line from buffer
     * @return String if all is good or throw RunTimeException if smth goes wrong with readline from buffer
     */
    @Override
    public String next() {
        String next_line = this.nextLine;

        try {
            this.nextLine = bufferReader.readLine();
        } catch (IOException e) {
            try {
                bufferReader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return next_line;
    }
}
