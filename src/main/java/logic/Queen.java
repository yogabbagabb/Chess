package logic;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Square position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(MOVE_PATTERN.UNRESTRICTED_EAST,MOVE_PATTERN.UNRESTRICTED_WEST,
                        MOVE_PATTERN.UNRESTRICTED_NORTH,MOVE_PATTERN.UNRESTRICTED_SOUTH,
                        MOVE_PATTERN.UNRESTRICTED_NORTH_EAST, MOVE_PATTERN.UNRESTRICTED_NORTH_WEST,
                        MOVE_PATTERN.UNRESTRICTED_SOUTH_EAST, MOVE_PATTERN.UNRESTRICTED_SOUTH_WEST)), playerID == 0? WHITE_QUEEN : BLACK_QUEEN);
    }
}

