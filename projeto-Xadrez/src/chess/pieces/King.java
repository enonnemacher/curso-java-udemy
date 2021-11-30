package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

import java.io.PipedOutputStream;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
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

        return matrix;
    }
}
