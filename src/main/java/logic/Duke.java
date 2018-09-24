package logic;

import java.util.ArrayList;
import java.util.List;

public class Duke extends Piece {
    public Duke(Square position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(
                        MOVE_PATTERN.UNRESTRICTED_NORTH,MOVE_PATTERN.UNRESTRICTED_SOUTH,
                        MOVE_PATTERN.RANDOM_EAST, MOVE_PATTERN.RANDOM_WEST)), "");
    }
}

