package DS13;

public class ArrayStack<E> implements Stack<E> {

	private static final int DEFAULT_CAPACITY = 1000;

	private int _capacity;
	private int _size;
	private E[] _elements;

	public int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	@Override
	public int size() {
		return this._size;
	}

	public void setSize(int newSize) {
		this._size = newSize;
	}

	private E[] elements() {
		return this._elements;
	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	public ArrayStack() {
		this(ArrayStack.DEFAULT_CAPACITY); 
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
	}

	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { 
			this.elements()[i] = this.elements()[i - 1];
		}
	}

	private void removeGapAt(int aPosition) {
		for (int i = aPosition + 1; i < this.size(); i++) { 
			this.elements()[i - 1] = this.elements()[i]; 
		}
		this.elements()[this.size() - 1] = null; 
	}

	@Override
	public boolean isFull() { 
		return (this.capacity() == this.size());
	}

	@Override
	public boolean isEmpty() {
		return (this.size() == 0);
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
		} 
		else {
			return this.elements()[anOrder];
		}
	}

	public void setElementsAt(int anOrder, E anElement) { 
		if (anOrder < 0 || anOrder >= this.size()) { 
			return;
		} 
		else {
			this.elements()[anOrder] = anElement; 
		}
	}

	public boolean addTo(E anElement, int anOrder) { 
		if (this.isFull()) { 
			return false; 
		} 
		else if (anOrder < 0 || anOrder > this.size()) {
			return false; 
		} 
		else {
			this.makeRoomAt(anOrder);
			this.elements()[anOrder] = anElement;
			this.setSize(this.size() + 1); 
			return true;
		}
	}

	public boolean addToFirst(E anElement) {
		return addTo(anElement, 0);
	}

	public boolean addToLast(E anElement) { 
		return addTo(anElement, this.size());
	}

	public E removeFrom(int anOrder) {
		if (anOrder < 0 || anOrder >= this.size()) {
			return null; 
		} 
		else {
			E removedElement = this.elements()[anOrder];
			this.removeGapAt(anOrder);
			this.setSize(this.size() - 1);
			return removedElement;
		}
	}

	public E removeFirst() { 
		return removeFrom(0);
	}

	public E removeLast() { 
		return removeFrom(this.size() - 1);
	}

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
		if (this.isEmpty()) { 
			return null;
		} 
		else {
			return this.elementAt(this.size() - 1); 
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.size(); i++) { 
			this.elements()[i] = null; 
		}
		this.setSize(0); 
	}

}
