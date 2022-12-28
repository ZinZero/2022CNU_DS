package DS10_Array;

public class CircularArrayQueue<E> implements Queue<E>{
    // 상수
    private static final int DEFAULT_CAPACITY = 100;

    // 비공개 인스턴스 변수
    private int _maxLength; // capacity + 1
    private int _frontPosition;
    private int _rearPosition;
    private E[] _elements;

    // getter, setter
    private int maxLength(){
        return this._maxLength;
    }
    private void setMaxLength(int newMaxLength){
        this._maxLength = newMaxLength;
    }

    public int capacity(){
        return (this.maxLength() - 1);
    }

    private int frontPosition(){
        return this._frontPosition;
    }
    private void setFrontPosition(int newFrontPosition){
        this._frontPosition = newFrontPosition;
    }

    private int rearPosition(){
        return this._rearPosition;
    }
    private void setRearPosition(int newRearPosition){
        this._rearPosition = newRearPosition;
    }

    private E[] elements(){
        return this._elements;
    }
    private void setElements(E[] newElements){
        this._elements = newElements;
    }

    // 생성자
    @SuppressWarnings("Unchecked")
    public CircularArrayQueue(int givenCapacity) {
        this.setMaxLength(givenCapacity + 1);
        this.setElements((E[])new Object[this.maxLength()]);
        this.setFrontPosition(0);
        this.setRearPosition(0);
    }
    public CircularArrayQueue(){
        this(CircularArrayQueue.DEFAULT_CAPACITY);
    }

    // 공개함수
    public boolean isEmpty(){
        return (this.frontPosition() == this.rearPosition());
    }
    public boolean isFull(){
        int nextRearPosition = (this.rearPosition() + 1) % this.maxLength();
        return (nextRearPosition == this.frontPosition());
    }
    public int size(){
        if (this.frontPosition() <= this.rearPosition()){
            return (this.rearPosition() - this.frontPosition());
        }
        else {
            return ((this.rearPosition() + this.maxLength()) - this.frontPosition());
        }
    }

    public E front(){
        E frontElement = null;
        if (! this.isEmpty()){
            frontElement = this.elements()[this.frontPosition() + 1];
        }
        return frontElement;
    }

    public E rear(){
        E rearElement = null;
        if (!this.isEmpty()){
            rearElement = this.elements()[this.rearPosition()];
        }
        return rearElement;
    }

    public boolean enQueue(E anElement){
        if (this.isFull()){
            return false;
        }
        else{
            this.setRearPosition(
                    (this.rearPosition() + 1) % this.maxLength());
            this.elements()[this.rearPosition()] = anElement;
            return true;
        }
    }

    public E deQueue(){
        E frontElement = null;
        if (!this.isEmpty()){
            this.setFrontPosition((this.frontPosition() + 1) % this.maxLength());
            frontElement = this.elements()[this.frontPosition()];
            this.elements()[this.frontPosition()] = null;
        }
        return frontElement;
    }

    public void clear(){
        for (int i = this.frontPosition() + 1; i <= this.rearPosition(); i++){
            this.elements()[i] = null;
        }
        this.setFrontPosition(0);
        this.setRearPosition(0);
    }

    public E elementAt(int anOrder){
        anOrder = ((this.frontPosition() + 1 + anOrder) % this.maxLength());
        return this.elements()[anOrder];

    }

    public Iterator<E> iterator(){
        return (new CircularArrayQueueIterator());
    }

    private class CircularArrayQueueIterator implements Iterator<E>{
        private int _nextOrder;

        private int nextOrder(){
            return this._nextOrder;
        }
        private void setNextOrder(int newNextOrder) {
            this._nextOrder = newNextOrder;
        }
        private CircularArrayQueueIterator(){
            this.setNextOrder(0);
        }

        @Override
        public boolean hasNext(){
            return (this.nextOrder() < CircularArrayQueue.this.size());
        }
        @Override
        public E next(){
            E nextElement = null;
            if (this.hasNext()){
                nextElement = CircularArrayQueue.this.elementAt(this.nextOrder());
                this.setNextOrder(this.nextOrder() + 1);
            }
            return nextElement;
        }
    }
}
