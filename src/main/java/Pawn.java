import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Coordinate position, int playerID, MOVE_PATTERN verticalPattern) {
        super(position, playerID, new ArrayList <>
                (List.of(verticalPattern, MOVE_PATTERN.DIAGONAL_ON_ENEMY, MOVE_PATTERN.TWO_INITIALLY)));

    }



}
