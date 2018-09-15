import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Coordinate position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of( MOVE_PATTERN.UNRESTRICTED_NORTH_EAST, MOVE_PATTERN.UNRESTRICTED_NORTH_WEST,
                        MOVE_PATTERN.UNRESTRICTED_SOUTH_EAST, MOVE_PATTERN.UNRESTRICTED_SOUTH_WEST)));
    }
}

