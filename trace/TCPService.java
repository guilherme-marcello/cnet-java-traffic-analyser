package trace;

import java.util.Map;
import java.util.HashMap;

/**
 * this enum represent known TCP services
 */
public enum TCPService {
    FTP_D("20 (FTP - data)"),
    FTP_C("21 (FTP - control)"),
    SSH("22 (SSH)"),
    TELNET("23 (Telnet)"),
    SMTP("25 (SMTP)"),
    DNS("53 (DNS)"),
    HTTP("80 (HTTP)"),
    POP("109 (POP)"),
    POP3("110 (POP3)"),
    HTTPS("443 (HTTPS)"),
    DHCPv6_C("546 (DHCPv6 - client)"),
    DHCPv6_S("547 (DHCPv6 - server)"),
    RTSP("554 (RTSP)"),
    FTP_D_TLS("989 (FTP - data over TLS)"),
    FTP_C_TLS("990 (FTP - control over TLS)"),
    TELNET_TLS("992 (Telnet over TLS)"),
    IMAP4_SSL("993 (IMAP4 over SSL"),
    POP3_SSL("995 (POP3 over SSL)");

    /**
     * Build TCPService with its description
     * @param description   textual representation of service
     */
    private TCPService(String description) {
        this.description = description;
    }

    /**
     * @return textual representation of TCP service
     */
    @Override
    public String toString() {
        return this.description;
    }

    private final String description;
    
    // static map [port number (string)] -> [TCPService]
    // this enable clients to translate a port number to a TCPService
    public static Map<String,TCPService> ports = new HashMap<>() {{
        put("20", TCPService.FTP_D);
        put("21", TCPService.FTP_C);
        put("22", TCPService.SSH);
        put("23", TCPService.TELNET);
        put("25", TCPService.SMTP);
        put("53", TCPService.DNS);
        put("80", TCPService.HTTP);
        put("109", TCPService.POP);
        put("110", TCPService.POP3);
        put("443", TCPService.HTTPS);
        put("546", TCPService.DHCPv6_C);
        put("547", TCPService.DHCPv6_S);
        put("554", TCPService.RTSP);
        put("989", TCPService.FTP_D_TLS);
        put("990", TCPService.FTP_C_TLS);
        put("992", TCPService.TELNET_TLS);
        put("993", TCPService.IMAP4_SSL);
        put("995", TCPService.POP3_SSL);
    }};
}
