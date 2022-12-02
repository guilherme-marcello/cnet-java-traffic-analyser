import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util {
    public static Capture readTrace(String filename) {
        Capture capture = new Capture();
		try (Scanner scanner = new Scanner(new File(filename));) {
            scanner.nextLine();
			while (scanner.hasNextLine()) {
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