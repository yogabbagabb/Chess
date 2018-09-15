
public class Board {

    private int length;
    private int width;
    private Piece [][] piecePositions;

    public Board(int length, int width, Piece [][] piecePositions)
    {
        this.length = length;
        this.width = width;
        this.piecePositions = piecePositions;
    }

//    public Board(int length, int width, int typeOfBoard, boolean whiteBelow)
//    {
//        this.length = length;
//        this.width = width;
//        if (typeOfBoard == Game.STANDARD_BOARD)
//        {
//            initializeStandardBoard(whiteBelow);
//        }
//    }



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

    public Piece getPieceAtPosition(int xPos, int yPos)
    {
        return piecePositions[xPos][yPos];
    }

    public void setPieceAtPosition(int xPos, int yPos, Piece replacementPiece)
    {
        piecePositions[xPos][yPos] = replacementPiece;
    }

}
