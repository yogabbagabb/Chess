package ui;

import logic.Board;
import logic.Game;
import logic.Piece;
import logic.Square;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {

    private Game game;
    private Square [][] chessBoardSquares;

    public BoardController(Game game)
    {
        this.game = game;
    }

    public String getText(Square square)
    {
        Board gameBoard = game.getChessBoard();
        Piece pieceAtSquare = gameBoard.getPieceAtPosition(square.getPosX(), square.getPosY());
        String squareText;
        if (pieceAtSquare == null)
        {
            squareText = "";
        }
        else
        {
            squareText = pieceAtSquare.getIconUnicodeString();
        }
        return squareText;

    }


    public static void main (String [] args)
    {
        Runnable r = () -> {
            Game aGame = new Game(true);
            BoardController boardController = new BoardController(aGame);
            Board gameBoard = aGame.getChessBoard();
            BoardView boardUI = new BoardView(gameBoard.getWidth(), gameBoard.getLength(), boardController);
            boardUI.displayGame();;

        };

        SwingUtilities.invokeLater(r);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hit");
        System.out.println(e.getActionCommand());

    }

    public Square[][] getChessBoardSquares() {
        return chessBoardSquares;
    }

    public void setChessBoardSquares(Square[][] chessBoardSquares) {
        this.chessBoardSquares = chessBoardSquares;
    }
}
