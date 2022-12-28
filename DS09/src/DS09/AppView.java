package DS09;

import java.util.*;

public class AppView {
    // 비공개 상수, 변수들
    private static Scanner scanner = new Scanner (System.in);
    private static boolean debugMode = false;

    // 생성자
    public AppView(){

    }

    // 공개함수
    public static boolean debugMode(){
        return debugMode;
    }
    public static void setDebugMode(boolean newDebugMode){
        debugMode = newDebugMode;
    }

    public static void outputDebugMessage(String message){
        if (AppView.debugMode()){
            System.out.print(message);
        }
    }
    public static void outputLineDebugMessage(String message){
        if (AppView.debugMode()){
            System.out.println(message);
        }
    }

    public static void output(String message){
        System.out.print(message);
    }
    public static void outputLine(String message){
        System.out.println(message);
    }

    public static String inputLine(){
        String line = AppView.scanner.nextLine().trim();
        while (line.equals("")){
            line = AppView.scanner.nextLine().trim();
        }
        return line;
    }
}
