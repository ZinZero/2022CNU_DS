package DS04;

public class LinkedBag<E> {
    // 비공개 인스턴스 변수
    private int _size;
    private LinkedNode<E> _head;

    public int size() {
        return this._size;
    }

    private void setSize(int newSize) {
        this._size = newSize;
    }

    private LinkedNode<E> head() {
        return this._head;
    }

    private void setHead(LinkedNode<E> newHead) {
        this._head = newHead;
    }

    public LinkedBag() {
        this.setSize(0);
        this.setHead(null);
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return false;
    }

    public boolean doesContain(E anElement) {
        LinkedNode<E> currentNode = this.head();
        while (currentNode != null) {
            if (currentNode.element().equals(anElement)) {
                return true;
            }
            currentNode = currentNode.next();
        }
        return false;
    }

    public int frequencyOf (E anElement)
    {
        int frequencyCount = 0 ;
        LinkedNode<E> currentNode = this.head() ; while ( currentNode != null ) {
        if ( currentNode.element().equals(anElement) ) {
            frequencyCount ++ ;
        }
        currentNode = currentNode.next() ; }
        return frequencyCount ;
    }
    public E any(){
        if (this.isEmpty()){
            return null;
        }
        else{
            return this.head().element();
        }
    }

    public boolean add(E anElement){
        if (this.isFull()){
            return false ;
        }
        else {
            LinkedNode<E> newNode = new LinkedNode<E>() ; newNode.setElement(anElement) ;
            newNode.setNext (this.head()) ;
            this.setHead (newNode) ;
            this.setSize (this.size()+1) ;
            return true ;
        }
    }

    public E removeAny(){
        if (this.isEmpty()){
            return null ;
        }
        else {
            E removedElement = this.head().element() ;
            this.setHead (this.head().next()) ;
            this.setSize (this.size()-1) ;
            return removedElement ;
        }
    }

    public boolean remove(E anElement){
        if ( this.isEmpty() ) {
            return false ;
        }
        else{
            // linkedBag 2 15페이지 부터 다시 
        }
    }
}
