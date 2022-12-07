package processing;

import trace.Capture;
import util.MenuUtils;

public class TraceTimeSize {
    public static void inspectTraceTimeAndSize(Capture capture) {
        MenuUtils.showOutput(
            String.format(
                "Total trace time:\t%.2f seconds\nTotal trace size:\t%d packets", 
                capture.findTraceTime(), capture.findTraceSize()
            )
        );
    }
}