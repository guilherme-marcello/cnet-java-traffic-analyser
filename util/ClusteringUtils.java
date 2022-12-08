package util;

import trace.Packet;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

/**
 * this class provides methods to classify and group packets
 */
public class ClusteringUtils {
    // interface of a function that classify a Object and return an ID
    public interface partitionerFunction<T> {
        /**
         * @param x given object
         * @requires x != null
         * @return id of given object
         */
        int classify(T x);
    }

    /**
     * Aggregate packets in a List by applying a specific function and return generated clusters
     * @param packets   List of packets to divide in subgroups
     * @param function  A function to classify each packet
     * @return list of groups created from original list
     */
    public static List<List<Packet>> findClusters(List<Packet> packets, partitionerFunction function) {
        HashMap<Integer,List<Packet>> clusters = new HashMap<>();
        for (Packet packet : packets) {
            // get a clusterId for each packet
            int clusterId = function.classify(packet);
            // add packet to a map [clusterId]->packets; create new entry if needed and add this packet to correct entry
            clusters.putIfAbsent(clusterId, new LinkedList<Packet>());
            clusters.get(clusterId).add(packet);
        }
        // return list of created groups
        return new LinkedList<>(clusters.values());
    }
}