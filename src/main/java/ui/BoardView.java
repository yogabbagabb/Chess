package ui;


import logic.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BoardView {
    private JFrame frame;
    private JPanel optionsPanel;// = new JPanel(new BorderLayout(2,2));
    private int chessBoardWidth;
    private int chessBoardLength;
    private JPanel chessBoardPanel;
    private BoardSquare[][] chessBoardBoardSquares;
    private final Color JADE = new Color(51, 153, 102);
    private final Color MANILA = new Color(255, 255, 179);
    private final Color NAVY = new Color(89, 66, 244);

    static final int FONT_SIZE = 60;
    static final Font WRITING_FONT = new Font("Lucida Grande", Font.PLAIN, FONT_SIZE);
    private BoardController boardController;

    //optionsPanelPieces
    private JButton deselectButton;
    private JButton undoButton;
    private JButton forfeitButton;
    private JButton restartButton;
    private JLabel turnLabel;

    //Score Label
    private JLabel whiteScoreLabel;
    private JLabel blackScoreLabel;
    private final String whiteScorePreface = "White Score: ";
    private final String blackScorePreface = "Black Score: ";

    public BoardView(int width, int length, BoardController boardController)
    {
        chessBoardWidth = width;
        chessBoardLength = length;
        chessBoardBoardSquares = new BoardSquare[chessBoardWidth][chessBoardLength];
        this.boardController = boardController;

        initializeFrame();
        initializeOptions();
        initializeBoard();
        addTopComponents();
    }

    /**
     * Highlight the square of the piece that a player has chosen to work with during her turn.
     * @param square The square on which the chosen piece is located.
     */
    public void highlightChosenPiece(Square square)
    {
        BoardSquare squareButton = chessBoardBoardSquares[square.getPosX()][square.getPosY()];
        squareButton.setBackground(NAVY);
        squareButton.repaint();

    }

    /**
     * Highlight squares to indicate the locations that the chosen piece can access.
     * @param boardSquareList A list of squares that the piece can access.
     */
    public void highlightSquares(Set<Square> boardSquareList)
    {
        for (Square square : boardSquareList)
        {
            BoardSquare squareButton = chessBoardBoardSquares[square.getPosX()][square.getPosY()];
            squareButton.setBackground(Color.WHITE);
            squareButton.setBorderPainted(true);
            squareButton.repaint();
        }

    }

    /**
     * Update the icons of a list of squares.
     * @param boardSquareList The list of squares on which piece icons may have changed.
     */
    public void updatePiecesOnSquares(Set <Square> boardSquareList)
    {
        for (Square square : boardSquareList)
        {
            BoardSquare squareButton = chessBoardBoardSquares[square.getPosX()][square.getPosY()];
            squareButton.setText(boardController.getText(squareButton));
            squareButton.repaint();
        }

    }

    /**
     * Restore the colors of squares to their original ones.
     * @param boardSquareList A list of squares.
     */
    public void restoreOriginalColors(Set<Square> boardSquareList)
    {
        for (Square square : boardSquareList)
        {
            BoardSquare squareButton = chessBoardBoardSquares[square.getPosX()][square.getPosY()];
            restoreBoardColor(squareButton);
            squareButton.setBorderPainted(false);
            squareButton.repaint();
        }

    }

    /**
     * Query the user for information as to who wishes to forfeit.
     */
    public void confirmForfeitChoice()
    {
       int optionChosen = JOptionPane.showOptionDialog(frame, "Who wants to lose the game: Black or White?", "Forfeit", JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE, null, new String [] {"White", "Black"}, null);

       boardController.relayForfeit(optionChosen);


    }

    /**
     * Tell the current player that she is selecting a piece that is not his.
     */
    public void showWrongPlayerDialog()
    {
        JOptionPane.showMessageDialog(frame, "That's not your piece dumbass.");
    }

    /**
     * Tell the current player that she is making an illegal move.
     */
    public void showIllegalMoveDialog()
    {
        JOptionPane.showMessageDialog(frame, "Illegal move mate.");
    }

    /**
     *  Tell the user that they have either been checked, checkmated or stalemated.
     * @param endStateString
     */
    public void showEndStateDialog(String endStateString)
    {
        JOptionPane.showMessageDialog(frame, endStateString);
    }

    /**
     * Ask the user if they wish to play another game.
     */
    public void showGameRenewalDialog()
    {
        int optionChosen = JOptionPane.showOptionDialog(frame, "Play another game?", "Player Again",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        int yesOption = 0;
        boardController.relayRenewalChoice(optionChosen == yesOption? true: false, true);
    }

    /**
     * Update the tool bar display's current player field to the new player.
     * @param currentPlayerID The ID of the current player (0 -- White and 1 -- Black)
     */
    public void changePlayer(int currentPlayerID)
    {
        int whiteID = 0;
        String colorOfTurn = currentPlayerID == whiteID? "White" : "Black";
        turnLabel.setText("Turn: " + colorOfTurn);
        turnLabel.repaint();
    }

    /**
     * Add event listeners to each of the pieces on the board.
     * @param actionListener The listener each piece will register.
     */
    public void addSquareListeners(ActionListener actionListener)
    {
        for (int verCounter = chessBoardLength-1; verCounter >= 0; --verCounter) {
            for (int horCounter = 0; horCounter < chessBoardWidth; ++horCounter) {
                chessBoardBoardSquares[horCounter][verCounter].addActionListener(actionListener);
            }
        }
    }

    /**
     * Both update the pieces on all squares and repaint all squares to be what they were originally.
     */
    public void resetBoard()
    {
        Set <Square> allSquareList = new LinkedHashSet<>();
        for (int verCounter = chessBoardLength-1; verCounter >= 0; --verCounter) {
            for (int horCounter = 0; horCounter < chessBoardWidth; ++horCounter) {
               allSquareList.add(Square.getCoordinate(horCounter,verCounter));
            }
        }

        updatePiecesOnSquares(allSquareList);
        restoreOriginalColors(allSquareList);
    }

    /**
     * Add a listener to the deselect button.
     * @param actionListener Listener to add.
     */
    public void addDropPieceListener(ActionListener actionListener)
    {
        deselectButton.addActionListener(actionListener);
    }

    /**
     * Add a listener to the forfeit button.
     * @param actionListener Listener to add.
     */
    public void addForfeitInitiationListener(ActionListener actionListener)
    {
        forfeitButton.addActionListener(actionListener);
    }

    /**
     * Add a listener to the restart button.
     * @param actionListener Listener to add.
     */
    public void addRestartListener(ActionListener actionListener)
    {
        restartButton.addActionListener(actionListener);
    }

    /**
     * Add a listener to the undo button.
     * @param actionListener Listener to add.
     */
    public void addUndoListener(ActionListener actionListener)
    {
        undoButton.addActionListener(actionListener);
    }



    public JFrame getFrame()
    {
        return frame;
    }
    public BoardSquare getBoardSquare(int xCoor, int yCoor)
    {
        return chessBoardBoardSquares[xCoor][yCoor];
    }

    private void initializeFrame()
    {
        frame = new JFrame("Chess Game");
        // Destroy the current window; if the current window is the only window, close the Java VM
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Set up the options panel.
     */
    private void initializeOptions()
    {
        optionsPanel = new JPanel();
        JToolBar options = new JToolBar();
        // Make the tool bar fixed
        options.setFloatable(false);
        // Add all the buttons onto our options panel
        deselectButton = new JButton("Deselect Piece");
        options.add(deselectButton);
        options.addSeparator();

        undoButton = new JButton("Undo");
        options.add(undoButton);
        options.addSeparator();

        forfeitButton = new JButton("Forfeit");

        options.add(forfeitButton);
        options.addSeparator();

        restartButton = new JButton("Restart");

        options.add(restartButton);
        options.addSeparator();

        turnLabel = new JLabel("Turn: White");
        options.add(turnLabel);
        int distanceApart = 30;
        int height = 0;
        options.addSeparator(new Dimension(distanceApart, height));

        // Set the player names on the UI and save them into the game
        String whiteName = JOptionPane.showInputDialog("White: Please give me your name");
        String blackName = JOptionPane.showInputDialog("Black: Please give me your name");
        boardController.setPlayerNames(List.of(whiteName, blackName));
        options.addSeparator(new Dimension(distanceApart, height));
        options.add(new JLabel("White: " + whiteName));
        options.addSeparator();
        options.add(new JLabel("Black: " + blackName));

        options.addSeparator(new Dimension(distanceApart, height));
        whiteScoreLabel = new JLabel (whiteScorePreface + Integer.toString(boardController.getWhiteScore()));
        blackScoreLabel = new JLabel (blackScorePreface + Integer.toString(boardController.getBlackScore()));
        options.add(whiteScoreLabel);
        options.addSeparator();
        options.add(blackScoreLabel);


        optionsPanel.add(options);
    }

    /**
     * Set the square to be its standard color.
     * @param boardSquare The square whose color we wish to reset.
     */
    private void restoreBoardColor(BoardSquare boardSquare)
    {
        boardSquare.setBackground(getStandardColor(boardSquare.getPosX(), boardSquare.getPosY()));
    }

    /**
     * Get the standard color of a square located at a certain position.
     * @param horCounter The x coordinate of the position.
     * @param verCounter The y coordinate of the position.
     * @return The standard color.
     */
    private Color getStandardColor(int horCounter, int verCounter)
    {
        Color colorToChoose = ((horCounter + verCounter) % 2 == 0? JADE: MANILA);
        return colorToChoose;
    }

    public void updateScore()
    {
        whiteScoreLabel.setText(whiteScorePreface + Integer.toString(boardController.getWhiteScore()));
        blackScoreLabel.setText(blackScorePreface + Integer.toString(boardController.getBlackScore()));
    }

    /**
     * Set up the board panel.
     */
    private void initializeBoard()
    {
        chessBoardPanel = new JPanel(new GridLayout(0,8));
//        Dimension aSize = chessBoardPanel.getPreferredSize();
//        int aMinDimension = (aSize.height < aSize.width ? aSize.height : aSize.width);
//        System.out.println(aSize.height + " " + aSize.width);
//        chessBoardPanel.setPreferredSize(new Dimension(aMinDimension + 10,aMinDimension + 10));
        for (int verCounter = chessBoardLength-1; verCounter >= 0; --verCounter)
        {
            for (int horCounter = 0; horCounter < chessBoardWidth; ++horCounter)
            {
                BoardSquare square = new BoardSquare (horCounter, verCounter);
                square.setBackground(getStandardColor(horCounter, verCounter));
                square.setOpaque(true);
                square.setBorderPainted(false);
                square.setEnabled(true);
                square.setText(boardController.getText(square));
                square.setFont(WRITING_FONT);
                square.setActionCommand(Integer.toString(horCounter) + "," + Integer.toString(verCounter));
//                Font buttonFont = square.getFont();
//                System.out.println(buttonFont.getName());
//                square.setText("\u265A");
//                square.setFont(new Font(buttonFont.getName(), Font.PLAIN, 60));

//                Dimension size = square.getPreferredSize();
//                int minDimension = (size.height < size.width ? size.height : size.width);
//                System.out.println(size.height + " " + size.width);
//                square.setPreferredSize(new Dimension(minDimension,minDimension));

                chessBoardBoardSquares[horCounter][verCounter] = square;
                chessBoardPanel.add(square);

            }
        }


    }

    /**
     * Add the options panel and board panel to the main frame.
     */
    private void addTopComponents()
    {
        frame.add(optionsPanel, BorderLayout.NORTH);
        frame.add(chessBoardPanel, BorderLayout.CENTER);
    }

    public void displayGame()
    {
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }






}
