package ui;

import logic.Board;
import logic.Game;
import logic.Piece;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {

    private Game game;
    private BoardSquare [][] chessBoardBoardSquares;

    public BoardController(Game game)
    {
        this.game = game;
    }

    public String getText(BoardSquare square)
    {
        Board gameBoard = game.getChessBoard();
        Piece pieceAtBoardSquare = gameBoard.getPieceAtPosition(square.getPosX(), square.getPosY());
        String squareText;
        if (pieceAtBoardSquare == null)
        {
            squareText = "";
        }
        else
        {
            squareText = pieceAtBoardSquare.getIconUnicodeString();
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

    public BoardSquare[][] getChessBoardBoardSquares() {
        return chessBoardBoardSquares;
    }

    public void setChessBoardBoardSquares(BoardSquare[][] chessBoardBoardSquares) {
        this.chessBoardBoardSquares = chessBoardBoardSquares;
    }
}
