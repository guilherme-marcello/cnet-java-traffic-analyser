package trace;

import java.util.LinkedList;
import java.util.List;

public class Capture {

    private List<Packet> packets;

    public Capture () {
        this.packets = new LinkedList<>();
    }

    public List<Packet> getPackets() {
        return this.packets;
    }

    public void add(Packet packet) {
        this.packets.add(packet);
    }
    
    public String toString() {
        return this.packets.toString();
    }
}