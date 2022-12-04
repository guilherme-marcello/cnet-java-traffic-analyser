package util;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class ClusteringUtils {
    public interface partitionerFunction {
        int classify(int x);
    }

    public static List<List<Integer>> findClusters(List<Integer> list, partitionerFunction function) {
        HashMap<Integer,List<Integer>> clusters = new HashMap<>();
        for (Integer value : list) {
            int clusterId = function.classify(value);
            clusters.putIfAbsent(clusterId, new LinkedList<Integer>());
            clusters.get(clusterId).add(value);
        }
        return new LinkedList<>(clusters.values());
    }
}