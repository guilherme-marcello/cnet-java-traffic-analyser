package processing;

import trace.Capture;
import trace.Packet;
import util.ClusteringUtils;
import util.MenuUtils;
import java.util.HashMap;
import java.util.List;

/**
 * this class provides methods to process a Capture and get information related to emitter and receiver who sent/received more traffic
 */
public class TrafficPerIP {
	/**
	 * Calculate the sum of packet's length
	 * @param packets   Sample of Packet to analyse
	 * @requires packets != null
     * @ensures {\result >= 0}
     * @return sum of packet's length
	 */
    private static int getTrafficAmount(List<Packet> packets) {
        int sum = 0;
        // for each packet, add its length to sum
        for (Packet packet : packets)
            sum += packet.getLength();
        return sum;
    }

	/**
	 * From a list of groups of Packet, return the List with more traffic
	 * @param listOfPackets   List of groups of Packet
	 * @requires listOfPackets != null
     * @return heavier list
	 */
    private static List<Packet> getHeavierList(List<List<Packet>> listOfPackets) {
        int maxTraffic = 0;
        List<Packet> heavierList = null;
        // for each group of packet, verify if traffic amount is greater than current max
        // update heavierList address to new heavier and update maxTraffic with current max
        for (List<Packet> packets : listOfPackets) {
            int trafficSum = getTrafficAmount(packets);
            if (maxTraffic < trafficSum) {
                heavierList = packets;
                maxTraffic = trafficSum;
            }
        }
        return heavierList;
    }

	/**
	 * From a list of groups of Packet, return the List with more traffic
	 * @param amountOfTraffic   Amount of traffic
     * @param time              Time between begin and end of traffic
     * @requires time != null
     * @return throughput
	 */
    private static Double calculateThroughput(int amountOfTraffic, Double time) {
        return amountOfTraffic / time;
    }

	/**
	 * From a capture, aggregate packets by source IP, find group with more traffic and print a network report of this emitter
	 * @param capture   Capture to analyse
     * @requires capture != null
	 */
    public static void inspectEmitters(Capture capture) {
        List<Packet> packets = capture.getPackets(); 
        // find clusters inside capture, where each packet is classified (receiving an ID) by the hashcode of its sourceIP
        List<List<Packet>> clusters = ClusteringUtils.findClusters(
            packets, (x) -> ((Packet) x).getSourceIP().hashCode()
        );
        // find, among all clusters, the group that contains the higher amount of traffic
        List<Packet> sentMoreTraffic = getHeavierList(clusters);
        int amountOfTraffic = getTrafficAmount(sentMoreTraffic);
        // print to console the found information about this emitter
        MenuUtils.showOutput(
            String.format(
                "Internet Protocol address:\t%s\nAmount of emitted traffic:\t%s bytes\nNumber of emitted packets:\t%s\nEstimated network throughput:\t%.2f bits/s",
                sentMoreTraffic.get(0).getSourceIP(), amountOfTraffic, sentMoreTraffic.size(), calculateThroughput(amountOfTraffic / 8, capture.findTraceTime())
            )
        );
    }

	/**
	 * From a capture, aggregate packets by destination IP, find group with more traffic and print a network report of this receiver
	 * @param capture   Capture to analyse
     * @requires capture != null
	 */
    public static void inspectReceivers(Capture capture) {
        List<Packet> packets = capture.getPackets(); 
        // find clusters inside capture, where each packet is classified (receiving an ID) by the hashcode of its sourceIP
        List<List<Packet>> clusters = ClusteringUtils.findClusters(
            packets, (x) -> ((Packet) x).getDestinationIP().hashCode()
        );
         // find, among all clusters, the group that contains the higher amount of traffic
        List<Packet> receivedMoreTraffic = getHeavierList(clusters);
        int amountOfTraffic = getTrafficAmount(receivedMoreTraffic);
        // print to console the found information about this receiver
        MenuUtils.showOutput(
            String.format(
                "Internet Protocol address:\t%s\nAmount of received traffic:\t%s bytes\nNumber of received packets:\t%s\nEstimated network throughput:\t%.2f bits/s",
                receivedMoreTraffic.get(0).getDestinationIP(), amountOfTraffic, receivedMoreTraffic.size(), calculateThroughput(amountOfTraffic / 8, capture.findTraceTime())
            )
        );
    }

}