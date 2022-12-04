package processing;

import java.util.Hashtable;
import java.util.List;

import trace.Capture;
import trace.Packet;

public class UniqueTcpPorts {
    
    public static void inspectUniqueTcpPorts (Capture capture) {
        Hashtable<String, Integer> tcpPortsTable = tcpPortsTable(capture.getPackets());
        System.out.println(String.format("There are %d unique TCP ports in this trace, " +
                                         "from which the %sports are well known services", tcpPortsTable.size(), wellKnownServices(tcpPortsTable) ) );
    }

    private static Hashtable<String, Integer> tcpPortsTable (List<Packet> packets) {
        Hashtable<String, Integer> tcpPortsTable = new Hashtable<>();
        for (Packet p : packets) {
            if (p.getProtocol().equals("TCP") && tcpPortsTable.containsKey(p.getSourcePort())) {
                tcpPortsTable.put(p.getSourcePort(), tcpPortsTable.get(p.getSourcePort()) +1);
            }
            else if (p.getProtocol().equals("TCP")) {
                tcpPortsTable.put(p.getSourcePort(), 1);
            }
        } 
        return tcpPortsTable;
    }

    private static String wellKnownServices (Hashtable<String, Integer> tcpPortsTable) {
        StringBuilder builder = new StringBuilder();
        tcpPortsTable.forEach((key, value) -> {
                                if (Integer.parseInt(key) <= 1023) {
                                    builder.append(key + ", ");
                                }
                              });
        builder.delete(builder.length()-2, builder.length()-1);

        return builder.toString();
    }
}