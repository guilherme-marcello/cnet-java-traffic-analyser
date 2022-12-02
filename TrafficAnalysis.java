import java.util.List;
import java.util.Collections;
import java.util.HashMap;

public class TrafficAnalysis {

    private static HashMap<String,Integer> inspectPacketsSize(Capture capture) {
        HashMap<String,Integer> report = new HashMap<>();
        List<Integer> packetsLength = capture.getPacketsLength();
        Collections.sort(packetsLength);
        int min = packetsLength.get(0);
        int max = packetsLength.get(packetsLength.size() - 1);
        int sum = 0;
        for (Integer size : packetsLength)
            sum += size;
        int mean = sum / packetsLength.size();

        report.put("Min", min);
        report.put("Max", max);
        report.put("Mean", mean);
        return report;
    }

    public static void main(String[] args) {
        Capture capture = Util.readTrace("traces/traceA.csv");
        System.out.println(capture);
        System.out.println(inspectPacketsSize(capture));


    }
}