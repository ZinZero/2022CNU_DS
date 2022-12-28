package DS03;

public class ArrayBag <E> {
    //비공개 인스턴스 변수
    private static final int DEFAULT_CAPACITY = 100;
    private int _capacity;
    private int _size;
    private E _elements[] ; // ArrayBag 의 원소들을 담을 java 배열

    public ArrayBag(){
        this.setCapacity(ArrayBag.DEFAULT_CAPACITY);
        this.setElements((E[]) new Object[this.capacity()]);
        this.setSize(0);
    }
    public ArrayBag(int givenCapacity){
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Object[this.capacity()]);
        this.setSize(0);
    }

    private int capacity(){
        return this._capacity;
    }
    private void setCapacity(int newCapacity){
        this._capacity = newCapacity;
    }

    public int size(){
        return this._size;
    }
    private void setSize(int newSize){
        this._size = newSize;
    }

    private E[] elements(){
        return this._elements;
    }
    private void setElements(E[] newElements){
        this._elements = newElements;
    }

    public boolean isEmpty(){
        return (this.size() == 0);
    }
    public boolean isFull(){
        return (this.size() == this.capacity());
    }

    public boolean doesContain(E anElement){
        int foundIdx = -1;
        for (int i = 0; i < this.size() && foundIdx < 0; i++){
            if (this.elements()[i].equals(anElement)){
                foundIdx = i;
            }
        }
        return (foundIdx >= 0);
    }

    public int frequencyOf(E anElement){
        int frequencyCount = 0;
        for (int i = 0; i < this._size; i++){
            if (this.elements()[i].equals(anElement)){
                frequencyCount++;
            }
        }
        return frequencyCount;
    }
    public E any(){
        if (this.isEmpty()){
            return null;
        }
        else{
            return this.elements()[0];
        }
    }

    public boolean add(E anElement){
        if (this.isFull()){
        return false;
        }
        else {
            this.elements()[this.size()] = anElement;
            this.setSize(this.size() + 1);
            return true;
        }
    }

    public E removeAny() {
            if (this.isEmpty()){
                return null;
            }
            else {
                E removeElement = this.elements()[this.size() -1];
                this.elements()[this.size() -1] = null;
                this.setSize(this.size() -1);
                return removeElement;
            }
         }

    public boolean remove(E anElement) {
        int foundIndex = -1;

        for (int i = 0; i < this.size() && foundIndex < 0; i++){
            if (this.elements()[i].equals(anElement)){
                foundIndex = i;
            }
        }

        if (foundIndex < 0){
            return false;
        }
        else {
            for (int i = foundIndex; i < this.size() -1; i++){
                this.elements()[i] = this.elements()[i + 1];
            }
            this.elements()[this.size() -1] = null;
            this.setSize(this.size() -1);
            return true;
        }
    }
    public void clear() {
        for (int i = 0; i < this.size(); i++){
            this.elements()[i] = null;
        }
        this.setSize(0);
    }

    public E elementAt(int order){
        if ((0 <= order) && (order < this.size())){
            return this.elements()[order];
        }
        else {
            return null;
        }
    }
}
