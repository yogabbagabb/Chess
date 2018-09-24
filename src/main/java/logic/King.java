package logic;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Square position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(MOVE_PATTERN.ONE_ANYWHERE)), playerID == 0? WHITE_KING : BLACK_KING);
    }
}

