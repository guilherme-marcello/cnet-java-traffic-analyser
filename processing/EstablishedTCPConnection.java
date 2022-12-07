package processing;

import trace.TCPFlag;
import trace.Capture;
import trace.Packet;
import util.MenuUtils;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class EstablishedTCPConnection {

    public static void inspectEstablishedTcpConnections(Capture capture) {
        List<Packet> packets = capture.getPackets();
        Set<String> waitingForSynAck = new HashSet<>();
        Set<String> waitingForAck = new HashSet<>();
        int count = 0;
        
        for (Packet packet : packets) {
            String sourceId = packet.getSourceIP() + packet.getSourcePort();
            String destinationId = packet.getDestinationIP() + packet.getDestinationPort();
            String flag = packet.getFlags();
            if (flag != null) {
                switch (TCPFlag.parseHex(flag)) {
                    case SYN:
                        waitingForSynAck.add(sourceId + destinationId);
                        break;
                    case SYNACK:
                        if (waitingForSynAck.contains(destinationId + sourceId)) {
                            waitingForAck.add(destinationId + sourceId);
                            waitingForSynAck.remove(destinationId + sourceId);
                        }
                        break;
                    case ACK:
                        if (waitingForAck.contains(sourceId + destinationId)) {
                            count++;
                            waitingForAck.remove(sourceId + destinationId);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        MenuUtils.showOutput(String.format("Number of TCP conn. established:\t%d", count));
    }

}


