package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

import java.io.PipedOutputStream;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    // verifica se a peça pode mover para determinada posição
    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position position = new Position(0, 0);

        // above
        position.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // below
        position.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // left
        position.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // right
        position.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // nw
        position.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // ne
        position.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // sw
        position.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // se
        position.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        // special move castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // special move castling kingside rook
            Position positionRookRight = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(positionRookRight)) {
                Position position1 = new Position(position.getRow(), position.getColumn() + 1);
                Position position2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(position1) == null && getBoard().piece(position2) == null) {
                    matrix[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            // special move castling queenkingside rook
            Position positionRookLeft = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(positionRookLeft)) {
                Position position1 = new Position(position.getRow(), position.getColumn() - 1);
                Position position2 = new Position(position.getRow(), position.getColumn() - 2);
                Position position3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(position1) == null && getBoard().piece(position2) == null && getBoard().piece(position3) == null) {
                    matrix[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return matrix;
    }
}
