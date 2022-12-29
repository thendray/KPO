package fileDependencies.models;

import java.util.*;

public class FileGraph {

    private final Map<String, List<String>> fileTable;

    public FileGraph(){
        fileTable = new HashMap<>();
    }


    public void add(String mainFilePath, String dependentFilePath) {

        if (!fileTable.containsKey(mainFilePath)) {
            fileTable.put(mainFilePath, new ArrayList<>());
        }

        fileTable.get(mainFilePath).add(dependentFilePath);
    }

    public void add(String mainFilePath, List<String> requires) {
        if (!fileTable.containsKey(mainFilePath)) {
            fileTable.put(mainFilePath, requires);
        } else {
            fileTable.get(mainFilePath).addAll(requires);
        }
    }

    public List<String> getByKey(String key) {
        return fileTable.get(key);
    }

    public Set<String> getKeys() {
        return fileTable.keySet();
    }

    public boolean isExist(String key) {
        return fileTable.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String key : fileTable.keySet()) {
            result.append(key).append(": ");
            for (String require : fileTable.get(key)) {
                result.append(require).append("  ");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
