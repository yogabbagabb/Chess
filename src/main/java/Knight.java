import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Coordinate position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(MOVE_PATTERN.L_SHAPE)));
    }
}

