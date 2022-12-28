package DS06;

public class UnsortedLinkedList<E extends Comparable<E>>{
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
    public UnsortedLinkedList(){
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
        if (this.isFull()){
            return false;
        }
        else{
            LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, this.head());
            this.setHead(nodeForAdd);
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
