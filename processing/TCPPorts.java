package processing;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import trace.Capture;
import trace.Packet;
import trace.TCPService;
import util.MenuUtils;

/**
 * this class provides a way to find the number of unique TCP ports, and which ones are well known services
 * in a given Capture
 */
public class TCPPorts {

    /**
     * Returns a text answer with the number of unique TCP ports
     * and which ones are well known services in a given Capture
     * @param capture Capture to analyse
     */
    public static void inspectPortsAndServices(Capture capture) {
        HashMap<String, Integer> portsHit = getTcpPorts(capture.getPackets());
        MenuUtils.showOutput(
            String.format(
                "Number of unique TCP ports:\t%d\nFound well known services:\t%s",
                portsHit.size(), extractWellKnownServices(portsHit.keySet())
            )
        );
    }

    /**
     * Returns a HashTable where the key is a Tcp port in a Packet and the value is the
     * number of times that that tcp port has appered in a given list of Packets 
     * @param packets the list of Packet to analyse
     * @return a HashTable where the key is a Tcp port in a Packet and the value is the
     * number of times that that tcp port has appered
     */
    private static HashMap<String, Integer> getTcpPorts(List<Packet> packets) {
        HashMap<String, Integer> portsHit = new HashMap<>();
        for (Packet packet : packets) {
            if (packet.getProtocol().equals("TCP")) {
                String serverPort = packet.getDestinationPort();
                String clientPort = packet.getSourcePort();
                portsHit.putIfAbsent(clientPort, 0);
                portsHit.put(clientPort, portsHit.get(clientPort) + 1);
            }
        } 
        return portsHit;
    }

    /**
     * Returns a String with the well known services in a given set of Strings
     * @param ports the Set to analyse
     * @return a String with the well known services in a given set of Strings
     */
    private static String extractWellKnownServices(Set<String> ports) {
        Set<TCPService> foundServices = new HashSet<>(); 
        for (String port : ports) {
            TCPService service = TCPService.ports.get(port);
            if (service != null) foundServices.add(service);
        }
        return foundServices.toString();
    }
}
