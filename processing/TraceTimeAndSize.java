package processing;

import java.util.List;

import trace.Capture;
import trace.Packet;

public class TraceTimeAndSize {
    public static void inspectTraceTimeAndSize (Capture capture) {
        System.out.println(String.format("Total trace time: %s \n" +
                                         "Total trace size: %s", traceTime(capture.getPackets()), traceSize(capture.getPackets())));
    }

    private static String traceTime (List<Packet> packets) {
        return packets.get(packets.size()-1).getTime();
    }

    private static String traceSize (List<Packet> packets) {
        return packets.get(packets.size()-1).getPacketNumer();
    }
}