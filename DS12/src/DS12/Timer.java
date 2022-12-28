package DS12;

public class Timer {
    // 비공개 인스턴스 변수
    private long _startTime;    // 단위 : nano second
    private long _stopTime;     // 단위 : nano second

    public Timer(){
        this._startTime = 0;
        this._stopTime = 0;
    }

    public void start(){
        this._startTime = System.nanoTime();
    }

    public void stop(){
        this._stopTime = System.nanoTime();
    }

    public long duration(){
        return (this._stopTime - this._startTime);
    }
}
