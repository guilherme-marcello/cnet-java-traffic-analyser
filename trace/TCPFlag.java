package trace;

/**
 * this enum represent possible TCP flags
 */
public enum TCPFlag {
    SYN("0x002"),
    SYNACK("0x012"),
    ACK("0x010"),
    FIN("0x001"),
    UNNAMED("unnamed");
    
    /**
     * Build TCPFlag with its hexadecimal representation
     * @param hexValue  hexadecimal representation of flag
     */
    private final String hexValue;
    private TCPFlag(String hexValue) {
        this.hexValue = hexValue;
    }
    
    /**
     * Given an hexadecimal representation of a flag, return TCPFlag
     * @param hexValue  hexadecimal representation of flag
     * @return TCPFlag of given hex value
     */
    public static TCPFlag parseHex(String hexValue) {
        // verify if hexValue is equal to correct representation
        // return enum for each case; return UNNAMED otherwise
        if (hexValue.equals("0x002")) return SYN;
        if (hexValue.equals("0x012")) return SYNACK; 
        if (hexValue.equals("0x010")) return ACK; 
        if (hexValue.equals("0x001")) return FIN; 
        return UNNAMED;
    }
}
