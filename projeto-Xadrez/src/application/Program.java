package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.println("Source: ");
            ChessPosition source = UI.readChessPosition(leitorTeclado);

            System.out.println();
            System.out.println("Target: ");
            ChessPosition target = UI.readChessPosition(leitorTeclado);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
        }
    }
}
