
public class Board {

    private int length;
    private int width;
    private Piece[][] piecePositions;


    public Board(int length, int width, Piece[][] piecePositions) {
        this.length = length;
        this.width = width;
        this.piecePositions = piecePositions;
    }


    /**
     * @param anotherBoard The board whose fields we wish to copy to a new board
     *                     The field's references are copied. Ie this is a shallow copy constructor.
     */
    public Board(Board anotherBoard)
    {
        length = anotherBoard.length;
        width = anotherBoard.width;
        piecePositions = new Piece[width][length];

        for (int widthIdx = 0; widthIdx < width; ++widthIdx)
        {
            for (int lengthIdx = 0; lengthIdx < length; ++length)
            {
               piecePositions[widthIdx][lengthIdx] = anotherBoard.getPieceAtPosition(widthIdx,lengthIdx);
            }
        }
    }


    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPiecePositions(Piece[][] piecePositions) {
        this.piecePositions = piecePositions;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Piece[][] getPiecePositions() {
        return piecePositions;
    }

    public Piece getPieceAtPosition(Coordinate coor)
    {
        return getPieceAtPosition(coor.getPosX(), coor.getPosY());
    }

    public Piece getPieceAtPosition(int xPos, int yPos)
    {
        return piecePositions[xPos][yPos];
    }

    public void setPieceAtPosition(Coordinate coor, Piece replacementPiece)
    {
        setPieceAtPosition(coor.getPosX(), coor.getPosY(), replacementPiece);
    }

    public void setPieceAtPosition(int xPos, int yPos, Piece replacementPiece)
    {
        piecePositions[xPos][yPos] = replacementPiece;
    }

    @Override
    public String toString() {

        String boardRep = "";

        for (int yIndex = length-1; yIndex >= 0; yIndex--)
        {
            for (int xIndex = 0; xIndex < width; xIndex++)
            {
                if (piecePositions[xIndex][yIndex] == null)
                {
                    boardRep += "X ";
                }
                else
                {
                    boardRep += piecePositions[xIndex][yIndex].toString();
                }
               boardRep += " ";
            }

            boardRep +=  "\n";
        }
        return boardRep;
    }

    public boolean contains(int posX, int posY)
    {
        return (0 <= posX && posX < width && 0 <= posY && posY < length);
    }

}
