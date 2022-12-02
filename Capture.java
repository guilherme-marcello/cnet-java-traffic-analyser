import java.util.LinkedList;
import java.util.List;

public class Capture {

    private List<Packet> packets;

    public Capture () {
        this.packets = new LinkedList<>();
    }

    public void add(Packet packet) {
        this.packets.add(packet);
    }

    public List<Integer> getPacketsLength() {
        List<Integer> packetsLength = new LinkedList<>();
        for (Packet packet : this.packets)
            packetsLength.add(packet.getLength());
        return packetsLength;
    }

    public String toString() {
        return this.packets.toString();
    }


}