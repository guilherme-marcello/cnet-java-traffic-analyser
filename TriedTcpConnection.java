package processing;

import java.util.Hashtable;
import java.util.List;

import trace.Capture;
import trace.Packet;

public class TriedTcpConnection {
    
    public static void inspectTriedTcpConnections (Capture capture) {
        Hashtable<String, Integer> tcpConnectionsTable = tcpConnectionsTable(capture.getPackets());
        System.out.println(String.format("There was %d tried tcp connections, and the ip that did the most tries is %s"
                                                , connectionsNum(tcpConnectionsTable), persistentIp(tcpConnectionsTable)));
    }

    private static Hashtable<String, Integer> tcpConnectionsTable (List<Packet> packets) {
        Hashtable<String, Integer> tcpConnectionsTable = new Hashtable<>();
        for (Packet p : packets) {
            if (p.getFlags().equals("0x002") && tcpConnectionsTable.containsKey(p.getSourceIp())) {
                tcpConnectionsTable.put(p.getSourceIp(),  tcpConnectionsTable.get(p.getSourceIp()) +1);
            }
            else if (p.getFlags().equals("0x002")) {
                tcpConnectionsTable.put(p.getSourceIp(), 1);
            }
        }

        return tcpConnectionsTable;
    }

    private static int connectionsNum (Hashtable<String, Integer> tcpConnectionsTable) {
        int acc = 0;
        for (Integer value : tcpConnectionsTable.values()) {
            acc += value.intValue();
        }
        return acc;
    }

    private static String persistentIp (Hashtable<String, Integer> tcpConnectionsTable) {
        int maxTmp = -1;
        StringBuilder persistentIp = new StringBuilder();
        for (Integer value : tcpConnectionsTable.values()) {
            if (value.intValue() > maxTmp) {
                maxTmp = value.intValue();
            }
        }
        final int max = maxTmp;
        tcpConnectionsTable.forEach((key, value) -> {
                                    if (tcpConnectionsTable.get(key).intValue() == max) {
                                        persistentIp.append(key);
                                    }
                                    });
        return persistentIp.toString();
    } 

}
