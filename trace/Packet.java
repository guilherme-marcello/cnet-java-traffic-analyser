package trace;

public class Packet {

        private String packetNumber;
        private String time;
        private String sourceIP;
        private String destinationIP;
        private String sourcePort;
        private String destinationPort;
        private String protocol;
        private String ICMPtype;
        private int length;
        private String flags;

        public Packet (String line) {
            String[] info = line.split(",");
            for (int i = 0; i < info.length; i++)
                info[i] = info[i].replace("\"", "");
            this.packetNumber = info[0];
            this.time = info[1];
            this.sourceIP = info[2];
            this.destinationIP = info[3];
            this.sourcePort = info[4];
            this.destinationPort = info[5];
            this.protocol = info[6];
            this.ICMPtype = info[7];
            this.length = Integer.parseInt(info[8]);
            this.flags = info[9];
        }

        public String getPacketNumer() {
            return this.packetNumber;
        }
        
        public String getTime() {
            return this.time;
        }

        public String getSourceIP() {
            return this.sourceIP;
        }

        public String getDestinationIP() {
            return this.destinationIP;
        }

        public String getSourcePort() {
            return this.sourcePort;
        }

        public String getDestinationPort() {
            return this.destinationPort;
        }

        public String getProtocol() {
            return this.protocol;
        }

        public String getICMPtype() {
            return this.ICMPtype;
        }

        public int getLength() {
            return this.length;
        }

        public String getFlags() {
            return this.flags;
        }

        public String toString() {
            return String.format(
                "\n[BEGIN - Packet %s]\n[Timestamp - %s]\n %s:%s sent %d bytes to[protocol=%s, icmptype=%s, flags=%s] %s:%s \n[END - Packet]",
                this.packetNumber, this.time, this.sourceIP, this.sourcePort, this.length, this.protocol, this.ICMPtype, this.flags, this.destinationIP, this.destinationPort 
            );
        }


    }