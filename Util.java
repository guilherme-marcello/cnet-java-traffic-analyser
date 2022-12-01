import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class Util {
    public static List<Packet> readTrace(String filename) {
        List<Packet> capture = new LinkedList<>();
		try (Scanner scanner = new Scanner(new File(filename));) {

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