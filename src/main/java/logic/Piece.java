package logic;

import java.util.List;
import java.util.Objects;


/**
 * logic.Piece is the parent for all actual chess types.
 */
public abstract class Piece {
    private Square position;
    private int playerID;


    private List <MOVE_PATTERN> movePatterns;

    public Piece(Square position, int playerID, List <MOVE_PATTERN> movePatterns)
    {
        this.position = position;
        this.playerID = playerID;
        this.movePatterns = movePatterns;
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
