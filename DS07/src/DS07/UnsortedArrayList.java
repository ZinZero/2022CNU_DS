package DS07;

import java.util.*;

public class UnsortedArrayList<E extends Comparable<E>> {
    // 상수
    private static final int DEFAULT_CAPACITY = 100;

    // 비공개 인스턴스 변수
    private int _capacity;
    private int _size;
    protected E[] _elements;

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
    @SuppressWarnings("unchecked")
    public UnsortedArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Comparable[this.capacity()]);
    }

    public UnsortedArrayList() {
        this(UnsortedArrayList.DEFAULT_CAPACITY);
    }

    // 공개함수
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return (this.size() == this.capacity());
    }

    public boolean doesContain(E anElement) {
        return (this.orderOf(anElement) >= 0);
    }

    public int orderOf(E anElement) {
        int order = -1;
        for (int index = 0; index < this.size() && order < 0; index++) {
            if (this.elements()[index].equals(anElement)) {
                order = index;
            }
        }
        return order;
    }

    public E elementAt(int anOrder) {
        if (anOrder < 0 || anOrder >= this.size()) {
            return null;
        } else {
            return this.elements()[anOrder];
        }
    }

    protected void setElementAt(int anOrder, E anElement) {
        if (anOrder < 0 || anOrder >= this.size()) {
            return;
        } else {
            this.elements()[anOrder] = anElement;
        }
    }

    // 원소 추가하기
    public boolean addToFirst(E anElement) {
        if (this.isFull()) {
            return false;
        } else {
            this.makeRoomAt(0);
            this.elements()[0] = anElement;
            this.setSize(this.size() + 1);
            return true;
        }
    }

    public boolean addToLast(E anElement) {
        if (this.isFull()) {
            return false;
        } else {
            this.elements()[this.size()] = anElement;
            this.setSize(this.size() + 1);
            return true;
        }
    }

    public boolean add(E anElement) {
        return this.addToLast(anElement);
    }

    // 원소 제거하기
    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            E removedElement = this.elements()[0];
            this.setSize(this.size() - 1);
            return removedElement;
        }
    }

    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            E removedElement = this.elements()[this.size() -1];
            this.setSize(this.size() - 1);
            return removedElement;
        }
    }

    public E removeAny() {
        return this.removeLast();
    }

    public boolean remove(E anElement) {
        // 단계 1 : 주어진 원소의 위치를 찾는다.
        int foundIndex = this.orderOf(anElement);

        // 단계 2 : 주어진 원소가 존재하면 삭제를 실행한다.
        if (foundIndex < 0) {
            return false;
        } else {
            this.removeGapAt(foundIndex);
            this.setSize(this.size() - 1);
            this._elements[this.size()] = null;
            return true;
        }
    }

    public Iterator<E> iterator() {
        return (new ListIterator());
    }

    private class ListIterator implements Iterator<E> {
        private int _nextPosition;

        private int nextPosition() {
            return this._nextPosition;
        }

        private void setNextPosition(int newNextPosition) {
            this._nextPosition = newNextPosition;
        }

        private ListIterator() {
            this.setNextPosition(0);
        }

        @Override
        public boolean hasNext() {
            return (this.nextPosition() < UnsortedArrayList.this.size());
        }

        @Override
        public E next() {
            E nextElement = null;
            if (this.hasNext()) {
                nextElement = UnsortedArrayList.this.elements()[this.nextPosition()];
                this.setNextPosition(this.nextPosition() + 1);
            }
            return nextElement;
        }
    }

    // 비공개함수
    private void makeRoomAt(int position) {
        for (int i = this.size(); i > position; i--) {
            this.elements()[i] = this.elements()[i - 1];
        }
    }

    private void removeGapAt(int position) {
        for (int i = position + 1; i < this.size(); i++) {
            this.elements()[i - 1] = this.elements()[i];
        }
        this.elements()[this.size() - 1] = null;
    }
}
