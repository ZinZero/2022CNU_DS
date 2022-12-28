package DS04;

import java.util.Scanner;

public class AppView {
    private static Scanner scanner = new Scanner(System.in);

    private AppView() {
    }

    public static void output(String message) {
        System.out.print(message);
    }

    public static void outputLine(String message) {
        System.out.println(message);
    }

    public static int inputCapacityOfCoinBag() {
        System.out.print("? 동전 가방의 크기, 즉 가방에 들어갈 동전의 최대 개수를 입력하시오: ");
        return scanner.nextInt();
    }

    public static int inputCoinValue() {
        System.out.print("? 동전 값을 입력하시오: ");
        return scanner.nextInt();
    }

    public static int inputMenuNumber() {
        System.out.print("? 수행하려고 하는 메뉴 번호를 선택하시오(add: 1, remove: 2, search: 3, frequency: 4, exit: 9) : ");
        return scanner.nextInt();
    }

}
