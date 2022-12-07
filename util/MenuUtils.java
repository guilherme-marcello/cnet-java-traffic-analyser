package util;

import java.util.Scanner;

public class MenuUtils {
    public static void showBanner() {
        System.out.println("___  __        ___  ___    __                              __     __  ");
        System.out.println(" |  |__)  /\\  |__  |__  | /  `     /\\  |\\ |  /\\  |    \\ / /__` | /__` ");
        System.out.println(" |  |  \\ /~~\\ |    |    | \\__,    /~~\\ | \\| /~~\\ |___  |  .__/ | .__/ ");                                                                        
    }
    
    public static void showMenu(String[] options){
        for (String option : options){
        	System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }

    public static int getUserOption(String[] options, Scanner stdin) {
        showMenu(options);
        return stdin.nextInt();
    }

    public static void showOutput(String content) {
        System.out.println("[BEGIN - OUTPUT]\n" + content + "\n[END - OUTPUT]");
    }
    

}