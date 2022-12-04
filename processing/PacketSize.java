package processing;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import trace.Capture;
import util.ClusteringUtils;
import util.MathUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PacketSize {
    private static void showGeneralStats(List<Integer> orderedList) {
        int size = orderedList.size();
        int min = orderedList.get(0);
        int max = orderedList.get(size - 1);
        int sum = 0;
        for (Integer value : orderedList)
            sum += value;
        int mean = sum / size;
        System.out.println(
            String.format(
                "[BEGIN - OUTPUT]\nMinimum:\t%s\nMaximum:\t%s\nAr. Mean:\t%s\n[END - OUTPUT]",
                min, max, mean
            )
        );
    }

    private static void writeBarplotData(List<List<Integer>> clusters, int size) {
        HashMap<String, Double> packetClusters = new HashMap<>(); 
        for (List<Integer> cluster : clusters) {
            packetClusters.put(
                String.format("%d-%d", cluster.get(0), cluster.get(cluster.size() - 1)),
                cluster.size() / (size * 1.0)
            );
        }
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(
            String.format("samples/data_%s.csv", 
            new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()))
        ));) {
            for (String sizeRange : packetClusters.keySet())
                pw.print(sizeRange + ",");
            pw.print("\n");
            for (Double sizePercentage : packetClusters.values())
                pw.print(String.format("%6.5f,", sizePercentage));
                
        } catch (Exception e) {
        }
    }

    public static void inspectPacketsSize(Capture capture) {
        List<Integer> packetsLength = capture.getPacketsLength();
        int numberOfPackets = packetsLength.size();
        Collections.sort(packetsLength);
        showGeneralStats(packetsLength);
        List<List<Integer>> clusters = ClusteringUtils.findClusters(
            packetsLength, (x) -> MathUtils.log2(x / 80)
        );
        writeBarplotData(clusters, numberOfPackets);
    }
}