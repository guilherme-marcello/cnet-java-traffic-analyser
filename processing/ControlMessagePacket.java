package processing;

import trace.Capture;
import trace.Packet;
import util.MenuUtils;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


/**
 * this class provides methods and functions to process a capture and get information about Internet Control Messages
 */
public class ControlMessagePacket {
    /**
     * Given a string, verify if it is numeric
     * @param str	String to evaluate
     * @return logic value of "str is numeric"
     */
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    /**
     * Given a capture, count number of ICMP packets and found ICMP types
     * @param capture	Capture to evaluate
     */
    public static void inspectICMP(Capture capture) {
        List<Packet> packets = capture.getPackets();
        int icmpCounter = 0; // start conunting from 0
        Set<Integer> icmpTypes = new HashSet<>(); 
        for (Packet packet : packets) {
            // for each packet, get protocol and icmp type.
            String protocol = packet.getProtocol();
            String icmpType = packet.getICMPtype();  
            // if icmpType is numeric and protocol is ICMP, increment count and add found type to a Set
            if (isNumeric(icmpType) && (protocol.equals("ICMP") || protocol.equals("ICMPv6"))) {
                icmpCounter++;
                icmpTypes.add(Integer.valueOf(icmpType));
             }	
        }
	// show module output: count of icmp packets and set of found icmp types
        MenuUtils.showOutput(
            String.format(
                "Number of ICMP packets:\t%s\nFound ICMP types:\t%s",
                icmpCounter, icmpTypes.toString()
            )
        );
	}
}
