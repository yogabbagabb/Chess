import java.util.List;

public class Player {
    List <Piece> pieces;
    int playerID;
    Piece king;

    public Player(List<Piece> pieces, int playerID, Piece king) {
        this.pieces = pieces;
        this.playerID = playerID;
        this.king = king;
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

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Piece getKing() {
        return king;
    }

    public void setKing(Piece king) {
        this.king = king;
    }


}