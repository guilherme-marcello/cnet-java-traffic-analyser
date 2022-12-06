package processing;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import trace.Capture;
import trace.Packet;
import trace.TCPService;

public class TCPPorts {
    public static void inspectPortsAndServices(Capture capture) {
        HashMap<String, Integer> portsHit = getTcpPorts(capture.getPackets());
        System.out.println(
            String.format(
                "[BEGIN - OUTPUT]\nNumber of unique TCP ports:\t%d\nFound well known services:\t%s\n[END - OUTPUT]",
                portsHit.size(), extractWellKnownServices(portsHit.keySet())
            )
        );
    }

    private static HashMap<String, Integer> getTcpPorts(List<Packet> packets) {
        HashMap<String, Integer> portsHit = new HashMap<>();
        for (Packet packet : packets) {
            if (packet.getProtocol().equals("TCP")) {
                String serverPort = packet.getDestinationPort();
                String clientPort = packet.getSourcePort();
                portsHit.putIfAbsent(clientPort, 0);
                portsHit.put(clientPort, portsHit.get(clientPort) + 1);
                portsHit.putIfAbsent(serverPort, 0);
                portsHit.put(serverPort, portsHit.get(serverPort) + 1);
            }
        } 
        return portsHit;
    }

    private static String extractWellKnownServices(Set<String> ports) {
        Set<TCPService> foundServices = new HashSet<>(); 
        for (String port : ports) {
            TCPService service = TCPService.ports.get(port);
            if (service != null) foundServices.add(service);
        }
        return foundServices.toString();
    }
}