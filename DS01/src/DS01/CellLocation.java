package DS01;

public class CellLocation {
    private static final int UndefinedIndex = -1;

    private int _row;
    private int _col;

    public CellLocation() {
        this.setRow(UndefinedIndex);
        this.setCol(UndefinedIndex);
    }

    public CellLocation(int givenRow, int givenCol) {
        this.setRow(givenRow);
        this.setCol(givenCol);
    }

    public void setRow(int newRow) {
        this._row = newRow;
    }

    public int row() {
        return this._row;
    }

    public void setCol(int newCol) {
        this._col = newCol;
    }

    public int col() {
        return this._col;
    }
}
