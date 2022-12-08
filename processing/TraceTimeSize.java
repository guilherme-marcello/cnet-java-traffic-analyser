package processing;

import trace.Capture;
import util.MenuUtils;

/**
 * this class provides a way of finfing the trace time and size in a give Capture
 */
public class TraceTimeSize {
    
    /**
     * Returns a text answer with the given capture trace time and size
     * @param capture the capture to analyse
     */
    public static void inspectTraceTimeAndSize(Capture capture) {
        MenuUtils.showOutput(
            String.format(
                "Total trace time:\t%.2f seconds\nTotal trace size:\t%d packets", 
                capture.findTraceTime(), capture.findTraceSize()
            )
        );
    }
}