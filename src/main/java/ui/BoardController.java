package ui;

import logic.Board;
import logic.Game;
import logic.Piece;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController{

    private Game game;
    private BoardSquare [][] chessBoardBoardSquares;

    public BoardController(Game game)
    {
        this.game = game;
    }

    public void addListeners(BoardView boardView)
    {
        boardView.addSquareListeners(e -> {
            BoardSquare square = (BoardSquare)e.getSource();
            game.showInterestInPos(square.getPosX(), square.getPosY());
        });

        boardView.addDropPieceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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

            aGame.setBoardView(boardUI);
            boardController.addListeners(boardUI);

            boardUI.displayGame();;

        };

        SwingUtilities.invokeLater(r);
    }


    public BoardSquare[][] getChessBoardBoardSquares() {
        return chessBoardBoardSquares;
    }

    public void setChessBoardBoardSquares(BoardSquare[][] chessBoardBoardSquares) {
        this.chessBoardBoardSquares = chessBoardBoardSquares;
    }
}
