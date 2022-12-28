package DS14;

public interface VisitDelegate<Key extends Comparable<Key>, Obj extends Comparable<Obj>> {
    public void visitForSortedOrder(DictionaryElement<Key, Obj> anElement, int aLevel);

    public void visitForReverseOfSortedOrder(DictionaryElement<Key, Obj> anElement, int aLevel);

}
