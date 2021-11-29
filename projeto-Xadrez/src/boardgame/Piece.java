package boardgame;

// classe responsável pelo tabuleiro
public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    protected Board getBoard() {
        return board;
    }

    // verifica os movimentos possíveis no tabuleiro
    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] possible = possibleMoves();
        for (int i = 0; i < possible.length; i++) {
            for (int j = 0; j < possible.length; j++) {
                if (possible[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
