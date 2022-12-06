package processing;

import java.util.List;

import trace.Capture;
import trace.Packet;

public class TraceTimeSize {
    public static void inspectTraceTimeAndSize(Capture capture) {
        System.out.println(
            String.format(
                "[BEGIN - OUTPUT]\nTotal trace time:\t%.2f seconds\nTotal trace size:\t%d packets\n[END - OUTPUT]", 
                traceTime(capture), traceSize(capture)
            )
        );
    }

    private static Double traceTime(Capture capture) {
        List<Packet> packets = capture.getPackets();
        Packet firstPacket = packets.get(0);
        Packet lastPacket = packets.get(packets.size() - 1);
        return Double.parseDouble(lastPacket.getTime()) - Double.parseDouble(firstPacket.getTime());
    }

    private static int traceSize(Capture capture) {
        List<Packet> packets = capture.getPackets();
        Packet firstPacket = packets.get(0);
        Packet lastPacket = packets.get(packets.size() - 1);
        return Integer.valueOf(lastPacket.getPacketNumber()) - Integer.valueOf(firstPacket.getPacketNumber());
    }
}