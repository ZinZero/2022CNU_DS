package DS01;

import java.util.Scanner;

public class AppView {
    private static Scanner scanner = new Scanner(System.in);

    private AppView() {
    }

    public static int inputOrder() {
        // 채우기

        System.out.print("? 마방진 차수를 입력하시오.(음수를 입력하면 종료합니다.): ");
        return scanner.nextInt();
    }

    public static void output(String message) {
        System.out.print(message);
    }

    public static void outputLine(String message) {
        System.out.println(message);
    }

    public static void outputTitleWithOrder(int order) {
        // 채우기
        System.out.println("! Magic Square Board: Order " + order);
    }

    public static void outputRowNumber(int number) {
        System.out.printf("[%3d]", number);
    }

    public static void outputCellValue(int value) {
        System.out.printf(" %3d", value);
    }
}

