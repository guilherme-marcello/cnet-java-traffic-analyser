package processing;

import trace.Capture;
import trace.Packet;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ControlMessagePacket {
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

	public static void inspectICMP(Capture capture) {
		List<Packet> packets = capture.getPackets();
		int icmpCounter = 0;
		Set<Integer> icmpTypes = new HashSet<>(); 
		for (Packet packet : packets) {
			String protocol = packet.getProtocol();
			String icmpType = packet.getICMPtype();
	
			if (isNumeric(icmpType) && (protocol.equals("ICMP") || protocol.equals("ICMPv6"))) {
				icmpCounter++;
                icmpTypes.add(Integer.valueOf(icmpType));
            }	
		}
        System.out.println(
            String.format(
                "[BEGIN - OUTPUT]\nNumber of ICMP packets:\t%s\nFound ICMP types:\t%s\n[END - OUTPUT]",
                icmpCounter, icmpTypes.toString()
            )
        );
	}
}