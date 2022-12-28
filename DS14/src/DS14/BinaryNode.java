package DS14;

public class BinaryNode<E> {
    // 비공개 인스턴스 변수
    private E _element;
    private BinaryNode<E> _left;
    private BinaryNode<E> _right;

    // Getter/Setter
    public E element() {
        return this._element;
    }

    public void setElement(E newElement) {
        this._element = newElement;
    }

    public BinaryNode<E> left() {
        return this._left;
    }

    public void setLeft(BinaryNode<E> newLeft) {
        this._left = newLeft;
    }

    public BinaryNode<E> right() {
        return this._right;
    }

    public void setRight(BinaryNode<E> newRight) {
        this._right = newRight;
    }

    // 생성자
    public BinaryNode() {
        this(null, null, null);
    }
    public BinaryNode(E givenElement, BinaryNode<E> givenLeft, BinaryNode<E> givenRight) {
        this.setElement(givenElement);
        this.setLeft(givenLeft);
        this.setRight(givenRight);
    }
}