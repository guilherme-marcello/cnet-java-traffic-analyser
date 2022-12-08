package processing;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import trace.Capture;
import trace.Packet;
import trace.TCPFlag;
import util.MenuUtils;
/**
 * this class provides methods to check the number of tried TCP connections in a given Capture
 * and the ip that did the most trys 
 */
public class TriedTCPConnection {

    /**
     * returns a text answer with the number of tried TCP connections in a given Capture
     * and the ip that did the most trys
     * @param capture the capture with the data to analyse
     * @return a text answer with the number of tried TCP connections in a given Capture
     * and the ip that did the most trys
     */
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

    /**
     * Returns a HashMap were the keys are the ips that have tried to make a TCP connections 
     * and the value is the number of connection trys made by that ip, a tried TCP connction 
     * being a Packet that has a flag value of TCPFlag.SYN
     * @param packets the list of Packet to analyse
     * @return a HashMap were the keys are the ips that have tried to make a TCP connections 
     * and the value is the number of connection trys made by that ip
     */
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

    /**
     * Returns the sum of all tcp tries in a given HashMap, that being the sum of all 
     * stored values in the HashMap
     * @param tcpTries the HashMap to analyse
     * @return the sum of all tcp tries in a given HashMap, that being the sum of all 
     * stored values in the HashMap
     */
    private static int sumTries(HashMap<String, Integer> tcpTries) {
        int acc = 0;
        for (Integer value : tcpTries.values())
            acc += value;
        return acc;
    }

    /**
     * Finds the ip that has tried to establish the most tcp connections, that being the
     *  ip key with the biggest integer value in a given HashMap
     * @param tcpTries the HashMap to analyse
     * @return the ip that has tried to establish the most tcp connections
     */
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