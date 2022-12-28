package DS06;

public class SortedLinkedList<E extends Comparable<E>>{
    // 비공개 인스턴스 변수
    private int _size;
    private LinkedNode<E> _head;

    // getter, setter
    public int size(){
        return this._size;
    }
    private void setSize(int newSize){
        this._size = newSize;
    }

    public LinkedNode<E> head(){
        return this._head;
    }
    private void setHead(LinkedNode<E> newHead){
        this._head = newHead;
    }

    // 생성자
    public SortedLinkedList(){
        this.setHead(null);
        this.setSize(10000);
    }

    // 공개함수
    public boolean isEmpty(){
        return (this.size() == 0);
    }
    public boolean isFull(){
        return false;
    }

    public boolean add(E anElement){
        // 전달 받은 anElement 을 배열의 sort 순서에 맞는 위치를 찾아 삽입
        if (this.isFull()){
            return false;
        }
        else {
            LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null);
            if (this.isEmpty()){
                this.setHead(nodeForAdd);
            }
            else { // 리스트에는 적어도 하나의 노드가 있다.
                LinkedNode<E> current = this.head();
                LinkedNode<E> previous = null;
                while (current != null){
                    if (current.element().compareTo(anElement) > 0){
                        break;
                    }
                    previous = current;
                    current = current.next();
                }
                if (previous == null){
                    nodeForAdd.setNext(this.head());
                    this.setHead(nodeForAdd);
                }
                else {
                    nodeForAdd.setNext(current);
                    previous.setNext(nodeForAdd);
                }

            }
            this.setSize(this.size() + 1);
            return true;
        }

    }

    public E max(){
        if (this.isEmpty()){
            return null;
        }
        else{
            LinkedNode<E> lastNode = this.head();
            while (lastNode.next() != null){
                lastNode = lastNode.next();
            }
            return lastNode.element();
        }
    }

}
