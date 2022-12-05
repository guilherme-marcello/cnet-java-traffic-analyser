package util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Models {
    public static final Pattern VALID_IPV4 = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");
    public static final Pattern VALID_IPV6 = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    public static final Pattern VALID_COMPRESSED_IPV6 = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    public static boolean isValidIPv4(String address) {
        Matcher addressMatcher = VALID_IPV4.matcher(address);
        return addressMatcher.matches();
    }

    public static boolean isValidIPv6(String address) {
        Matcher addressMatcherStd = VALID_IPV6.matcher(address);
        Matcher addressMatcherCompressed = VALID_COMPRESSED_IPV6.matcher(address);
        return addressMatcherStd.matches() || addressMatcherCompressed.matches();
    }
}