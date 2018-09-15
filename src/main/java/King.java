import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Coordinate position, int playerID) {
        super(position, playerID, new ArrayList<>
                (List.of(MOVE_PATTERN.ONE_ANYWHERE)));
    }
}

