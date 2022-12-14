import java.io.*;
import java.util.Iterator;

class FileIterator implements Iterator<String> {
    private String next_line;
    private final BufferedReader BUFFER_READER;

    public FileIterator(String path) throws IOException {
        File file = new File(path);

        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("no such file");
        } else {
            BUFFER_READER = new BufferedReader(new FileReader(file));
        }
        next_line = BUFFER_READER.readLine();
    }

    @Override
    public boolean hasNext() {
        return next_line != null;
    }

    @Override
    public String next() {
        String next_line = this.next_line;

        try {
            this.next_line = BUFFER_READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return next_line;
    }
}
