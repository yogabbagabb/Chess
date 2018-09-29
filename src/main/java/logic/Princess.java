package logic;

import java.util.ArrayList;
import java.util.List;

public class Princess extends Piece {
    public Princess(Square position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(
                        MOVE_PATTERN.UNRESTRICTED_NORTH_EAST,MOVE_PATTERN.UNRESTRICTED_SOUTH_EAST,
                        MOVE_PATTERN.UNRESTRICTED_NORTH_WEST,MOVE_PATTERN.UNRESTRICTED_SOUTH_WEST,
                        MOVE_PATTERN.RANDOM_EAST, MOVE_PATTERN.RANDOM_WEST)), playerID==0? WHITE_PRINCESS : BLACK_PRINCESS);
    }
}

