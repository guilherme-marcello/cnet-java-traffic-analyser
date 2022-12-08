package util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * this class provides methods to check if defined patterns match given patterns
 */
public class Models {
    // valid IPv4 address
    public static final Pattern VALID_IPV4 = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");
    // valid IPv6 address
    public static final Pattern VALID_IPV6 = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    // valid compressed IPv6 address
    public static final Pattern VALID_COMPRESSED_IPV6 = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    /**
     * checks if the given string matches the VALID_IPV4
     * @param address string to check
     * @return true if it matches false if otherwise
     */
    public static boolean isValidIPv4(String address) {
        Matcher addressMatcher = VALID_IPV4.matcher(address);
        return addressMatcher.matches();
    }

    /**
     * checks if the given string matches the VALID_IPV6 or VALID_COMPRESSED_IPV6
     * @param address string to check
     * @return true if it matches false if otherwise
     */
    public static boolean isValidIPv6(String address) {
        Matcher addressMatcherStd = VALID_IPV6.matcher(address);
        Matcher addressMatcherCompressed = VALID_COMPRESSED_IPV6.matcher(address);
        return addressMatcherStd.matches() || addressMatcherCompressed.matches();
    }
}