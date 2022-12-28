package DS01;

public class Board {
    private static int EMPTY_CELL = -1;

    private int _order;
    private int[][] _cells;

    public int order() {

        return this._order;
    }

    private void setOrder(int newOrder) {
        this._order = newOrder;
    }

    public Board(int givenOrder) {
        this.setOrder(givenOrder);
        this.setCells(new int[givenOrder][givenOrder]);
        for (int row = 0; row < givenOrder; row++) {
            for (int col = 0; col < givenOrder; col++) {
                this.setCellValue(row, col, Board.EMPTY_CELL);
            }
        }
    }

    public boolean cellsEmpty(CellLocation location) {
        return (this.cellValue(location) == EMPTY_CELL);
    }

    private void setCells(int[][] newCells) {
        this._cells = newCells;
    }

    public int cellValue(CellLocation location) {
        return this._cells[location.row()][location.col()];
    }

    public void setCellValue(CellLocation location, int value) {
        this._cells[location.row()][location.col()] = value;
    }

    private void setCellValue(int row, int col, int value) {
        this._cells[row][col] = value;
    }
}
