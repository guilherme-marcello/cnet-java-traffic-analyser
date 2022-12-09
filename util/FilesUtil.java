package util;

import trace.Capture;
import trace.Packet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * this class provides methods to read and parse traces
 */
public class FilesUtil {
    /**
     * Read a trace file, parse content to a List of Packet and return a instance of Capture
     * @param filename	Name of the source file
     * @return a Capture instance with all Packet inside file
     */
    public static Capture readTrace(String filename) {
	// create new Capture instance
        Capture capture = new Capture();
		try (Scanner scanner = new Scanner(new File(filename));) {
            scanner.nextLine();
			while (scanner.hasNextLine()) {
			// parse each line to a Packet and add Packet to capture
                capture.add(
                    new Packet(scanner.nextLine())
                );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return capture;
    }


}
