package DS01;

public class MagicSquare {
    private static final int DEFAULT_MAX_ORDER = 99;

    private int _maxOrder;

    public int maxOrder() {
        return this._maxOrder;
    }

    private void setMaxOrder(int newMaxOrder) {
        this._maxOrder = newMaxOrder;
    }

    public MagicSquare(){
        this.setMaxOrder(MagicSquare.DEFAULT_MAX_ORDER);
    }

    public MagicSquare(int givenMaxOrder) {
        this.setMaxOrder(givenMaxOrder);
    }

    public Board solve(int anOrder) {
        if (OrderValidity.validityOf(anOrder) != OrderValidity.Valid) {
            return null;
        } else {
            Board board = new Board(anOrder);
            CellLocation currentLoc = new CellLocation(0, anOrder / 2);
            CellLocation nextLoc = new CellLocation();

            board.setCellValue(currentLoc, 1);

            int lastValue = anOrder * anOrder;
            for (int cellValue = 2; cellValue <= lastValue; cellValue++) {
                // 여기를 채울 것
                nextLoc.setRow(currentLoc.row() -1);
                nextLoc.setCol(currentLoc.col() +1);
                if (nextLoc.row() < 0) {
                    nextLoc.setRow(anOrder -1);
                }
                if (nextLoc.col() >= anOrder){
                    nextLoc.setCol(0);
                }
                if (!board.cellsEmpty(nextLoc)) {
                    // 여기를 채울 것
                    nextLoc.setRow(currentLoc.row() +1);
                    nextLoc.setCol(currentLoc.col());
                }
                currentLoc.setRow(nextLoc.row());
                currentLoc.setCol(nextLoc.col());
                board.setCellValue(currentLoc, cellValue);
            }
            return board;
        }
    }
}
