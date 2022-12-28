package DS06;

public class UnsortedArrayList<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 100;

    private int _capacity;
    private int _size;
    private E[] _elements;

    // getter, setter
    public int capacity() {
        return this._capacity;
    }

    public void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
    }

    public int size() {
        return this._size;
    }

    private void setSize(int newSize) {
        this._size = newSize;
    }

    private E[] elements() {
        return this._elements;
    }

    private void setElements(E[] newElements) {
        this._elements = newElements;
    }

    // 생성자
    public UnsortedArrayList() {
        this(UnsortedArrayList.DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked")
    public UnsortedArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Comparable[this.capacity()]);
    }

    // 공개함수
    public boolean isEmpty() {
        return (this.size() == 0);
    }
    public boolean isFull() {
        return (this.size() == this.capacity());
    }

    public boolean add(E anElement) {
       if (this.isFull()){
           return false;
       }
       else{
           int order = this.size();
           this.makeRoomAt(order);
           this.elements()[order] = anElement;
           this.setSize(this.size() + 1);
           return true;
       }

    }

    public E max() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            E maxElement = this.elements()[0];
            for (int i = 1; i < this.size(); i++) {
                if (maxElement.compareTo(this.elements()[i]) < 0) {
                    maxElement = this.elements()[i];
                }
            }
            return maxElement;
        }
    }

    // 비공개 함수
    private void makeRoomAt(int position) {
        for (int i = this.size(); i > position; i--) {
            this.elements()[i] = this.elements()[i - 1];
        }
    }

}

