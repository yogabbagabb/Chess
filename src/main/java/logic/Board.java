package logic;

/**
 * logic.Board stores the piece (or blank space) at each position of the board.
 */
public class Board {

    private int length;
    private int width;
    private Piece[][] piecePositions;


    public Board(int length, int width, Piece[][] piecePositions) {
        this.length = length;
        this.width = width;
        this.piecePositions = piecePositions;
    }


    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Piece getPieceAtPosition(Square coor)
    {
        return getPieceAtPosition(coor.getPosX(), coor.getPosY());
    }

    public Piece getPieceAtPosition(int xPos, int yPos)
    {
        return piecePositions[xPos][yPos];
    }

    public void setPieceAtPosition(Square coor, Piece replacementPiece)
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
