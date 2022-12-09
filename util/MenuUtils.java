package util;

import java.util.Scanner;
/**
 * this class provides methods to handle menu display and interactions
 */
public class MenuUtils {
     /**
     * show a banner with the name of the tool
     */
    public static void showBanner() {
        System.out.println("___  __        ___  ___    __                              __     __  ");
        System.out.println(" |  |__)  /\\  |__  |__  | /  `     /\\  |\\ |  /\\  |    \\ / /__` | /__` ");
        System.out.println(" |  |  \\ /~~\\ |    |    | \\__,    /~~\\ | \\| /~~\\ |___  |  .__/ | .__/ ");                                                                        
    }
    
    /**
     * Given a list of options, build and show a choice menu, followed by an input request
     * @param options   List of options to be displayed
     */
    public static void showMenu(String[] options){
        // iterate over all options, printing each one
        for (String option : options){
        	System.out.println(option);
        }
        // request for input
        System.out.print("Choose your option : ");
    }

     /**
     * Given a list of options and a scanner, show menu and return user's choice
     * @param options   List of options to be displayed
     * @param stdin     Scanner to be used
     * @return  user option (integer)
     */
    public static int getUserOption(String[] options, Scanner stdin) {
        showMenu(options);
        return stdin.nextInt();
    }

     /**
     * Given a module output, display the content in a standard and formatted manner
     * @param content   Output of a module to be displayed
     */
    public static void showOutput(String content) {
        System.out.println("[BEGIN - OUTPUT]\n" + content + "\n[END - OUTPUT]");
    }
    

}
