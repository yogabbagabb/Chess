package logic;

import java.util.List;
import java.util.Objects;


/**
 * logic.Piece is the parent for all actual chess types.
 */
public abstract class Piece {
    static final String WHITE_PAWN = "\u2659";
    static final String BLACK_PAWN = "\u265F";
    static final String WHITE_ROOK = "\u2656";
    static final String BLACK_ROOK = "\u265C";
    static final String WHITE_KNIGHT = "\u2658";
    static final String BLACK_KNIGHT = "\u265E";
    static final String WHITE_BISHOP = "\u2657";
    static final String BLACK_BISHOP = "\u265D";
    static final String WHITE_QUEEN = "\u2655";
    static final String BLACK_QUEEN = "\u265B";
    static final String WHITE_KING = "\u2654";
    static final String BLACK_KING = "\u265A";
    private Square position;
    private int playerID;
    private String iconUnicodeString;


    private List <MOVE_PATTERN> movePatterns;

    public Piece(Square position, int playerID, List <MOVE_PATTERN> movePatterns, String iconUnicodeString)
    {
        this.position = position;
        this.playerID = playerID;
        this.movePatterns = movePatterns;
        this.iconUnicodeString = iconUnicodeString;
    }


    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public List<MOVE_PATTERN> getMovePatterns() {
        return movePatterns;
    }

    public void setMovePatterns(List<MOVE_PATTERN> movePatterns) {
        this.movePatterns = movePatterns;
    }
    public int getPlayerID()
    {
        return playerID;
    }

    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    public String getIconUnicodeString()
    {
        return iconUnicodeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {

        if (this == null)
        {
            return "X ";
        }
        else if (this instanceof Pawn)
        {
            return "P ";
        }
        else if (this instanceof Rook)
        {
            return "R ";
        }
        else if (this instanceof Knight)
        {
            return "Kn";
        }
        else if (this instanceof Bishop)
        {
            return "Bi";
        }
        else if (this instanceof Queen)
        {
            return "Q ";
        }
        else
        {
            return "K ";
        }
    }
}
