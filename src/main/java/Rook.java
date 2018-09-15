import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Coordinate position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(MOVE_PATTERN.UNRESTRICTED_EAST,MOVE_PATTERN.UNRESTRICTED_WEST,
                        MOVE_PATTERN.UNRESTRICTED_NORTH,MOVE_PATTERN.UNRESTRICTED_SOUTH)));
    }
}
