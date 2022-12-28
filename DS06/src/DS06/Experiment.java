package DS06;

import java.util.*;

public class Experiment {
    // constants
    private static final int DEFAULT_NUMBER_OF_ITERATION = 5;
    private static final int DEFAULT_FIRST_SIZE = 10000; // 첫 번 째 실험 데이터 크기
    private static final int DEFAULT_SIZE_INCREMENT = 10000; // 실험 데이터 크기 증가량

    // 비공개 인스턴스
    private int _numberOfIteration;
    private int _firstSize;
    private int _sizeIncrement;
    private Coin[] _data;
    private MeasuredResult[] _measuredResults;

    // getter, setter
    public int numberOfIteration(){
        return this._numberOfIteration;
    }
    public void setNumberOfIteration(int newNumberOfIteration){
        this._numberOfIteration = newNumberOfIteration;
    }

    public int firstSize(){
        return this._firstSize;
    }
    public void setFirstSize(int newFirstSize){
        this._firstSize = newFirstSize;
    }

    public int sizeIncrement(){
        return this._sizeIncrement;
    }
    public void setSizeIncrement(int newSizeIncrement){
        this._sizeIncrement = newSizeIncrement;
    }

    public Coin[] data(){
        return this._data;
    }
    public void setData(Coin[] newData){
        this._data = newData;
    }

    public MeasuredResult[] measuredResults(){
        return this._measuredResults;
    }
    public void setMeasuredResults(MeasuredResult[] newMeasuredResults){
        this._measuredResults = newMeasuredResults;
    }

    // 셍상자
    public Experiment(){
        this(DEFAULT_NUMBER_OF_ITERATION, DEFAULT_FIRST_SIZE, DEFAULT_SIZE_INCREMENT);
    }
    public Experiment(int givenNumberOfIteration, int givenFirstSize, int givenSizeIncrement){
        this.setNumberOfIteration(givenNumberOfIteration);
        this.setFirstSize(givenFirstSize);
        this.setSizeIncrement(givenSizeIncrement);

        this.setData(new Coin[this.maxSize()]); // 실험 데이터를 담을 배열 공간 확보
        this.setMeasuredResults(new MeasuredResult[this.numberOfIteration()]); // 실험 결과를 저장할 배열 공간 확보
    }

    // 공개함수
    public int maxSize(){
        return this.firstSize() + this.sizeIncrement() * (this.numberOfIteration() - 1);
    }

    public void generateData(){
        Random random = new Random();
        for (int i = 0; i < this.maxSize(); i++){
            int randomCoinValue = random.nextInt(this.maxSize());
            this.data()[i] = new Coin(randomCoinValue);
        }
    }

    // 성능 측정 실행 공개함수
    public void measureForUnSortedArrayList(){
        @SuppressWarnings("unused")
        Coin maxCoin;

        long durationForAdd, durationForMax;
        long start, stop;

        int dataSize = this.firstSize();
        for(int iteration = 0; iteration < this.numberOfIteration(); iteration++){
            UnsortedArrayList<Coin> listOfCoins = new UnsortedArrayList<Coin>(dataSize);
            durationForAdd = 0;
            durationForMax = 0;
            for (int i = 0; i < dataSize; i++){
                start = System.nanoTime();
                listOfCoins.add(this.data()[i]);
                stop = System.nanoTime();
                durationForAdd += (stop - start);

                start = System.nanoTime();
                maxCoin = listOfCoins.max(); // 실행시간을 측정할 실행코드
                stop = System.nanoTime();
                durationForMax += (stop - start);
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
            dataSize += this.sizeIncrement();
        }

    }

    public void measureForSortedArrayList(){
        // Sorted Array 로 구현한 List 의 성능을 측정한다.
        @SuppressWarnings("unused")
        Coin maxCoin;

        long durationForAdd, durationForMax;
        long start, stop;

        int dataSize = this.firstSize();
        for(int iteration = 0; iteration < this.numberOfIteration(); iteration++){
            SortedArrayList<Coin> listOfCoins = new SortedArrayList<Coin>(dataSize);
            durationForAdd = 0;
            durationForMax = 0;
            for (int i = 0; i < dataSize; i++){
                start = System.nanoTime();
                listOfCoins.add(this.data()[i]);
                stop = System.nanoTime();
                durationForAdd += (stop - start);

                start = System.nanoTime();
                maxCoin = listOfCoins.max(); // 실행시간을 측정할 실행코드
                stop = System.nanoTime();
                durationForMax += (stop - start);
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
            dataSize += this.sizeIncrement();
        }
    }

    public void measureForUnSortedLinkedList(){
        @SuppressWarnings("unused")
        Coin maxCoin;

        long durationForAdd, durationForMax;
        long start, stop;

        UnsortedLinkedList<Coin> listOfCoins = new UnsortedLinkedList<Coin>();
        int dataSize = listOfCoins.size();
        for(int iteration = 0; iteration < this.numberOfIteration(); iteration++){
            durationForAdd = 0;
            durationForMax = 0;
            for (int i = 0; i < dataSize; i++){
                start = System.nanoTime();
                listOfCoins.add(this.data()[i]);
                stop = System.nanoTime();
                durationForAdd += (stop - start);

                start = System.nanoTime();
                maxCoin = listOfCoins.max(); // 실행시간을 측정할 실행코드
                stop = System.nanoTime();
                durationForMax += (stop - start);
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
            dataSize += this.sizeIncrement();
        }

    }

    public void measureForSortedLinkedList(){
        @SuppressWarnings("unused")
        Coin maxCoin;

        long durationForAdd, durationForMax;
        long start, stop;

        SortedLinkedList<Coin> listOfCoins = new SortedLinkedList<Coin>();
        int dataSize = listOfCoins.size();
        for(int iteration = 0; iteration < this.numberOfIteration(); iteration++){
            durationForAdd = 0;
            durationForMax = 0;
            for (int i = 0; i < dataSize; i++){
                start = System.nanoTime();
                listOfCoins.add(this.data()[i]);
                stop = System.nanoTime();
                durationForAdd += (stop - start);

                start = System.nanoTime();
                maxCoin = listOfCoins.max(); // 실행시간을 측정할 실행코드
                stop = System.nanoTime();
                durationForMax += (stop - start);
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
            dataSize += this.sizeIncrement();
        }

    }

}
