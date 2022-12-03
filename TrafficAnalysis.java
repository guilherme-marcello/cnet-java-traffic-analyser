import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class TrafficAnalysis {

    private static HashMap<String,Integer> inspectPacketsSize(Capture capture) {
        HashMap<String,Integer> report = new HashMap<>();
        List<Integer> packetsLength = capture.getPacketsLength();
        int numberOfPackets = packetsLength.size();
        Collections.sort(packetsLength);
        int min = packetsLength.get(0);
        int max = packetsLength.get(numberOfPackets - 1);
        int sum = 0;
        for (Integer size : packetsLength)
            sum += size;
        int mean = sum / numberOfPackets;

        report.put("Min", min);
        report.put("Max", max);
        report.put("Mean", mean);

        List<List<Integer>> clusters = Clustering.findClusters(packetsLength, (x) -> Util.log2(x / 80));
        HashMap<String, Double> packetClusters = new HashMap<>(); 
        for (List<Integer> cluster : clusters) {
            packetClusters.put(
                String.format("%d-%d", cluster.get(0), cluster.get(cluster.size() - 1)),
                cluster.size() / (numberOfPackets * 1.0)
            );
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream("data.csv"));) {
            for (String sizeRange : packetClusters.keySet())
                pw.print(sizeRange + ",");
            pw.print("\n");
            for (Double sizePercentage : packetClusters.values())
                pw.print(String.format("%6.5f,", sizePercentage));
                
        } catch (Exception e) {
        }
        
        return report;
    }

    public static void main(String[] args) {
        Capture capture = Util.readTrace("traces/traceA.csv");
        System.out.println(inspectPacketsSize(capture));
    }
}