package DS10_Linked;

public class AppController {
    // 상수
    private static final int QUEUE_CAPACITY = 10;

    // 비공개 변수
    private Queue<Character> _queue;

    private int _inputChars;    // 입력된 문자의 개수
    private int _addedChars;    // 삽입된 문자의 개수
    private int _ignoredChars;  // 무시된 문자의 개수

    // getter, setter
    private Queue<Character> queue(){
        return this._queue;
    }
    private void setQueue(Queue<Character> newQueue){
        this._queue = newQueue;
    }
    private int inputChars(){
        return this._inputChars;
    }
    private void setInputChars(int newInputChars){
        this._inputChars = newInputChars;
    }
    private int addedChars(){
        return this._addedChars;
    }
    private void setAddedChars(int newAddedChars){
        this._addedChars = newAddedChars;
    }
    private int ignoredChars(){
        return this._ignoredChars;
    }
    private void setIgnoredChars(int newIgnoredChars){
        this._ignoredChars = newIgnoredChars;
    }

    // 생성자
    public AppController(){
        this.setQueue(new CircularlyLinkedQueue<Character>());
        this.setInputChars(0);
        this.setAddedChars(0);
        this.setIgnoredChars(0);
    }

    // 공개 함수
    public void run(){
        AppView.outputLine("<<< 큐 기능 확인 프로그램을 시작합니다>>>");
        AppView.outputLine("");

        char inputElement = this.inputChar();
        while (inputElement != '!'){
            this.countInputChar();
            if ((Character.isAlphabetic(inputElement))){
                this.addToQueue(Character.valueOf(inputElement));
                this.countAddedChar();
            }
            else if (Character.isDigit(inputElement)){
                this.removeN(Character.getNumericValue(inputElement));
            }
            else if (inputElement == '-'){
                this.removeOne();
            }
            else if (inputElement == '#'){
                this.showQueueSize();
            }
            else if (inputElement == '/'){
                this.showAllFromFront();
            }
            else if (inputElement == '\\'){
                this.showAllFromRear();
            }
            else if (inputElement == '<'){
                this.showFrontElement();
            }
            else if (inputElement == '>'){
                this.showRearElement();
            }
            else {
                AppView.outputLine("[Ignore] 의미 없는 문자가 입력되었습니다");
                this.countIgnoredChar();
            }
            inputElement = this.inputChar();
        }
        this.quitQueueProcessing();

        this.showStatistics();
        AppView.outputLine("");
        AppView.outputLine("<<< 큐 기능 확인 프로그램을 종료합니다.>>>");
    }

    // 비공개 함수
    // 횟수 계산
    private void countInputChar(){
        this.setInputChars(this.inputChars() + 1);
    }
    private void countIgnoredChar(){
        this.setIgnoredChars(this.ignoredChars() + 1);
    }
    private void countAddedChar(){
        this.setAddedChars(this.addedChars() + 1);
    }

    // 큐 수행 관련
    private void addToQueue(char aCharForAdd){
        if (this.queue().isFull()){
            AppView.outputLine("(오류) 큐가 꽉 차서, 더 이상 넣을 수 없습니다.");
        }
        else {
            Character addedChar = Character.valueOf(aCharForAdd);
            if (this.queue().enQueue(addedChar)){
                AppView.outputLine("[EnQ] 삽입된 원소는 '" + addedChar + "' 입니다.");
            }
            else{
                AppView.outputLine("(오류) 큐에 넣는 동안에 오류가 발생하였습니다.");
            }
        }
    }
    private void removeOne(){
        if (this.queue().isEmpty()){
            AppView.outputLine("[DeQ.Empty] 큐에 삭제할 원소가 없습니다.");
        }
        else{
            Character removedChar = this.queue().deQueue();
            if (removedChar == null){
                AppView.outputLine("(오류) 큐에서 삭제하는 동안에 오류가 발생하였습니다.");
            }
            else{
                AppView.outputLine("[DeQ] 삭제된 원소는 '" + removedChar + "' 입니다.");
            }
        }
    }
    private void removeN(int numberOfCharsToBeRemoved){
        if (numberOfCharsToBeRemoved == 0){
            AppView.outputLine("[DeQs] 삭제할 원소의 개수가 0개 입니다.");
        }
        else{
            int count = 0;
            while (count < numberOfCharsToBeRemoved && (!this.queue().isEmpty())){
                Character removedChar = this.queue().deQueue();
                if (removedChar == null){
                    AppView.outputLine("(오류) 큐에서 삭제하는 동안 오류가 발생하였습니다.");
                }
                else{
                    AppView.outputLine("[DeQs] 삭제된 원소는 '" + removedChar + "' 입니다.");
                }
                count++;
            }
            if (count < numberOfCharsToBeRemoved){
                AppView.outputLine("[DeQs.Empty] 큐에 더 이상 삭제할 원소가 없습니다.");
            }
        }
    }
    private void quitQueueProcessing(){
        AppView.outputLine("");
        AppView.outputLine("<큐를 비우고 사용을 종료합니다.>");
        this.showAllFromFront();
        this.removeN(this.queue().size());
    }

    // 출력 관련
    private void showAllFromFront(){
        // 큐의 모든 원소를 Front 부터 Rear 까지 츨력한다.
        AppView.output("[Queue] <Front> ");
        Iterator<Character> queueIterator = this.queue().iterator();
        while (queueIterator.hasNext()){
            Character element = queueIterator.next();
            AppView.output(element.toString() + " ");
        }
        AppView.outputLine("<Rear>");
    }
    private void showAllFromRear(){
        AppView.output("[Queue] <Read> ");
        for (int order = this.queue().size() - 1; order >= 0; order--){
            AppView.output(this.queue().elementAt(order).toString() + " ");
        }
        AppView.outputLine("<Front>");
    }
    private void showFrontElement(){
        if (this.queue().isEmpty()){
            AppView.outputLine("[Front.Empty] 큐가 비어서 맨 앞 원소가 존재하지 않습니다.");
        }
        else {
            Character FrontChar = this.queue().front();
            if (FrontChar == null){
                AppView.outputLine("(오류) 큐에서 불러오는 동안 오류가 발생하였습니다.");
            }
            else {
                AppView.outputLine("[Front] 큐의 맨 앞 원소는 '" + FrontChar + "' 입니다.");
            }
        }
    }
    private void showRearElement(){
        if (this.queue().isEmpty()){
            AppView.outputLine("[Rear.Empty] 큐가 비어서 맨 뒤 원소가 존재하지 않습니다.");
        }
        else{
            Character RearChar = this.queue().rear();
            if (RearChar == null){
                AppView.outputLine("(오류) 큐에서 불러오는 동안 오류가 발생하였습니다.");
            }
            else{
                AppView.outputLine("[Rear] 큐의 맨 뒤 원소는 '" + RearChar + "' 입니다.");
            }
        }
    }
    private void showQueueSize(){
        int QueueSize = this.queue().size();
        AppView.outputLine("[Size] 큐에는 현재 " + QueueSize + " 개의 원소가 있습니다.");
    }
    private void showStatistics(){
        AppView.outputLine("");
        AppView.outputLine("<큐 사용 통계>");
        AppView.outputLine("- 입력된 문자는 " + this.inputChars() + " 개 입니다.");
        AppView.outputLine("- 정상처리 된 문자는 " + (this.inputChars() - this.ignoredChars()) + " 개 입니다.");
        AppView.outputLine("- 무시된 문자는 " + this.ignoredChars() + " 개 입니다.");
        AppView.outputLine("- 삽입된 문자는 " + this.addedChars() + " 개 입니다.");
    }

    // 입력 관련
    private char inputChar(){
        AppView.output("? 문자를 입력하시오: ");
        return AppView.inputChar();
    }

}
