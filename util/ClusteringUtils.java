package util;

import trace.Packet;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class ClusteringUtils {
    public interface partitionerFunction<T> {
        int classify(T x);
    }

    public static List<List<Packet>> findClusters(List<Packet> packets, partitionerFunction function) {
        HashMap<Integer,List<Packet>> clusters = new HashMap<>();
        for (Packet packet : packets) {
            int clusterId = function.classify(packet);
            clusters.putIfAbsent(clusterId, new LinkedList<Packet>());
            clusters.get(clusterId).add(packet);
        }
        return new LinkedList<>(clusters.values());
    }
}