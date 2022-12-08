package trace;

import java.util.LinkedList;
import java.util.List;

/*
 * instances of this class represent a sorted network trace (listing Packet)
 */
public class Capture {
    // list of packets
    private final List<Packet> packets;

    public Capture () {
        this.packets = new LinkedList<>();
    }

    /*
     * @return list of packets for this capture
     */
    public List<Packet> getPackets() {
        return this.packets;
    }

    /*
     * add given packet to this capture
     * @param packet    Packet to add
     */
    public void add(Packet packet) {
        this.packets.add(packet);
    }
    
    /*
     * @return string representation of a capture
     */
    @Override
    public String toString() {
        return this.packets.toString();
    }

    /*
     * @return difference between last packet timestamp and first packet timestamp (in seconds)
     */
    public Double findTraceTime() {
        Packet firstPacket = this.packets.get(0);
        Packet lastPacket = this.packets.get(this.packets.size() - 1);
        return Double.parseDouble(lastPacket.getTime()) - Double.parseDouble(firstPacket.getTime());
    }

    /*
     * @return difference between number and first packet number
     */
    public int findTraceSize() {
        Packet firstPacket = this.packets.get(0);
        Packet lastPacket = this.packets.get(this.packets.size() - 1);
        return Integer.valueOf(lastPacket.getPacketNumber()) - Integer.valueOf(firstPacket.getPacketNumber()) + 1;
    }
}
