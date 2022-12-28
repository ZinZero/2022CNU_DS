package DS12;

public class ExperimentManager {
    // 상수
    private static final int DEFAULT_NUMBER_OF_SIZE_INCREASING_STEPS = 10;
    private static final int DEFAULT_INCREMENT_SIZE = 1000;
    private static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;

    // 상수 객체
    private static final InsertionSort<Integer> INSERTION_SORT = new InsertionSort<Integer>();
    private static final QuickSort<Integer> QUICK_SORT = new QuickSort<Integer>();

    // 비공개 인스턴스 변수
    private Experiment _experiment;                     // 측정 실험을 실시할 객체
    private ParameterSet _parameterSet;                 // 측정 실험에 사용할 매개변수 집합
    private Integer[] _ascendingList;                   // 오름차순 데이터 리스트
    private Integer[] _descendingList;                  // 내림차순 데이터 리스트
    private Integer[] _randomList;                      // 무작위 데이터 리스트
    private long[] _measuredResultForInsertionSort;     // 삽입 정렬의 측정 결과 저장할 곳
    private long[] _measuredResultForQuickSort;         // 퀵 정렬의 측정 결과 저장할 곳

    // getter, setter
    private Experiment experiment() {
        return this._experiment;
    }

    private void setExperiment(Experiment newExperiment) {
        this._experiment = newExperiment;
    }

    public ParameterSet parameterSet() {
        return this._parameterSet;
    }

    private void setParameterSet(ParameterSet newParameterSet) {
        this._parameterSet = newParameterSet;
    }

    private Integer[] ascendingList() {
        return this._ascendingList;
    }

    private void setAscendingList(Integer[] newAscendingList) {
        this._ascendingList = newAscendingList;
    }

    private Integer[] descendingList() {
        return this._descendingList;
    }

    private void setDescendingList(Integer[] newDescendingList) {
        this._descendingList = newDescendingList;
    }

    private Integer[] randomList() {
        return this._randomList;
    }

    private void setRandomList(Integer[] newRandomList) {
        this._randomList = newRandomList;
    }

    private long[] measuredResultForInsertionSort() {
        return this._measuredResultForInsertionSort;
    }

    private void setMeasuredResultForInsertionSort(long[] newMeasuredResultForInsertionSort) {
        this._measuredResultForInsertionSort = newMeasuredResultForInsertionSort;
    }

    private long[] measuredResultForQuickSort() {
        return this._measuredResultForQuickSort;
    }

    private void setMeasuredResultForQuickSort(long[] newMeasuredResultForQuickSort) {
        this._measuredResultForQuickSort = newMeasuredResultForQuickSort;
    }

    // 셍성자
    public ExperimentManager() {
        this.setParameterSetWithDefaults(); // 기본 값으로 매개변수 집합을 초기화 한다.
    }

    // 공개 함수
    public void prepareExperiment(ParameterSet aParameterSet) {
        // 실험을 준비한다.

        if (aParameterSet != null) {
            // 객체 생성할 때, 매개변수 집합은 기본 값으로 설정되어 있다.
            // 실험 준비 단계에서, 이렇게 새로운 매개변수 집합을 주어 변경할 수 있다.
            this.setParameterSet(aParameterSet);
        }

        this.setExperiment(new Experiment(this.parameterSet()));
        // 현재 상태의 매개변수 집합을 사용하여 Experiment 객체를 생성한다.
        this.prepareExperimentLists();
        // 측정 실험에서 정렬에 사용할 데이터 리스트를 생성하여 보관한다.

        this.performExperiment(ListOrder.Random);
        this.performExperiment(ListOrder.Random);
    }

    public long measuredResultForInsertionSortAt(int sizeStep) {
        return this.measuredResultForInsertionSort()[sizeStep];
    }

    public long measuredResultForQuickSortAt(int sizeStep) {
        return this.measuredResultForQuickSort()[sizeStep];
    }

    public void performExperiment(ListOrder anOrder) {
        // 측정 실험을 실행한다.

        // 주어진 anOrder 의 실험 리스트를 얻는다.
        Integer[] experimentList = this.experimentListOfOrder(anOrder);

        // 이 실험 리스트로 삽입과 퀵 각각의 정렬의 성능을 측정해서, 그 결과를 얻는다.
        this.setMeasuredResultForInsertionSort (this.experiment().durationsOfSort(INSERTION_SORT, experimentList) );
        this.setMeasuredResultForQuickSort(this.experiment().durationsOfSort(QUICK_SORT, experimentList));
    }

    // 비공개 함수
    private void prepareExperimentLists() {
        int maxDataSize = this.parameterSet().maxDataSize();
        this.setAscendingList(DataGenerator.ascendingList(maxDataSize));
        this.setDescendingList(DataGenerator.descendingList(maxDataSize));
        this.setRandomList(DataGenerator.randomList(maxDataSize));
    }

    private void setParameterSetWithDefaults() {
        this.setParameterSet(new ParameterSet(
                DEFAULT_STARTING_SIZE,
                DEFAULT_NUMBER_OF_SIZE_INCREASING_STEPS,
                DEFAULT_INCREMENT_SIZE));
    }

    private Integer[] experimentListOfOrder(ListOrder anOrder) {
        // 주어진 anOrder 에 해당하는 리스트를 돌려준다.
        switch (anOrder) {
            case Ascending:
                return this.ascendingList();
            case Descending:
                return this.descendingList();
            default:
                return this.randomList();
        }
    }

}
