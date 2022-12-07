package processing;

import trace.Capture;
import trace.Packet;
import util.ClusteringUtils;
import util.MenuUtils;
import java.util.HashMap;
import java.util.List;

public class TrafficPerIP {
    private static int getTrafficAmount(List<Packet> packets) {
        int sum = 0;
        for (Packet packet : packets)
            sum += packet.getLength();
        return sum;
    }

    private static List<Packet> getHeavierList(List<List<Packet>> listOfPackets) {
        int maxTraffic = 0;
        List<Packet> heavierList = null;
        for (List<Packet> packets : listOfPackets) {
            int trafficSum = getTrafficAmount(packets);
            if (maxTraffic < trafficSum) {
                heavierList = packets;
                maxTraffic = trafficSum;
            }
        }
        return heavierList;
    }

    private static Double calculateThroughput(int amountOfTraffic, Double time) {
        return amountOfTraffic / time;
    }

    public static void inspectEmitters(Capture capture) {
        List<Packet> packets = capture.getPackets(); 
        List<List<Packet>> clusters = ClusteringUtils.findClusters(
            packets, (x) -> ((Packet) x).getSourceIP().hashCode()
        );
        List<Packet> sentMoreTraffic = getHeavierList(clusters);
        int amountOfTraffic = getTrafficAmount(sentMoreTraffic);
        MenuUtils.showOutput(
            String.format(
                "Internet Protocol address:\t%s\nAmount of emitted traffic:\t%s bytes\nNumber of emitted packets:\t%s\nEstimated network throughput:\t%.2f bits/s",
                sentMoreTraffic.get(0).getSourceIP(), amountOfTraffic, sentMoreTraffic.size(), calculateThroughput(amountOfTraffic / 8, capture.findTraceTime())
            )
        );
    }

    public static void inspectReceivers(Capture capture) {
        List<Packet> packets = capture.getPackets(); 
        List<List<Packet>> clusters = ClusteringUtils.findClusters(
            packets, (x) -> ((Packet) x).getDestinationIP().hashCode()
        );
        List<Packet> receivedMoreTraffic = getHeavierList(clusters);
        int amountOfTraffic = getTrafficAmount(receivedMoreTraffic);
        MenuUtils.showOutput(
            String.format(
                "Internet Protocol address:\t%s\nAmount of received traffic:\t%s bytes\nNumber of received packets:\t%s\nEstimated network throughput:\t%.2f bits/s",
                receivedMoreTraffic.get(0).getDestinationIP(), amountOfTraffic, receivedMoreTraffic.size(), calculateThroughput(amountOfTraffic / 8, capture.findTraceTime())
            )
        );
    }

}