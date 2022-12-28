package DS01;

public class AppController {
    public static final int MIN_ORDER = 3;
    public static final int MAX_ORDER = 99;

    private MagicSquare _magicSquare;

    public AppController() {
        this._magicSquare = new MagicSquare(AppController.MAX_ORDER);
    }

    public void run() {
        AppView.outputLine("<<< 마방진 풀이를 시작합니다 >>>");
        AppView.outputLine("");

        int currentOrder = AppView.inputOrder();
        OrderValidity currentValidity = OrderValidity.validityOf(currentOrder);
        while (currentValidity != OrderValidity.EndOfRun) {
            if (currentValidity == OrderValidity.Valid) {
                AppView.outputTitleWithOrder(currentOrder);
                Board solvedBoard = this._magicSquare.solve(currentOrder);
                this.showBoard(solvedBoard);
            } else {
                this.showOrderValidityErrorMessage(currentValidity);
            }
            currentOrder = AppView.inputOrder();
            currentValidity = OrderValidity.validityOf(currentOrder);
        }
        AppView.outputLine("");
        AppView.outputLine("<<< 마방진 풀이를 종료합니다 >>>");
    }

    private void showOrderValidityErrorMessage(OrderValidity orderValidity) {
        switch (orderValidity) {
            case TooSmall:
                AppView.outputLine(
                        "[오류] 차수가 너무 작습니다." + AppController.MIN_ORDER + "보다 크거나 같아야 합니다."
                );
                break;
            case TooLarge:
                AppView.outputLine(
                        "[오류] 차수가 너무 큽니다." + AppController.MAX_ORDER + "보다 작거나 같아야 합니다."
                );
                break;
            case NotOddNumber:
                AppView.outputLine("[오류] 차수가 짝수입니다. 홀수이어야 합니다.");
                break;
            default:
                break;
        }
    }

    private void showBoard(Board board) {
        CellLocation currentLoc = new CellLocation();
        this.showTitleForColumnIndexes(board.order());
        for (int row = 0; row < board.order(); row++) {
            AppView.outputRowNumber(row);
            for (int col = 0; col < board.order(); col++) {
                currentLoc.setRow(row);
                currentLoc.setCol(col);
                AppView.outputCellValue(board.cellValue(currentLoc));
            }
            AppView.outputLine("");
        }
    }

    private void showTitleForColumnIndexes(int order) {
        AppView.output("      ");
        for (int col = 0; col < order; col++) {
            AppView.output(String.format("[%3d]", col));
        }
        AppView.outputLine("");
    }

}
