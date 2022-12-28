package DS14;

public class LinkedStack<E> implements Stack<E> {
    // 비공개 인스턴스 변수
    private int _size;
    private LinkedNode<E> _top;

    // getter, setter
    @Override
    public int size() {
        return this._size;
    }

    private void setSize(int newSize) {
        this._size = newSize;
    }

    private LinkedNode<E> top() {
        return this._top;
    }

    private void setTop(LinkedNode<E> newTop) {
        this._top = newTop;
    }

    // 생성자
    public LinkedStack() {
        this.setSize(0);
        this.setTop(null);
    }

    // 인터페이스 구현
    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public E elementAt(int order) {
        if (order < 0 || order >= this.size()) {
            return null;
        } else {
            LinkedNode<E> currentNode = this.top();
            int count = 0;
            while (count < order) {
                currentNode = currentNode.next();
                count++;
            }
            return currentNode.element();
        }
    }

    public E last() {
        return this.elementAt(this.size() - 1);
    }

    public boolean addToLast(E anElement) {
        if (this.isFull()) {
            return false;
        } else {
            LinkedNode<E> nodeForAdd = new LinkedNode<E>();
            nodeForAdd.setElement(anElement);
            nodeForAdd.setNext(null);
            if (this.top() == null) {
                this.setTop(nodeForAdd);
            } else {
                LinkedNode<E> currentNode = this.top();
                while (currentNode.next() != null) {
                    currentNode = currentNode.next();
                }
                currentNode.setNext(nodeForAdd);
            }
            this.setSize(this.size() + 1);
            return true;
        }
    }

    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            E removedElement = null;
            if (this.size() == 1) {
                removedElement = this._top.element();
                this.setTop(null);
            } else {
                LinkedNode<E> previousNode = this.top();
                LinkedNode<E> currentNode = this.top().next();
                while (currentNode.next() != null) {
                    previousNode = currentNode;
                    currentNode = currentNode.next();
                }
                removedElement = currentNode.element();
                previousNode.setNext(null);
            }
            this.setSize(this.size() - 1);
            return removedElement;
        }
    }

    // 스택 기능
    @Override
    public boolean push(E anElement) {
        return this.addToLast(anElement);
    }

    @Override
    public E pop() {
        return this.removeLast();
    }

    @Override
    public E peek() {
        return this.last();
    }

    @Override
    public void clear() {
        this.setTop(null);
        this.setSize(0);
    }

}
