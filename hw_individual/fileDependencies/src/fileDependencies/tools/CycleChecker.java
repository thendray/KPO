package fileDependencies.tools;

import fileDependencies.models.FileGraph;

import java.util.HashMap;
import java.util.Map;

public class CycleChecker {

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
