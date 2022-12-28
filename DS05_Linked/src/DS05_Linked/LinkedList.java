package DS05_Linked;

public class LinkedList<T>{
    // 비공개 인스턴스 변수
    private int _size;
    private LinkedNode<T> _head;

    // getter, setter
    public int size(){
        return this._size;
    }
    private void setSize(int newSize){
        this._size = newSize;
    }

    public LinkedNode<T> head(){
        return this._head;
    }
    private void setHead(LinkedNode<T> newHead){
        this._head = newHead;
    }

    // 생성자
    public LinkedList(){
        this.setHead(null);
        this.setSize(0);
    }

    // 상태 알아보기
    public boolean isEmpty(){
        return (this.size() == 0);
    }
    public boolean isFull(){
        return false;
    }

    public T elementAt(int order){
        if (this.anElementDoesExistAt(order)){  //((order >= 0) && (order < this.size()))
            LinkedNode<T> currentNode = this.head();
            int nodeCount = 0;
            while (nodeCount < order){
                currentNode = currentNode.next();
                nodeCount++;
            }
            return currentNode.element();
        }
        else {
            return null;
        }
    }

    public int orderOf(T anElement){
        // 순차겸색
        int order = 0;
        LinkedNode<T> currentNode = this.head();
        while ((currentNode != null) && (!currentNode.element().equals(anElement))){
            order++;
            currentNode = currentNode.next();
        }
        if (currentNode == null){
            return -1; // 존재하지 않으면 -1을 돌려주기
        }
        else {
            return order;
        }
   }

   public boolean doesContain(T anElement){
        LinkedNode<T> currentNode = this.head();
        while (currentNode != null){
            if (currentNode.element().equals(anElement)){
                return true;
            }
            currentNode = currentNode.next();
        }
        return false;
   }


   // 원소 추가하기
   public boolean addTo(T anElement, int order){
        if ((order < 0) || (order > this.size())) {
            return false;
       }
        else if (this.isFull()){
           return false;
       }
       else {
            LinkedNode<T> nodeForAdd = new LinkedNode<T>(anElement, null);
            if (order == 0) { // 맨 앞 순서에 삽입. 앞 노드가 존재 X
                nodeForAdd.setNext(this.head());
                this.setHead(nodeForAdd);
            } else { // 순서가 맨 앞이 아니므로 반드시 앞 노드가 존재
                LinkedNode<T> previousNode = this.head();
                for (int i = 1; i < order; i++) {
                    previousNode = previousNode.next();
                }
                nodeForAdd.setNext(previousNode.next());
                previousNode.setNext(nodeForAdd);
            }
            this.setSize(this.size() + 1);
            return true;
       }
   }
    public boolean addToFirst(T anElement){
        if (this.isFull()){
            return false;
        }
        else {
            LinkedNode<T> nodeForAdd = new LinkedNode<T>(anElement, this.head());
            this.setHead(nodeForAdd);
            this.setSize(this.size() + 1);
            return true;
        }
    }
    public boolean addToLast(T anElement){
        if (this.isFull()){
            return false;
        }
        else {
            LinkedNode<T> nodeForAdd = new LinkedNode<T>(anElement, null);
            if (this.isEmpty()){
                this.setHead(nodeForAdd);
            }
            else {
                LinkedNode<T> lastNode = this.head();
                while (lastNode.next() != null){
                    lastNode = lastNode.next();
                }
                lastNode.setNext(nodeForAdd);
            }
            this.setSize(this.size() + 1);
            return true;
        }
    }
    public boolean add(T anElement){
        return this.addToFirst(anElement);
    }

    //원소 삭제하기
    public T removeFrom(int order){
        if (! this.anElementDoesExistAt(order)){
            return null;
        }
        else {
            // 리스트는 비어 있지 않으면, 삭제할 원소가 있음
            LinkedNode<T> removedNode = null;
            if (order == 0){ // 삭제할 원소가 맨 앞 원소
                removedNode = this.head();
                this.setHead(this.head().next());
            }
            else { // 삭제할 원소의 위치는 맨 앞이 아니며, 따라서 원소가 두 개 이상
                LinkedNode<T> previousNode = this.head();
                for (int i = 1; i < order; i++){
                    previousNode = previousNode.next();
                }
                removedNode = previousNode.next();
                previousNode.setNext(removedNode.next());
            }
            this.setSize(this.size() - 1);
            return removedNode.element();
        }
    }
    public T removeFirst(){
        if (this.isEmpty()){
            return null;
        }
        else {
            T removedElement = this.head().element();
            this.setHead(this.head().next());
            this.setSize(this.size() - 1);
            return removedElement;
        }
    }
    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }
        else {
            T removedElement = null;
            if (this.head().next() == null){
                removedElement = this.head().element();
                this.setHead(null);
            }
            else {
                LinkedNode<T> previousNode = this.head();
                LinkedNode<T> lastNode = previousNode.next();
                while (lastNode.next() != null){
                    previousNode = lastNode;
                    lastNode = lastNode.next();
                }
                removedElement = lastNode.element();
                previousNode.setNext(null);
            }
            this.setSize(this.size() - 1);
            return removedElement;
        }
    }
    public T removeAny(){
        return this.removeFirst();
    }
    public boolean remove(T anElement){
        // 단계 1 : 주어진 원소의 위치를 찾는다.
        LinkedNode<T> previousNode = null;
        LinkedNode<T> currentNode = this.head();
        while ((currentNode != null) && (!currentNode.element().equals(anElement))){
            previousNode = currentNode;
            currentNode = currentNode.next();
        }
        // 단계 2 : 주어진 원소가 존재하면 삭제한다.
        if (currentNode == null){
            return false; // Not Found
        }
        else {
            if (currentNode == this.head()){ // 삭제할 노드가 맨 앞 노드
                this.setHead(this.head().next());
            }
            else { // 삭제할 노드 앞에 노드가 존재
                previousNode.setNext(currentNode.next());
            }
            this.setSize(this.size() - 1);
            return true;
        }
    }

    // 원소 바꾸기
    public boolean replaceAt(T anElement, int order){
        if (!this.anElementDoesExistAt(order)){
            // 대체할 노드가 없거나, 잘 못 된 위치
            return false;
        }
        else {
            LinkedNode<T> currentNode = this.head();
            for (int i = 0; i < order; i++){
                currentNode = currentNode.next();
                // 원소를 대체할 노드를 찾는다.
            }
            currentNode.setElement(anElement);
            return true;
        }
    }

    // clear
    public void clear(){
        this.setHead(null);
        this.setSize(0);
    }

    public ListIterator iterator(){
        return new ListIterator();
    }

    // 비공개 함수
    private boolean anElementDoesExistAt(int order){
        return ((order >= 0) && (order < this.size()));
    }

    // 이터레이터
    public class ListIterator{
        private LinkedNode<T> _nextNode;

        // 생성자
        private ListIterator(){
            this._nextNode = LinkedList.this._head;
        }

        // 공개함수
        public boolean hasNext(){
            return (this._nextNode != null);
        }
        public T next(){
            if (this._nextNode == null){
                return null;
            }
            else {
                T e = this._nextNode.element();
                this._nextNode = this._nextNode.next();
                return e;
            }
        }
    }

}
