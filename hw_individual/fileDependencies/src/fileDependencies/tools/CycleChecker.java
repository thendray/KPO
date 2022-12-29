package fileDependencies.tools;

import fileDependencies.models.FileGraph;

import java.util.HashMap;
import java.util.Map;

public class CycleChecker {

    /**
     * check has the graph cycle or not
     * @param graph - graph for checking
     * @return - map with key = file`s paths which include in graph and with value = true|false - is file in cycle or not
     */
    public static Map<String, Boolean> hasGraphCycle(FileGraph graph) {
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, Boolean> beingVisitedNow = new HashMap<>();
        Map<String, Boolean> isInCycle = new HashMap<>();

        for (String filePath : graph.getKeys()) {
            visited.put(filePath, false);
            beingVisitedNow.put(filePath, false);
            isInCycle.put(filePath, false);
        }

        for (String filePath : graph.getKeys()) {
            if (!visited.get(filePath) && hasGraphCycleHelper(filePath, visited, graph, beingVisitedNow, isInCycle)) {
                return isInCycle;
            }
        }

        return null;
    }

    /**
     * recursive algorithm for searching cycles
     * @param filePath - current file path
     * @param visited - map with information about has been file visited or not
     * @param graph - graph which is checking
     * @param beingVisitedNow - map with information about check status of current file
     * @param isInCycle - map with information about files which include in cycle
     * @return - true|false - has cycle or not
     */
    private static boolean hasGraphCycleHelper(String filePath, Map<String, Boolean> visited, FileGraph graph,
                                               Map<String, Boolean> beingVisitedNow, Map<String, Boolean> isInCycle) {

        beingVisitedNow.replace(filePath, true);

        for (String require : graph.getByKey(filePath)) {

            if (beingVisitedNow.get(require)) {
                isInCycle.replace(require, true);
                return true;
            } else if (!visited.get(require) && hasGraphCycleHelper(require, visited, graph, beingVisitedNow, isInCycle)) {
                isInCycle.replace(require, true);
                return true;
            }
        }

        beingVisitedNow.replace(filePath, false);
        visited.replace(filePath, true);
        return false;
    }

}
