package ui;


import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardView {
    private JFrame frame;
    private JPanel optionsPanel;// = new JPanel(new BorderLayout(2,2));
    private int chessBoardWidth;
    private int chessBoardLength;
    private JPanel chessBoardPanel;
    private BoardSquare[][] chessBoardBoardSquares;
    private final Color JADE = new Color(51, 153, 102);
    private final Color MANILA = new Color(255, 255, 179);

    static final int FONT_SIZE = 60;
    static final Font WRITING_FONT = new Font("Lucida Grande", Font.PLAIN, FONT_SIZE);
    private BoardController boardController;


    public BoardView(int width, int length, BoardController boardController)
    {
        chessBoardWidth = width;
        chessBoardLength = length;
        chessBoardBoardSquares = new BoardSquare[chessBoardWidth][chessBoardLength];
        this.boardController = boardController;
        boardController.setChessBoardBoardSquares(chessBoardBoardSquares);

        initializeFrame();
        initializeOptions();
        initializeBoard();
        addTopComponents();
    }

    public void addSquareListeners(ActionListener actionListener)
    {
        for (int verCounter = chessBoardLength-1; verCounter >= 0; --verCounter) {
            for (int horCounter = 0; horCounter < chessBoardWidth; ++horCounter) {
                chessBoardBoardSquares[horCounter][verCounter].addActionListener(actionListener);
            }
        }
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
//        optionsPanel.setLayout(new BoxLayout (optionsPanel, BoxLayout.LINE_AXIS));
        JToolBar options = new JToolBar();
        options.setFloatable(false);
        options.add(new JButton ("Deselect Piece"));
        options.addSeparator();
        options.add(new JButton ("Move Back"));
        options.addSeparator();
        options.add(new JButton ("Forfeit"));
        options.addSeparator();
        options.add(new JButton ("Skip Turn"));
        options.addSeparator();
        options.add(new JLabel ("Turn: White"));
        optionsPanel.add(options);
    }

    private Color getStandardColor(int horCounter, int verCounter)
    {
        Color colorToChoose = ((horCounter + verCounter) % 2 == 0? JADE: MANILA);
        return colorToChoose;
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
                square.addActionListener(boardController);
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
