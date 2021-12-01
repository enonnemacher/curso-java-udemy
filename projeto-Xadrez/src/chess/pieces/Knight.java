package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
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

        position.setValues(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExists(position) && canMove(position)) {
            matrix[position.getRow()][position.getColumn()] = true;
        }

        return matrix;
    }
}
