package ui;


import logic.Square;
import logic.Game;

import javax.swing.*;
import java.awt.*;

public class BoardView {
    private JFrame frame;
    private JPanel optionsPanel;// = new JPanel(new BorderLayout(2,2));
    private int chessBoardWidth;
    private int chessBoardLength;
    private JPanel chessBoardPanel;
    private Square[][] chessBoardSquares;
    private final Color JADE = new Color(51, 153, 102);
    private final Color MANILA = new Color(255, 255, 179);
    private final Color[] boardColors = {JADE, MANILA};
    private final int numBoardColors = boardColors.length;

    static final int FONT_SIZE = 60;
    static final Font WRITING_FONT = new Font("Lucida Grande", Font.PLAIN, FONT_SIZE);
    private BoardController boardController;


    public BoardView(int width, int length, BoardController boardController)
    {
        chessBoardWidth = width;
        chessBoardLength = length;
        chessBoardSquares = new Square[chessBoardWidth][chessBoardLength];
        this.boardController = boardController;

        initializeFrame();
        initializeOptions();
        initializeBoard();
        addTopComponents();
    }

    public JFrame getFrame()
    {
        return frame;
    }
    public Square getSquare(int xCoor, int yCoor)
    {
        return chessBoardSquares[xCoor][yCoor];
    }

    public void initializeFrame()
    {
        frame = new JFrame("Chess Game");
        // Destroy the current window; if the current window is the only window, close the Java VM
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Set up the options panel.
     */
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

    /**
     * Set up the board panel.
     */
    public void initializeBoard()
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
                // Choose the leftmost color of row vertCounter, by choosing the offset in array
                int offsetFirstColor = verCounter % 2;
                Square square = new Square (horCounter, verCounter);
                int colorIndex = (offsetFirstColor + horCounter) % 2;
                square.setBackground(boardColors[colorIndex]);
                square.setOpaque(true);
                square.setBorderPainted(false);
                square.setEnabled(true);
                square.setText(boardController.getText(square));
                square.setFont(WRITING_FONT);
//                Font buttonFont = square.getFont();
//                System.out.println(buttonFont.getName());
//                square.setText("\u265A");
//                square.setFont(new Font(buttonFont.getName(), Font.PLAIN, 60));

//                Dimension size = square.getPreferredSize();
//                int minDimension = (size.height < size.width ? size.height : size.width);
//                System.out.println(size.height + " " + size.width);
//                square.setPreferredSize(new Dimension(minDimension,minDimension));

                chessBoardSquares[horCounter][verCounter] = square;
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
