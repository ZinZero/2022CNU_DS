package DS14;

public class LinkedNode<E> {
    // 비공개 인스턴스 변수
    private E _element;
    private LinkedNode<E> _next;

    // getter, setter
    public E element(){
        return this._element;
    }
    public void setElement(E newElement){
        this._element = newElement;
    }

    public LinkedNode<E> next(){
        return this._next;
    }
    public void setNext(LinkedNode<E> newNext){
        this._next = newNext;
    }

    // 생성자
    public LinkedNode(){
        this.setElement(null);
        this.setNext(null);
    }
    public LinkedNode(E givenElement, LinkedNode<E> givenNext){
        this.setElement(givenElement);
        this.setNext(givenNext);
    }
}
