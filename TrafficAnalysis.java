import java.util.List;

public class TrafficAnalysis {
    public static void main(String[] args) {
        List<Packet> capture = Util.readTrace("traces/traceA.csv");
        System.out.println(capture);
    }
}