package trace;

/*
 * instances of this class represent a network Packet
 */
public class Packet {
        // attributes that compose a packet
        private String packetNumber;
        private String time;
        private String sourceIP;
        private String destinationIP;
        private String sourcePort;
        private String destinationPort;
        private String protocol;
        private String ICMPtype;
        private int length; // length in bytes
        private String flags; // flags with hex representation

        /**
         * Build a Packet from a packet description (a line of a trace file)
         * @param line  description of a packet
         * @requires line != null
         */
        public Packet (String line) {
            // separate packet description by comma (getting each column of CSV trace data)
            String[] info = line.split(",");
            // for each value, parse "value" to value
            for (int i = 0; i < info.length; i++)
                info[i] = info[i].replace("\"", "");
            // update all attributes with parsed description
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

        /**
         * @return packet number
         */
        public String getPacketNumber() {
            return this.packetNumber;
        }

        /**
         * @return packet timestamp
         */
        public String getTime() {
            return this.time;
        }

        /**
         * @return packet source IP
         */
        public String getSourceIP() {
            return this.sourceIP;
        }
        
        /**
         * @return packet destination IP
         */
        public String getDestinationIP() {
            return this.destinationIP;
        }

         /**
         * @return packet source port
         */
        public String getSourcePort() {
            return this.sourcePort;
        }

        /**
         * @return packet destination port
         */
        public String getDestinationPort() {
            return this.destinationPort;
        }

        /**
         * @return packet protocol
         */
        public String getProtocol() {
            return this.protocol;
        }

        /**
         * @return packet ICMP type (if applicable)
         */
        public String getICMPtype() {
            return this.ICMPtype;
        }

         /**
          * @return packet length (in bytes)
          */
        public int getLength() {
            return this.length;
        }
        
         /**
          * @return packet flags (hex)
          */
        public String getFlags() {
            return this.flags;
        }

          /**
          * @return textual representation of a packet
          */
        public String toString() {
            return String.format(
                "\n[BEGIN - Packet %s]\n[Timestamp - %s]\n %s:%s sent %d bytes to[protocol=%s, icmptype=%s, flags=%s] %s:%s \n[END - Packet]",
                this.packetNumber, this.time, this.sourceIP, this.sourcePort, this.length, this.protocol, this.ICMPtype, this.flags, this.destinationIP, this.destinationPort 
            );
        }


    }
