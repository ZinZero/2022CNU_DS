package DS05_Linked;

public class LinkedNode<T> {
    // 비공개 인스턴스 변수
    private T _element;
    private LinkedNode<T> _next;

    // getter, setter
    public T element(){
        return this._element;
    }
    public void setElement(T newElement){
        this._element = newElement;
    }

    public LinkedNode<T> next(){
        return this._next;
    }
    public void setNext(LinkedNode<T> newNext){
        this._next = newNext;
    }

    // 생성자
    public LinkedNode(){
        this.setElement(null);
        this.setNext(null);
    }
    public LinkedNode(T givenElement, LinkedNode<T> givenNext){
        this.setElement(givenElement);
        this.setNext(givenNext);
    }
}
