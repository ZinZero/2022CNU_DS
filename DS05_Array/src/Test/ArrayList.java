//package Test;
//
//
//import java.util.*;
//
//public class ArrayList<E> {
//    private static final int DEFAULT_CAPACITY = 100;
//
//    private int _capacity;
//    private int _size;
//    private E[] _elements;
//
//    // getter, setter
//    public int capacity() {
//        return this._capacity;
//    }
//    public void setCapacity(int newCapacity) {
//        this._capacity = newCapacity;
//    }
//
//    public int size() {
//        return this._size;
//    }
//    private void setSize(int newSize) {
//        this._size = newSize;
//    }
//
//    private E[] elements() {
//        return this._elements;
//    }
//    private void setElements(E[] newElements) {
//        this._elements = newElements;
//    }
//
//    // 생성자
//    @SuppressWarnings("unchecked")
//    public ArrayList(int givenCapacity) {
//        this.setCapacity(givenCapacity);
//        this.setElements((E[]) new Object [this.capacity()]);
//    }
//    public ArrayList() {
//        this(ArrayList.DEFAULT_CAPACITY);
//    }
//
//    // 공개함수
//    // 상태 알아보기
//    public boolean isEmpty() {
//        return (this.size() == 0);
//    }
//    public boolean isFull() {
//        return (this.size() == this.capacity());
//    }
//
//    public E elementAt(int order) {
//        if (this.anElementDoesExistAt(order)) {
//            int position = order;
//            return this.elements()[position];
//        } else {
//            return null;
//        }
//    }
//
//    public E first(){
//        if (this.isEmpty()){
//            return null;
//        }
//        else {
//            return this.elements()[0];
//        }
//    }
//
//    public E last() {
//        if (this.isEmpty()) {
//            return null;
//        } else {
//            return this.elements()[this.size() - 1];
//        }
//    }
//
//    public boolean doesContain(E anElement) {
//        for (int i = 0; i < this.size(); i++) {
//            if (this.elements()[i].equals(anElement)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public int orderOf(E anElement) {
//        for (int order = 0; order < this.size(); order++) {
//            if (this.elements()[order].equals(anElement)) {
//                return order;
//            }
//        }
//        return -1;
//    }
//
//    // 원소 추가하기
//    public boolean addTo(E anElement, int order) {
//        if (this.isFull()) {
//            return false;
//        } else {
//            if ((order >= 0) && (order <= this.size())) {
//                this.makeRoomAt(order);
//                this.elements()[order] = anElement;
//                this.setSize(this.size() + 1);
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//    public boolean addToFirst(E anElement) {
//        return this.addTo(anElement, 0);
//    }
//    public boolean addToLast(E anElement) {
//        return this.addTo(anElement, this.size());
//    }
//    public boolean add(E anElement) {
//        return this.addToLast(anElement);
//    }
//
//    //  원소 제거하기
//    public E removeFrom(int order) {
//        E removedElement = null;
//        if (this.anElementDoesExistAt(order)) {
//            removedElement = this.elements()[order];
//            this.removeGapAt(order);
//            this.setSize(this.size() - 1);
//        }
//        return removedElement;
//    }
//    public E removeFirst() {
//        return removeFrom(0);
//    }
//    public E removeLast() {
//        return removeFrom(this.size() - 1);
//    }
//    public E removeAny() {
//        return removeLast();
//    }
//    public boolean remove(E anElement) {
//        int orderOfRemove = this.orderOf(anElement);
//        if (orderOfRemove < 0) {
//            return false;
//        } else {
//            this.removeGapAt(orderOfRemove);
//            this.setSize(this.size() - 1);
//            return true;
//        }
//    }
//
//    // 원소 변경하기
//    public boolean replaceAt(E anElement, int order) {
//        if (this.anElementDoesExistAt(order)) {
//            this.elements()[order] = anElement;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // clear
//    public void clear() {
//        for (int i = 0; i < this.size(); i++) {
//            this.elements()[i] = null;
//        }
//        this.setSize(0);
//    }
//
//
//
//    // 비공개 함수
//    private boolean anElementDoesExistAt(int order) {
//        return ((order >= 0) && (order < this.size()));
//    }
//
//    private void makeRoomAt(int position) {
//        for (int i = this.size(); i > position; i--) {
//            this.elements()[i] = this.elements()[i - 1];
//        }
//    }
//
//    private void removeGapAt(int position) {
//        for (int i = position + 1; i < this.size(); i++) {
//            this.elements()[i - 1] = this.elements()[i];
//        }
//        this.elements()[this.size() - 1] = null;
//    }
//
//    public Iterator<E> iterator(){
//        return (new ListIterator());
//    }
//
//    private class ListIterator<E> implements Iterator<E>
//    {
//        private int _nextPosition;
//
//        private ListIterator(){
//            this._nextPosition = 0;
//        }
//
//        @Override
//        public boolean hasNext(){
//            return (this._nextPosition < ArrayList.this.size());
//        }
//
//        @Override
//        public E next(){
//            if (this._nextPosition == ArrayList.this.size()){
//                return null;
//            }
//            else {
//                E nextElement = ArrayList.this._elements[this._nextPosition];
//                this._nextPosition++;
//                return nextElement;
//            }
//        }
//    }
//
//    public interface Iterator<E>{
//        public boolean hasNext();
//        public E next();
//    }
//
//
//
//}
//
