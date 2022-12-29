package fileDependencies.tools;

import fileDependencies.models.FileGraph;

import java.util.*;

public class TopologicalSort {

    public static List<String> topologicalSort(FileGraph graph) {

        List<String> filePathInOrder = new ArrayList<>();

        Map<String, Boolean> visited = new HashMap<>();

        for (String key : graph.getKeys()) {
            visited.put(key, false);
        }

        for (String filePath : graph.getKeys()) {

            if (!visited.get(filePath)) {
                topologicalSortHelper(graph, filePath, visited, filePathInOrder);
            }
        }

        return filePathInOrder;
    }

    private static void topologicalSortHelper(FileGraph graph, String filePath,
                                              Map<String, Boolean> visited, List<String> filePathInOrder) {

        visited.replace(filePath, true);

        for (String dependenceFile : graph.getByKey(filePath)) {
            if (!visited.get(dependenceFile)) {
                topologicalSortHelper(graph, dependenceFile, visited, filePathInOrder);
            }
        }

        filePathInOrder.add(filePath);
    }

}
