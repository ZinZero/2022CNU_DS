package DS12;

public class ParameterSet {

    // 비공개 인스턴스 변수
    private int _statingSize;
    private int _numberOfSizeIncreasingSteps;
    private int _incrementSize;

    // public getter, setter
    public int startingSize(){
        return this._statingSize;
    }
    public void setStartingSize(int newStatingSize){
        this._statingSize = newStatingSize;
    }

    public int numberOfSizeIncreasingSteps(){
        return this._numberOfSizeIncreasingSteps;
    }
    public void setNumberOfSizeIncreasingSteps(int newNumberOfSizeIncreasingSteps){
        this._numberOfSizeIncreasingSteps = newNumberOfSizeIncreasingSteps;
    }

    public int incrementSize(){
        return this._incrementSize;
    }
    public void setIncrementSize(int newIncrementSize){
        this._incrementSize = newIncrementSize;
    }

    public int maxDataSize(){
        return (this.startingSize() + (this.incrementSize() * (this.numberOfSizeIncreasingSteps() - 1)));
    }

    // 생성자
    public ParameterSet(int givenStatingSize, int givenNumberOfSizeIncreasingSteps, int givenIncrementSize){
        this.setStartingSize(givenStatingSize);
        this.setNumberOfSizeIncreasingSteps(givenNumberOfSizeIncreasingSteps);
        this.setIncrementSize(givenIncrementSize);
    }
}
