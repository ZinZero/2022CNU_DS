package DS14;

import java.util.Scanner;

public class AppView {
    private static Scanner scanner = new Scanner(System.in);

    private AppView() {

    }

    // 출력
    public static void outputLine(String message) {
        System.out.println(message);
    }

    public static void output(String message) {
        System.out.print(message);
    }

}
