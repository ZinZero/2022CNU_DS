package DS14;

public abstract class Dictionary<Key extends Comparable<Key>, Obj extends Comparable<Obj>> {
    // 비공개 인스턴스 변수
    private int _size;

    private VisitDelegate<Key, Obj> _visitDelegate;


    // getter, setter
    public int size() {
        return this._size;
    }

    protected void setSize(int newSize) {
        this._size = newSize;
    }

    public VisitDelegate<Key, Obj> visitDelegate(){
        return this._visitDelegate;
    }

    public void setVisitDelegate(VisitDelegate<Key, Obj> newVisitDelegate){
        this._visitDelegate = newVisitDelegate;
    }

    // 생성자
    public Dictionary() {
        this.setSize(0);
    }

    // non 추상메서드
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    // 추상메서드
    public abstract boolean isFull();

    public abstract boolean keyDoesExist(Key aKey);

    public abstract Obj objectForKey(Key aKey);

    public abstract boolean addKeyAndObject(Key aKey, Obj anObject);

    public abstract Obj removeObjectForKey(Key aKey);

    public abstract void clear();

    public abstract Iterator<DictionaryElement<Key, Obj>> iterator();

    public abstract void scanInSortedOrder();

    public abstract void scanInReverseOfSortedOrder();

}
