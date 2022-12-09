package processing;

import trace.Capture;
import trace.Packet;
import util.Models;
import util.MenuUtils;
import java.util.List;


/**
 * this class provides methods and functions to process a capture and get information about emitter-receiver pairs
 */
public class EmitterReceiverPair {
    /**
     * Given an address, verify if it is IPv4, IPv5 or Hostname
     * @param address	address to evaluate
     * @return "IPv4", "IPv6" or "Hostname"
     */
    private static String classifyAddress(String address) {
        // classify address by applying Models with Pattern validation
        if (Models.isValidIPv4(address))
            return "IPv4";
        if (Models.isValidIPv6(address))
            return "IPv6";
        return "Hostname";
    }

    /**
     * Given a capture, count number of emitter-receiver pairs using both IPv4, IPv6 or hostnames
     * @param capture	Capture to evaluate
     */
    public static void inspectAddresses(Capture capture) {
        List<Packet> packets = capture.getPackets();
        int ipv4Counter = 0, ipv6Counter = 0, hostnameCounter = 0;
	// start counting from 0
        for (Packet packet : packets) {
            // for each packet, classify source IP and destination IP
            String sourceType = classifyAddress(packet.getSourceIP());
            String destinationType = classifyAddress(packet.getDestinationIP());
            // if types are equal
            if (sourceType.equals(destinationType)) {
                // increment respective counter
                if (sourceType.equals("IPv4"))
                    ipv4Counter++;
                else if (sourceType.equals("IPv6"))
                    ipv6Counter++;
                else if (sourceType.equals("Hostname"))
                    hostnameCounter++;
            }	
        }
        // show module output: number of emmiter-receiver using both IPv4, IPv6 and hostnames
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
