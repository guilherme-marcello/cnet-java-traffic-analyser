package util;
/**
 * this class provides methods to extend Java default math operations
 */
public class MathUtils {
    /**
     * Given an integer x, return the binary logarithm of this number
     * @param x value to be evaluated
     * @return integer binary logarithm of x
     */
    public static int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }
}
