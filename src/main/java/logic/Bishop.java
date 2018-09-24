package logic;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Square position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of( MOVE_PATTERN.UNRESTRICTED_NORTH_EAST, MOVE_PATTERN.UNRESTRICTED_NORTH_WEST,
                        MOVE_PATTERN.UNRESTRICTED_SOUTH_EAST, MOVE_PATTERN.UNRESTRICTED_SOUTH_WEST)), playerID == 0? WHITE_BISHOP :
                BLACK_BISHOP);
    }
}

