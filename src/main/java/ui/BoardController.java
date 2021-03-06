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

        boardView.addDropPieceListener(e -> game.recolorBoardSquares());

        boardView.addForfeitInitiationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.prepareToForfeit();
            }
        });

        boardView.addRestartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Rebuild an exact instance of the game but start the scores from 0-0
                relayRenewalChoice(true, false);
                game.updateScore();
            }
        });

        boardView.addUndoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.undoUpdateMove();
            }
        });


    }

    /**
     * Fulfill the wish of the client (user interacting with the GUI) to either play
     * another game or stop playing games at the back end level. If the player wishes to continue playing,
     * make a new game or else exit.
     * @param renew
     * @param useOldScores
     */
    public void relayRenewalChoice(boolean renew, boolean useOldScores)
    {
        if (renew == true)
        {
            Game oldGame = this.game;
            // Determine whether we were playing a nonstandard game
            boolean addNewPieces = !oldGame.isStandardGame();
            int whiteScore = useOldScores? oldGame.getWhiteScore(): 0;
            int blackScore = useOldScores? oldGame.getBlackScore(): 0;
            Game aGame = new Game (oldGame.isWhiteBelow(), addNewPieces, whiteScore, blackScore);
            // Set the Game field of this to be the new game.
            this.game = aGame;

            // Set the view of the new game to be that of the old game.
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
        // Get the piece at the coordinate of the parameter square
        Piece pieceAtBoardSquare = gameBoard.getPieceAtPosition(square.getPosX(), square.getPosY());
        String squareText;
        if (pieceAtBoardSquare == null)
        {
            squareText = "";
        }
        else
        {
            // Get the piece's chess icon.
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
            int orientationOption = JOptionPane.showOptionDialog(null, "Would you like white to be at " +
                            "the bottom of the board, or the top?", "Orientation Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String [] {"Bottom", "Top"}, null);

            boolean augmentedGame = optionChosen == 1? true: false;
            boolean whiteBelow = orientationOption == 0? true: false;
            Game aGame = new Game(whiteBelow, augmentedGame);
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
