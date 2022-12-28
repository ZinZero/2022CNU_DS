package DS06;

public class SortedArrayList<E extends Comparable<E>> {
    // Constants
    private static final int DEFAULT_CAPACITY = 100;

    // 비공개 인스턴스
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
    public void setSize(int newSize) {
        this._size = newSize;
    }

    public E[] elements() {
        return this._elements;
    }
    public void setElements(E[] newElements) {
        this._elements = newElements;
    }

    // 생성자
    public SortedArrayList() {
        this(SortedArrayList.DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked")
    public SortedArrayList(int givenCapacity) {
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
        if (this.isFull()) {
            return false;
        }
        else {
            int order = 0;
            for (int i = 0; i < this.size(); i++){
                if (this.elements()[i].compareTo(anElement) > 0){
                    break;
                }
                order = i;
            }
            if ((order >= 0) && (order <= this.size())){
                this.makeRoomAt(order);
                this.elements()[order] = anElement;
                this.setSize(this.size() + 1);
                return true;
            }
            else {
                return false;
            }
        }

    }

    public E max() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.elements()[this.size() - 1];
        }
    }

    // 비공개 함수
    private void makeRoomAt(int position) {
        for (int i = this.size(); i > position; i--) {
            this.elements()[i] = this.elements()[i - 1];
        }
    }

}
