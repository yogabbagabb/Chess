package ui;

import logic.Board;
import logic.Game;
import logic.Piece;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BoardController{

    private Game game;
//    private BoardSquare [][] chessBoardBoardSquares;

    public BoardController(Game game)
    {
        this.game = game;
    }

    /**
     * Add listeners to the buttons in the UI that a player can interact with.
     * @param boardView The UI (view) exposed to a player.
     */
    public void addListeners(BoardView boardView)
    {
        boardView.addSquareListeners(e -> {
            BoardSquare square = (BoardSquare)e.getSource();
            game.showInterestInPos(square.getPosX(), square.getPosY());
        });

        boardView.addDropPieceListener(e -> game.dropPiece());

        boardView.addForfeitInitiationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.prepareToForfeit();
            }
        });


    }

    public void relayRenewalChoice(boolean renew)
    {
        if (renew == true)
        {
            Game oldGame = this.game;
            Game aGame = new Game (true, false, oldGame.getWhiteScore(), oldGame.getBlackScore());
            this.game = aGame;

            aGame.setOldBoardView(oldGame);
            aGame.reset();
        }
        else
        {
            System.exit(0);
        }
    }

    /**
     * Pass to game the side that chose to loseGame
     * @param sideChosen The side that chooses to loseGame. The side is white if sideChosen == WHITE_ID and black otherwise.
     */
    public void relayForfeit(int sideChosen)
    {
        game.loseGame(sideChosen);
    }

    /**
     * Get the icon of a piece situated at the position of square.
     * @param square A square on the game board.
     * @return The icon of the piece at square.
     */
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

    public int getWhiteScore()
    {
        return game.getWhiteScore();
    }

    public int getBlackScore()
    {
        return game.getBlackScore();
    }

    public void setPlayerNames(List<String> playerNames)
    {
        game.setPlayerNames(playerNames);
    }


    public static void main (String [] args)
    {
        Runnable r = () -> {

            int optionChosen = JOptionPane.showOptionDialog(null, "What type of game do you want to play?", "Game Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String [] {"Regular Game", "Aahan's Game"}, null);

            boolean augmentedGame = optionChosen == 1? true: false;
            Game aGame = new Game(true, augmentedGame);
            BoardController boardController = new BoardController(aGame);
            Board gameBoard = aGame.getChessBoard();
            BoardView boardView = new BoardView(gameBoard.getWidth(), gameBoard.getLength(), boardController);

            aGame.setBoardView(boardView);
            boardController.addListeners(boardView);

            boardView.displayGame();;

        };

        SwingUtilities.invokeLater(r);
    }


}
