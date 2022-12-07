package trace;

import java.util.LinkedList;
import java.util.List;

public class Capture {

    private final List<Packet> packets;

    public Capture () {
        this.packets = new LinkedList<>();
    }

    public List<Packet> getPackets() {
        return this.packets;
    }

    public void add(Packet packet) {
        this.packets.add(packet);
    }
    
    @Override
    public String toString() {
        return this.packets.toString();
    }

    public Double findTraceTime() {
        Packet firstPacket = this.packets.get(0);
        Packet lastPacket = this.packets.get(this.packets.size() - 1);
        return Double.parseDouble(lastPacket.getTime()) - Double.parseDouble(firstPacket.getTime());
    }

    public int findTraceSize() {
        Packet firstPacket = this.packets.get(0);
        Packet lastPacket = this.packets.get(this.packets.size() - 1);
        return Integer.valueOf(lastPacket.getPacketNumber()) - Integer.valueOf(firstPacket.getPacketNumber()) + 1;
    }
}
