package processing;

import trace.TCPFlag;
import trace.Capture;
import trace.Packet;
import util.MenuUtils;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

/**
 * this class provides methods and functions to process a capture and get information about established TCP connections
 */
public class EstablishedTCPConnection {
    /**
     * Given a capture, count number of established TCP connections
     * @param capture	Capture to evaluate
     */
    public static void inspectEstablishedTcpConnections(Capture capture) {
        List<Packet> packets = capture.getPackets();
        // create a set of connections waiting for SYNACK and another for connections waiting for ACK
        Set<String> waitingForSynAck = new HashSet<>();
        Set<String> waitingForAck = new HashSet<>();
        int count = 0; // start counting from 0
        for (Packet packet : packets) {
            // for each packet, create a connection ID with (sourceID + destinationID) or (destinationID + sourceID)
            String sourceId = packet.getSourceIP() + packet.getSourcePort();
            String destinationId = packet.getDestinationIP() + packet.getDestinationPort();
            // get hex representation of packet flags
            String flag = packet.getFlags();
            if (flag != null) {
                // parse hex to a TCPFlag and process packet accordingly
                switch (TCPFlag.parseHex(flag)) {
                    case SYN:
                        waitingForSynAck.add(sourceId + destinationId); // add connection ID to waiting for SYNACK
                        break;
                    case SYNACK:
                        if (waitingForSynAck.contains(destinationId + sourceId)) { // verify if connection ID exists in waiting for SYNACK
                            waitingForAck.add(destinationId + sourceId); // if so, add connection ID to waiting for ACK
                            waitingForSynAck.remove(destinationId + sourceId); // and remove connection ID from waiting for SYNACK set
                        }
                        break;
                    case ACK:
                        if (waitingForAck.contains(sourceId + destinationId)) { // verify if connection ID exists in waiting for ACK
                            count++; // if so, increment counter
                            waitingForAck.remove(sourceId + destinationId); // remove connection ID from waiting for ACK set
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        // show module output: number of tcp established connections
        MenuUtils.showOutput(String.format("Number of TCP conn. established:\t%d", count));
    }

}


