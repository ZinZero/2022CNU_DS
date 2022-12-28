package DS05_Linked;

public class AppController {

    private LinkedList<Student> _list;

    // getter,setter
    private LinkedList<Student> list(){
        return this._list;
    }
    private void setList(LinkedList<Student> newList){
        this._list = newList;
    }

    // 생성자
    public AppController(){
        this.setList(new LinkedList<Student>());
    }

    // 공개함수
    public void run(){
        AppView.outputLine("<<< 리스트 기능 확인 프로그램을 시작합니다 >>>");

        MainMenu selectedMenuValue = this.selectMenu();
        while (selectedMenuValue != MainMenu.EndOfRun){
            switch (selectedMenuValue){
                case DoesContain:
                    this.doesContain();
                    break;
                case ElementAt:
                    this.elementAt();
                    break;
                case First:
                    this.first();
                    break;
                case Last:
                    this.last();
                    break;
                case OrderOf:
                    this.orderOf();
                    break;
                case AddTo:
                    this.addTo();
                    break;
                case AddToFirst:
                    this.addToFirst();
                    break;
                case AddToLast:
                    this.addToLast();
                    break;
                case Add:
                    this.add();
                    break;
                case RemoveFrom:
                    this.removeFrom();
                    break;
                case RemoveFirst:
                    this.removeFirst();
                    break;
                case RemoveLast:
                    this.removeLast();
                    break;
                case RemoveAny:
                    this.removeAny();
                    break;
                case ReplaceAt:
                    this.replaceAt();
                    break;
                default:
                    break;
            }
            selectedMenuValue = this.selectMenu();
        }
        this.showStatistics();

        AppView.outputLine("");
        AppView.outputLine("<<< 리스트 기능 확인 프로그램을 종료합니다 >>>");
    }

    // 비공개 함수
    private void showMenu(){
        AppView.outputLine("> 해야 할 작업의 번호를 선택해야 합니다:");
        AppView.outputLine(" DoesContain=1, Elements=2, First=3, Last=4, OrderOf=5, ");
        AppView.outputLine(" AddTo=6, AddToFirst=7, AddToLast=8, Add=9, ");
        AppView.outputLine(" RemoveFrom=10, RemoveFirst=11, RemoveLast=12, RemoveAny=13, ReplaceAt=14, EndOfRun=99");
        AppView.output("? 작업 번호를 입력하시오: ");
    }

    private MainMenu selectMenu(){
        AppView.outputLine("");
        this.showList();
        this.showMenu();
        try{
            int selectedMenuNumber = AppView.inputInteger();
            MainMenu selectedMenuValue = MainMenu.value(selectedMenuNumber);
            if (selectedMenuValue == MainMenu.Error){
                AppView.outputLine("!오류: 작업 선택이 잘못되었습니다. (잘못된 메뉴 번호 :" + selectedMenuNumber + ")");
            }
            return selectedMenuValue;
        }
        catch (NumberFormatException e){
            AppView.outputLine("!오류: 입력된 메뉴 번호가 정수 숫자가 아닙니다.");
            return MainMenu.Error;
        }
    }

    private void doesContain(){
        AppView.outputLine("");
        AppView.outputLine("! DoesContain 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 작업을 수행 할 수 없습니다.");
        }
        else {
            int score = this.inputScore();
            if (this.list().doesContain(new Student(score))){
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생이 리스트에 존재합니다.");
            }
            else {
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생이 리트스에 존재하지 않습니다.");
            }
        }
    }

    private void elementAt(){
        AppView.outputLine("");
        AppView.outputLine("! elementAt 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 작업을 수행 할 수 없습니다.");
        }
        else {
            int order = this.inputOrder();
            if ((order < 0) || (order >= this.list().size())){
                AppView.outputLine("! 입력된 순서 [" + order + "]에 존재하는 학생은 없습니다.");
            }
            else {
                int score = this.list().elementAt(order).score();
                AppView.outputLine("! 입력된 순서 [" + order + "]의 학생의 점수는 (" + score + ") 입니다.");

            }
        }
    }

    private void first(){
        AppView.outputLine("");
        AppView.outputLine("! First 작업을 실행합니다.");
        int score = this.list().elementAt(0).score();
        AppView.outputLine("! [맨 앞] 학생의 점수는 (" + score + ") 입니다.");
    }
    private void last(){
        AppView.outputLine("");
        AppView.outputLine("! Last 작업을 실행합니다.");
        int score = this.list().elementAt(this.list().size() -1).score();
        AppView.outputLine("! [맨 뒤] 학생의 점수는 (" + score + ") 입니다.");
    }

    private void orderOf(){
        AppView.outputLine("");
        AppView.outputLine("! OrderOf 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 작업을 수행 할 수 없습니다.");
        }
        else {
            int score = inputScore();
            if (!this.list().doesContain(new Student(score))){
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생이 리스트에 존재하지 않습니다.");
            }
            else {
                int order = this.list().orderOf(new Student(score));
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생의 순서는 [" + order + "] 입니다.");
            }
        }
    }

    // 원소 추가하기
    private void addTo(){
        AppView.outputLine("");
        AppView.outputLine("! AddTo 작업을 실행합니다.");
        if (this.list().isFull()){
            AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
        }
        else{
            int order = this.inputOrder();
            if (order < 0 || order > this.list().size()){
                AppView.outputLine("! 입력된 순서[" + order + "]가 정상 법위 [0.." + this.list().size() + "]에 있지 않습니다.");
            }
            else {
                int score = this.inputScore();
                if (this.list().addTo(new Student(score), order)){
                    AppView.outputLine("! 입력된 순서 [" + order + "]에 입력된 점수 (" + score + ")의 학생을 삽입하는 작업을 성공하였습니다.");
                }
                else {
                    AppView.outputLine("! 입력된 순서 [" + order + "]에 입력된 점수 (" + score + ")의 학생을 삽입하는 작업을 실패하였습니다.");
                }
            }
        }
    }
    private void addToFirst(){
        AppView.outputLine("");
        AppView.outputLine("! AddToFirst 작업을 실행합니다.");
        if (this.list().isFull()){
            AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
        }
        else {
            int score = this.inputScore();
            if (this.list().addToFirst(new Student(score))){
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 앞]에 삽입하는 작업을 성공하였습니다.");
            }
            else {
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 앞]에 삽입하는 작업을 실패하였습니다.");
            }
        }
    }
    private void addToLast(){
        AppView.outputLine("");
        AppView.outputLine("! AddToLast 작업을 실행합니다.");
        if (this.list().isFull()){
            AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
        }
        else {
            int score = this.inputScore();
            if (this.list().addToLast(new Student(score))){
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 뒤]에 삽입하는 작업을 성공하였습니다.");
            }
            else {
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 뒤]에 삽입하는 작업을 실패하였습니다.");
            }
        }
    }
    private void add(){
        AppView.outputLine("");
        AppView.outputLine("! Add 작업을 실행합니다.");
        if (this.list().isFull()){
            AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
        }
        else {
            int score = this.inputScore();
            if (this.list().add(new Student(score))){
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [임의의 순서]에 삽입하는 작업을 성공하였습니다.");
            }
            else {
                AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [임의의 순서]에 삽입하는 작업을 실패하였습니다.");
            }
        }
    }

    // 원소 제거하기
    private void removeFrom(){
        AppView.outputLine("");
        AppView.outputLine("! RemoveFrom 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
        }
        else {
            int order = this.inputOrder();
            if (order < 0 || order >= this.list().size()){
                AppView.outputLine("! 입력된 순서 [" + order + "]가 정상 범위 [0.." + (this.list().size() -1) + "]에 있지 않습니다.");
            }
            Student student = this.list().removeFrom(order);
            if (student != null){
                AppView.outputLine("! 입력된 순서 [" + order + "]에서 삭제된 학생의 성적은 (" + student.score() + ")입니다.");
            }
            else {
                AppView.outputLine("! 입력된 순서 [" + order + "]에서 학생을 삭제하는 작업을 실패하였습니다.");
            }
        }
    }
    private void removeFirst(){
        AppView.outputLine("");
        AppView.outputLine("! RemoveFirst 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
        }
        else {
            Student student = this.list().removeFirst();
            if (student != null){
                AppView.outputLine("! 삭제된 [맨 앞] 학생의 성적은 (" + student.score() + ") 입니다.");
            }
            else {
                AppView.outputLine("! [맨 앞] 학생을 삭제하는 작업을 실패하였습니다.");
            }
        }
    }
    private void removeLast(){
        AppView.outputLine("");
        AppView.outputLine("! RemoveLast 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
        }
        else {
            Student student = this.list().removeLast();
            if (student != null){
                AppView.outputLine("! 삭제된 [맨 뒤] 학생의 성적은 (" + student.score() + ") 입니다.");
            }
            else {
                AppView.outputLine("! [맨 뒤] 학생을 삭제하는 작업을 실패하였습니다.");
            }
        }
    }
    private void removeAny(){
        AppView.outputLine("");
        AppView.outputLine("! RemoveAny 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
        }
        else {
            Student student = this.list().removeAny();
            if (student != null){
                AppView.outputLine("! 삭제된 [임의] 학생의 성적은 (" + student.score() + ") 입니다.");
            }
            else {
                AppView.outputLine("! [임의] 학생을 삭제하는 작업을 실패하였습니다.");
            }
        }
    }

    // 원소 변경하기
    private void replaceAt(){
        AppView.outputLine("");
        AppView.outputLine("! ReplaceAt 작업을 실행합니다.");
        if (this.list().isEmpty()){
            AppView.outputLine("! 리스트가 비어 있어서 바꾸기 작업을 할 수 없습니다.");
        }
        else {
            int order = this.inputOrder();
            if (order < 0 || order >= this.list().size()){
                AppView.outputLine("! 입력된 순서 [" + order + "]가 정상 범위 [0.." + (this.list().size() -1) + "]에 있지 않습니다.");
            }
            else {
                int score = this.inputScore();
                if (this.list().replaceAt(new Student(score), order)){
                    AppView.outputLine("! 주어진 순서 [" + order + "]의 학생의 점수가 (" + score + ")로 바뀌었습니다.");
                }
                else {
                    AppView.outputLine("! 주어진 순서 [" + order + "]의 학생의 점수를 바꾸는 작업을 실패하였습니다.");
                }
            }
        }
    }

    // 점수 받기
    private int inputScore(){
        int score;
        while (true){
            try{
                AppView.output("? 점수를 입력하시오: ");
                score = AppView.inputInteger();
                return score;
            }
            catch (NumberFormatException e){
                AppView.outputLine("[오류] 입력된 점수는 정수가 아닙니다.");
            }
        }
    }

    // 순서 받기
    private int inputOrder(){
        int score;
        while (true){
            try{
                AppView.output("? 리스트에서 순서 번호를 입력하시오: ");
                score = AppView.inputInteger();
                return score;
            }
            catch (NumberFormatException e){
                AppView.outputLine("[오류] 입력된 순서 번호는 정수가 아닙니다.");
            }
        }
    }

    private void showList(){
        AppView.output("! 현재의 리스트 원소들: [");
        Student student = null;
        LinkedList<Student>.ListIterator iterator = this.list().iterator();
        while (iterator.hasNext()){
            student = iterator.next();
            AppView.output(" " + student.score());
        }
        AppView.outputLine(" ]");
    }
    private void showStatistics(){
        AppView.outputLine("");
        AppView.outputLine("> 리스트 정보 입니다:");
        AppView.outputLine("! 학생 수: " + this.list().size());
        this.showList();
    }

}
