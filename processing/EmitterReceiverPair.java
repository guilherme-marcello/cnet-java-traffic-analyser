package processing;

import trace.Capture;
import trace.Packet;
import util.Models;
import util.MenuUtils;
import java.util.List;

public class EmitterReceiverPair {
    private static String classifyAddress(String address) {
        if (Models.isValidIPv4(address))
            return "IPv4";
        if (Models.isValidIPv6(address))
            return "IPv6";
        return "Hostname";
    }

    public static void inspectAddresses(Capture capture) {
		List<Packet> packets = capture.getPackets();
		int ipv4Counter = 0, ipv6Counter = 0, hostnameCounter = 0;
		
		for (Packet packet : packets) {
			String sourceType = classifyAddress(packet.getSourceIP());
			String destinationType = classifyAddress(packet.getDestinationIP());
            if (sourceType.equals(destinationType)) {
                if (sourceType.equals("IPv4"))
                    ipv4Counter++;
                else if (sourceType.equals("IPv6"))
                    ipv6Counter++;
                else if (sourceType.equals("Hostname"))
                    hostnameCounter++;
            }	
		}
        MenuUtils.showOutput(
            String.format(
                "Number of packets with receiver and sender using IPv4 addr.:\t%s\n" +
                "Number of packets with receiver and sender using IPv6 addr.:\t%s\n" +
                "Number of packets with receiver and sender using hostnames:\t%s",
                ipv4Counter, ipv6Counter, hostnameCounter
            )
        );
	}
}