package trace;

public enum TCPFlag {
    SYN("0x002"),
    SYNACK("0x012"),
    ACK("0x010"),
    FIN("0x001"),
    UNNAMED("unnamed");
    
    private final String hexValue;
    private TCPFlag(String hexValue) {
        this.hexValue = hexValue;
    }

    public static TCPFlag parseHex(String hexValue) {
        if (hexValue.equals("0x002")) return SYN;
        if (hexValue.equals("0x012")) return SYNACK; 
        if (hexValue.equals("0x010")) return ACK; 
        if (hexValue.equals("0x001")) return FIN; 
        return UNNAMED;
    }
}