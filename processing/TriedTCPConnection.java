package processing;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import trace.Capture;
import trace.Packet;
import trace.TCPFlag;
import util.MenuUtils;

public class TriedTCPConnection {

    public static void inspectTriedTcpConnections(Capture capture) {
        HashMap<String, Integer> tcpTries = getTCPTries(capture.getPackets());
        String sourceWithMaxTries = findSourceWithMaxTries(tcpTries);
        MenuUtils.showOutput(
            String.format(
                "Number of TCP conn. tries:\t%d\nSource who tried the most:\t%s (%s times)",
                sumTries(tcpTries), sourceWithMaxTries, tcpTries.get(sourceWithMaxTries)
            )
        );
    }

    private static HashMap<String, Integer> getTCPTries(List<Packet> packets) {
        HashMap<String, Integer> tries = new HashMap<>();
        for (Packet packet : packets) {
            if (TCPFlag.parseHex(packet.getFlags()) == TCPFlag.SYN) {
                String sourceIP = packet.getSourceIP();
                tries.putIfAbsent(sourceIP, 0);
                tries.put(sourceIP, tries.get(sourceIP) + 1);
            }
        }
        return tries;
    }

    private static int sumTries(HashMap<String, Integer> tcpTries) {
        int acc = 0;
        for (Integer value : tcpTries.values())
            acc += value;
        return acc;
    }

    private static String findSourceWithMaxTries(HashMap<String, Integer> tcpTries) {
        int max = -1;
        String targetSource = new String();
        for (Map.Entry<String, Integer> set : tcpTries.entrySet()) { 
            int tries = set.getValue();
            if (tries > max) {
                max = tries;
                targetSource = set.getKey();
            }
        }
        return targetSource;
    }
}