package processing;

import trace.Capture;

public class TraceTimeSize {
    public static void inspectTraceTimeAndSize(Capture capture) {
        System.out.println(
            String.format(
                "[BEGIN - OUTPUT]\nTotal trace time:\t%.2f seconds\nTotal trace size:\t%d packets\n[END - OUTPUT]", 
                capture.findTraceTime(), capture.findTraceSize()
            )
        );
    }
}