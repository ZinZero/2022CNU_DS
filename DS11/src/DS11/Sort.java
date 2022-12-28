package DS11;

public abstract class Sort <E extends Comparable<E>>{
    // Protected Method
    protected void swap(E[] aList, int p, int q){
        E tempElement = aList[p];
        aList[p] = aList[q];
        aList[q] = tempElement;
    }

    // 생성자
    protected Sort(){

    }

    // 공개함수
    public abstract boolean sort(E[] aList, int aSize);

}
