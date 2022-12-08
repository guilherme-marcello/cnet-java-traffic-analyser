package processing;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import trace.Capture;
import trace.Packet;
import util.ClusteringUtils;
import util.MathUtils;
import util.MenuUtils;

/**
 * this class provides methods to process a Capture and get relevant information related to packets size
 */
public class PacketSize {
	/**
	 * Find min, max and mean length value of the given list of packets
	 * @param packets   Sample of Packet to analyse
	 * @requires packets != null
     * @ensures {\result.length == 3}
     * @return a array of int with values {min, max, mean} of the sample
	 */
    private static int[] findMinMaxMean(List<Packet> packets) {
        int min = Integer.MAX_VALUE; 
        int max = 0, sum = 0, n = 0;   
        // for each packet, get its size, increment this value to sum and update min/max variables
        for (Packet packet : packets) {
            int packetSize = packet.getLength();
            if (packetSize > max) max = packetSize;
            if (packetSize < min) min = packetSize;
            sum += packetSize;
            n++;
        }
        return new int[] {min, max, sum / n};     
    }

	/**
	 * Print general length-related stats of the given list of Packet
	 * @param packets   Sample of Packet to analyse
	 * @requires packets != null
	 */
    private static void showGeneralStats(List<Packet> packets) {
        int[] minMaxMean = findMinMaxMean(packets);
        MenuUtils.showOutput(
            String.format(
                "Minimum:\t%s bytes\nMaximum:\t%s bytes\nAr. Mean:\t%s bytes",
                minMaxMean[0], minMaxMean[1], minMaxMean[2]
            )
        );
    }

	/**
	 * Receive groups of Packet, find min-max of each group and write this alongside frequency percentage to a CSV file
	 * @param clusters  List of packet groups to plot
     * @param size      Total amount of Packet (to create percentages)    
	 * @requires size != 0
	 */
    private static void writeBarplotData(List<List<Packet>> clusters, int size) {
        HashMap<String, Double> packetClusters = new HashMap<>(); 
        for (List<Packet> cluster : clusters) {
            // for each Packet group, put min-max as key and absolute frequency as value into packetClusters
            int[] minMaxMean = findMinMaxMean(cluster);
            packetClusters.put(
                String.format("%d-%d", minMaxMean[0], minMaxMean[1]),
                cluster.size() / (size * 1.0)
            );
        }
        // once [min-max]->frequency map is finished, prepare to write CSV file
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(
            String.format("samples/data_%s.csv", 
            new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()))
        ));) {
            // (first row) print each [min-max] followed by a comma
            for (String sizeRange : packetClusters.keySet())
                pw.print(sizeRange + ",");
            pw.print("\n");
            // (second row) print each frequency percentage followed by a comma
            for (Double sizePercentage : packetClusters.values())
                pw.print(String.format(Locale.US, "%6.5f,", sizePercentage * 100));
                
        } catch (Exception e) {
        }
    }

	/**
	 * Print length-stats, aggregate packets by packet size and export barplot data as a CSV file
	 * @param capture   Capture to analyse
	 * @requires capture != null
	 */
    public static void inspectPacketsSize(Capture capture) {
        List<Packet> packets = capture.getPackets();
        showGeneralStats(packets);
        // find clusters inside packets List, where each packet is classified (receiving an ID) by the expression: log2(packetSize / 80)
        List<List<Packet>> clusters = ClusteringUtils.findClusters(
            packets, (x) -> MathUtils.log2(((Packet) x).getLength() / 80)
        );
        // write barplot data, from generated clusters
        writeBarplotData(clusters, packets.size());
    }
}