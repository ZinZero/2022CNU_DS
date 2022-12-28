package DS11;

public class AppView {
    // 생성자
    private AppView(){

    }

    // 공개함수
    public static void outputLine(String message){
        System.out.println(message);
    }
    public static void output(String message){
        System.out.print(message);
    }

    public static void outputResults(int size, long durationForAdd, long durationForMax){
        AppView.outputLine(
                "[크기: " + String.format("%5d", size) + "] " +
                "삽입: " + String.format("%8d", durationForAdd) + ", " +
                "최댓값: " + String.format("%8d", durationForMax)
        );
    }
}
