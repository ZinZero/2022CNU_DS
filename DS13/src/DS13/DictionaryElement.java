package DS13;

public class DictionaryElement <Key extends Comparable<Key>, Obj extends Comparable <Obj>>{
    // 비공개 인스턴스 변수
    private Key _key;
    private Obj _object;

    // getter, setter
    public Key key(){
        return this._key;
    }

    public void setKey(Key newKey){
        this._key = newKey;
    }

    public Obj object () {
        return this._object;
    }
    public void setObject (Obj newObject) {
        this._object = newObject;
    }

    // 생성자
    public DictionaryElement () {

    }
    public DictionaryElement (Key givenKey, Obj givenObject) {
        this.setKey(givenKey);
        this.setObject(givenObject);
    }

}
