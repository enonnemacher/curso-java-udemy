package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position position = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            position.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                matrix[position.getRow()][position.getColumn()] = true;
            }
            position.setValues(position.getRow() - 2, position.getColumn());
            Position positionAux = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(position) &&
                    !getBoard().thereIsAPiece(position) &&
                    getBoard().positionExists(positionAux) &&
                    !getBoard().thereIsAPiece(positionAux) &&
                    getMoveCount() == 0) {
                matrix[position.getRow()][position.getColumn()] = true;
            }
            position.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                matrix[position.getRow()][position.getColumn()] = true;
            }
            position.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                matrix[position.getRow()][position.getColumn()] = true;
            }

            // specialmove en passant white color
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    matrix[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(left) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    matrix[right.getRow() - 1][right.getColumn()] = true;
                }
            }

        } else {
            position.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                matrix[position.getRow()][position.getColumn()] = true;
            }
            position.setValues(position.getRow() + 2, position.getColumn());
            Position positionAux = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(position) &&
                    !getBoard().thereIsAPiece(position) &&
                    getBoard().positionExists(positionAux) &&
                    !getBoard().thereIsAPiece(positionAux) &&
                    getMoveCount() == 0) {
                matrix[position.getRow()][position.getColumn()] = true;
            }
            position.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                matrix[position.getRow()][position.getColumn()] = true;
            }
            position.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                matrix[position.getRow()][position.getColumn()] = true;
            }

            // specialmove en passant black color
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    matrix[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(left) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    matrix[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return matrix;
    }

    @Override
    public String toString() {
        return "P";
    }
}
