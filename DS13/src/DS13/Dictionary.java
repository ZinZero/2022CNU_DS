package DS13;

public abstract class Dictionary<Key extends Comparable<Key>, Obj extends Comparable<Obj>> {
    // 비공개 인스턴스 변수
    private int _size;

    // getter, setter
    public int size() {
        return this._size;
    }

    protected void setSize(int newSize) {
        this._size = newSize;
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

}
