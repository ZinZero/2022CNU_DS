package DS13;

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

    public static void outputStudentList(String aId, int aScore){
        AppView.outputLine("학번: " + aId + ", 점수: " + aScore);
    }

    public static void outputTotalNumberOfStudents(int numberOfStudents) {
        // 학급 학생 수 출력
        AppView.outputLine("학급 학생 수: " + numberOfStudents);
    }

    public static void outputHighestScore(int aScore) {
        // 학급 최고 점수 출력
        AppView.outputLine("학급 최고 점수: " + aScore);
    }

    public static void outputLowestSore(int aScore) {
        // 학급 최저 점수 출력
        AppView.outputLine("학급 최저 점수: " + aScore);
    }

    public static void outputAverageScore(double average) {
        // 평균값 출력
        AppView.outputLine("학급 평균: " + String.format("%.1f", average));
    }

    public static void outputNumberOfStudentsAboveAverage(int numberOfStudents) {
        // 평균 이상인 학생 수 출력
        AppView.outputLine("평균 이상인 학생 수: " + numberOfStudents);
    }

    public static void outputNumberOfStudentsForGrade(char aGrade, int numberOfStudents) {
        // 각 학점에 대한 학생 수 출력
        AppView.outputLine(aGrade + " 학점의 학생 수는 " + numberOfStudents + " 입니다.");
    }

    public static void outputStudentInfo(String aStudentID, int aScore, char aGrade) {
        AppView.outputLine("학번: " + aStudentID + ", 점수: " + aScore + ", 학점: " + aGrade);
    }

    // 입력 함수
    public static int inputInt() throws NumberFormatException {
        return Integer.parseInt(AppView.scanner.nextLine());
    }

    public static boolean doesContinueToInputStudent() {
        AppView.output("? 학생 정보를 입력하러면 'Y' 또는 'y'를, 종료하려면 다른 아무 키나 치시오: ");
        String line = null;
        do {
            line = AppView.scanner.nextLine();
        } while (line.equals(""));
        char answer = line.charAt(0);
        return ((answer == 'Y') || (answer == 'y'));
    }

    public static String inputStudentId() {
        AppView.output("- 학번을 입력하시오: ");
        String studentId = scanner.nextLine();
        return studentId;
    }

    public static int inputScore() {
        while (true) {
            try {
                AppView.output("- 점수를 입력하시오. (0 .. 100): ");
                int score = AppView.inputInt();
                return score;
            } catch (NumberFormatException e) {
                AppView.outputLine("(오류) 정수가 입력되지 않았습니다.");
            }
        }
    }
}
