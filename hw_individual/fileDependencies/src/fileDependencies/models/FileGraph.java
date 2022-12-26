package fileDependencies.models;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileGraph {

    Map<String, List<String>> fileTable = new HashMap<>();


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

    @Override
    public String toString() {
        String result = "";
        for (String key : fileTable.keySet()) {
            result += key + ": ";
            for (String require : fileTable.get(key)) {
                result += require + "  ";
            }
            result += "\n";
        }
        return result;
    }

}
