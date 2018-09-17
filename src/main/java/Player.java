import java.util.List;
import java.util.Objects;

/**
 * Player maintains the list of pieces belonging to any player.
 */
public class Player {
    List <Piece> pieces;
    int playerID;
    Piece king;

    public Player(int playerID) {
        this.pieces = null;
        this.playerID = playerID;
        this.king = null;
    }


    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public int getPlayerID() {
        return playerID;
    }

    public Piece getKing() {
        return king;
    }

    public void setKing(Piece king) {
        this.king = king;
    }


    @Override
    public String toString() {
        return "Player{" +
                "pieces=" + pieces +
                ", playerID=" + playerID +
                ", king=" + king +
                '}' +"\n";
    }

    public boolean addPiece(Piece pieceToAdd){ return pieces.add(pieceToAdd);}

    public boolean removePiece(Piece pieceToRemove)
    {
        return pieces.remove(pieceToRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerID == player.playerID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID);
    }
}
