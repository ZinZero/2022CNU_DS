package DS05_Linked;

import java.util.Scanner;

public class AppView {

    private static Scanner scanner = new Scanner(System.in);

    private AppView(){  }

    private static void outputDebugMessage(String message){
        System.out.println(message);
    }

    public static void outputLine(String message){
        System.out.println(message);
    }

    public static void output(String message){
        System.out.print(message);
    }

    public static int inputInteger() throws NumberFormatException{
        return Integer.parseInt(AppView.scanner.next());
    }

}
