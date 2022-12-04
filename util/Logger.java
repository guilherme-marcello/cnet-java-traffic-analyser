package util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * this class provides methods to print useful information
 */
public class Logger {	
	/**
	 * Prints information in the format [time of printing] |type of information| - message
	 * @param level type of information
	 * @param content message to print
	 */
	private static void printWithLevel(String level, String content) {
		System.out.println(
				String.format("[%s] |%s| - %s", new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()), level, content)
		);
	}
	
	/**
	 * prints a given string in the format described in printWithLevel() of the type "INFO"
	 * @param content message to print
	 */
	public static void info(String content) {
		printWithLevel("INFO", content);
	}
	
	/**
	 * prints a given string in the format described in printWithLevel() of the type "ERROR"
	 * @param content message to print
	 */
	public static void error(String content) {
		printWithLevel("ERROR", content);
	}
}

