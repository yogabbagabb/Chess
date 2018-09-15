import java.util.List;



public abstract class Piece {
    private Coordinate position;
    private int playerID;


    private List <MOVE_PATTERN> movePatterns;

    public Piece(Coordinate position, int playerID, List <MOVE_PATTERN> movePatterns)
    {
        this.position = position;
        this.playerID = playerID;
        this.movePatterns = movePatterns;
    }


    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
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

    public void setPlayerID(int PlayerID)
    {
        this.playerID = playerID;
    }
}
