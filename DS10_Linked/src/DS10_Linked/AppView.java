package DS10_Linked;

import java.util.Scanner;

public class AppView {
    // 비공개 상수, 변수들
    private static Scanner scanner = new Scanner(System.in);

    // 출력 공개 함수
    public static void outputLine(String message){
        System.out.println(message);
    }
    public static void output(String message){
        System.out.print(message);
    }

    // 입력 공개 함수
    public static char inputChar(){
        String line = AppView.scanner.nextLine().trim();
        while (line.equals("")){
            line = AppView.scanner.nextLine().trim();
        }
        return line.charAt(0);
    }
}
