package ui;

import logic.Board;
import logic.Game;
import logic.Piece;
import logic.Square;

import javax.swing.*;

public class BoardController {

    private Game game;

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
}
