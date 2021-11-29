package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

// esta classe compõe as regras do jogo de xadrez
public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    // retorna a matriz de peças da partida de xadrez
    public ChessPiece[][] getPieces() {
        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                chessPieces[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return chessPieces;
    }

    // coloca uma nova peça no tabuleiro
    private void placeNewPiece(Character column, Integer row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    // responsável por iniciar a partida, colocando as peças no tabuleiro
    private void initialSetup() {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
    }
}
