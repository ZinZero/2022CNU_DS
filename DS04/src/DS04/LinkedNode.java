package DS04;

public class LinkedNode<E> {
    // 비공개 인스턴스 변수
    private E _element;
    private LinkedNode<E> _next;

    public E element() {
        return this._element;
    }

    public LinkedNode<E> next() {
        return this._next;
    }

    public void setElement(E newElement) {
        this._element = newElement;
    }

    public void setNext(LinkedNode<E> newNext) {
        this._next = newNext;
    }

    public LinkedNode() {
        this.setElement(null);
        this.setNext(null);
    }

    public LinkedNode(E givenElement, LinkedNode<E> givenNext) {
        this.setElement(givenElement);
        this.setNext(givenNext);
    }

}
