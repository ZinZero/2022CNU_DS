package DS10_Linked;

public class CircularlyLinkedQueue <E> implements Queue<E>{
    // 비공개 인스턴스 변수
    private int _size;
    private LinkedNode<E> _rearNode;

    // getter,setter
    public int size(){
        return this._size;
    }
    private void setSize(int newSize){
        this._size = newSize;
    }
    private LinkedNode<E> rearNode(){
        return this._rearNode;
    }
    private void setRearNode(LinkedNode<E> newRearNode){
        this._rearNode = newRearNode;
    }

    public CircularlyLinkedQueue(){
        this.setSize(0);
        this.setRearNode(null);
    }

    public boolean isEmpty(){
        return (this.rearNode() == null);
    }
    public boolean isFull(){
        return false;
    }

    public E rear(){
        E rearElement = null;
        if (!this.isEmpty()){
            rearElement = this.rearNode().element();
        }
        return rearElement;
    }

    public E front(){
        E frontElement = null;
        if (!this.isEmpty()){
            frontElement = this.rearNode().next().element();
        }
        return frontElement;
    }

    public boolean enQueue(E anElement){
        LinkedNode<E> newRearNode = new LinkedNode<>(anElement, null);
        if (this.isEmpty()){
            newRearNode.setNext(newRearNode);
        }
        else{
            newRearNode.setNext(this.rearNode().next());
            this.rearNode().setNext(newRearNode);
        }
        this.setRearNode(newRearNode);
        this.setSize(this.size() + 1);
        return true;
    }

    public E deQueue(){
        E frontElement = null;
        if (!this.isEmpty()){
            frontElement = this.rearNode().next().element();
            if (this.rearNode() == this.rearNode().next()){
                this.setRearNode(null);
            }
            else{
                this.rearNode().setNext(this.rearNode().next().next());
            }
            this.setSize(this.size() - 1);
        }
        return frontElement;
    }

    public void clear(){
        this.setSize(0);
        this.setRearNode(null);
    }

    public E elementAt(int anOrder){
        if(!this.isEmpty()){
            LinkedNode<E> frontNode = this.rearNode().next();
            int count = 0;
            while (count < anOrder){
                frontNode = frontNode.next();
                count++;
            }
            return frontNode.element();
        }
        else{
            return null;
        }
    }

    @Override
    public Iterator<E> iterator(){
        return new CircularlyLinkedQueueIterator();
    }

    private class CircularlyLinkedQueueIterator implements Iterator<E>{
        private LinkedNode<E> _nextNode;
        private int _count;

        private LinkedNode<E> nextNode(){
            return this._nextNode;
        }
        private void setNextNode(LinkedNode<E> newNextNode){
            this._nextNode = newNextNode;
        }

        private int count(){
            return this._count;
        }
        private void setCount(int newCount){
            this._count = newCount;
        }

        private CircularlyLinkedQueueIterator(){
            this.setNextNode(CircularlyLinkedQueue.this.rearNode());
            this.setCount(CircularlyLinkedQueue.this.size());
        }

        @Override
        public boolean hasNext(){
            return (this.count() > 0);
        }

        @Override
        public E next(){
            E nextElement = null;
            if (this.hasNext()){
                this.setNextNode(this.nextNode().next());
                nextElement = this.nextNode().element();
                this.setCount(this.count() - 1);
                return nextElement;
            }
            else{
                return null;
            }
        }
    }
}