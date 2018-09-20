package ui;


import logic.Board;
import logic.Square;
import logic.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardView {
    private JFrame frame;
    private JPanel optionsPanel;// = new JPanel(new BorderLayout(2,2));
    private Board chessBoard;
    private JPanel chessBoardPanel;
    private Square[][] chessBoardSquares;
    private final Color JADE = new Color(51, 153, 102);
    private final Color MANILA = new Color(255, 255, 179);
    private final Color[] boardColors = {JADE, MANILA};
    private final int numBoardColors = boardColors.length;

    public BoardView(Board chessBoard)
    {
        this.chessBoard = chessBoard;
        chessBoardSquares = new Square[chessBoard.getWidth()][chessBoard.getLength()];

        initializeFrame();
        initializeOptions();
        initializeBoard();
        addTopComponents();
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void initializeFrame()
    {
        frame = new JFrame("Chess Game");
        // Destroy the current window; if the current window is the only window, close the Java VM
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initializeOptions()
    {
        optionsPanel = new JPanel();
//        optionsPanel.setLayout(new BoxLayout (optionsPanel, BoxLayout.LINE_AXIS));
        JToolBar options = new JToolBar();
        options.setFloatable(false);
        options.add(new JButton ("Deselect Piece"));
        options.addSeparator();
        options.add(new JLabel ("Turn: White"));
        optionsPanel.add(options);
    }

    public void initializeBoard()
    {
        chessBoardPanel = new JPanel(new GridLayout(0,8));
        for (int verCounter = chessBoard.getLength()-1; verCounter >= 0; --verCounter)
        {
            for (int horCounter = 0; horCounter < chessBoard.getWidth(); ++horCounter)
            {
                // Choose the leftmost color of row vertCounter, by choosing the offset in array
                int offsetFirstColor = verCounter % 2;
                Square square = new Square (horCounter, verCounter);
                int colorIndex = (offsetFirstColor + horCounter) % 2;
                square.setBorder(new LineBorder(Color.BLACK));
                square.setBackground(boardColors[colorIndex]);
                square.setOpaque(true);
                chessBoardSquares[horCounter][verCounter] = square;
                chessBoardPanel.add(square);

            }
        }

    }

    private void addTopComponents()
    {
        frame.add(optionsPanel, BorderLayout.NORTH);
        frame.add(chessBoardPanel, BorderLayout.CENTER);
    }


    public static void main (String [] args)
    {
        Runnable r = () -> {
            Game aGame = new Game(true);
            BoardView boardUI = new BoardView(aGame.getChessBoard());

            // Get back the now initialized frame so that we can display it
            Frame frame = boardUI.getFrame();
            frame.pack();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            frame.setUndecorated(true);
            frame.setVisible(true);
        };

        SwingUtilities.invokeLater(r);
    }




}
