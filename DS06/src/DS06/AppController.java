package DS06;

public class AppController {
    // 비공개 인스턴스 변수
    private  Experiment _experiment;

    //getter, setter
    private Experiment experiment(){
        return this._experiment;
    }
    private void setExperiment(Experiment newExperiment){
        this._experiment = newExperiment;
    }

    // 생성자
    public AppController(){
        this.setExperiment(new Experiment()); // 실험 객체를 생성
        this.experiment().generateData(); // 사용할 데이터를 생성
    }

    // 공개함수의 구현
    public void run(){
        AppView.outputLine("<<< 리스트 성능 측정 프로그램을 시작합니다.>>>");
        AppView.outputLine("! 리스트의 구현에 따른 시간의 차이를 알아봅니다.: (단위: Micro Second)");

        // Unsorted Array List 에 대한 측정
        AppView.outputLine("");
        AppView.outputLine("<Unsorted Array List>");
        this.experiment().measureForUnSortedArrayList();
        this.showExperimentResults();

        // Sorted Array List 에 대한 측정
        AppView.outputLine("");
        AppView.outputLine("<Sorted Array List>");
        this.experiment().measureForSortedArrayList();
        this.showExperimentResults();

        // Unsorted Linked List 에 대한 측정
        AppView.outputLine("");
        AppView.outputLine("<Unsorted Linked List>");
        this.experiment().measureForUnSortedLinkedList();
        this.showExperimentResults();

        // Sorted Linked List 에 대한 측정
        AppView.outputLine("");
        AppView.outputLine("<Sorted Linked List>");
        this.experiment().measureForSortedLinkedList();
        this.showExperimentResults();

        AppView.outputLine("");
        AppView.outputLine("<<<리스트 성능 측정 프로그램을 종료합니다.>>>");

    }

    // 비공개함수 구현
    private void showExperimentResults(){
        MeasuredResult[] results = this.experiment().measuredResults();
        for (int i = 0; i < this.experiment().numberOfIteration(); i++){
            AppView.outputResults(
                    results[i].size(),
                    results[i].durationForAdd() / 1000,
                    results[i].durationForMax() / 1000
            );
        }
    }
}
