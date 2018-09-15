import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Coordinate position, int playerID, MOVE_PATTERN verticalPattern) {
        super(position, playerID, new ArrayList <> ());

        List <MOVE_PATTERN> movePatterns = new ArrayList<>();
        movePatterns.add(verticalPattern);
        movePatterns.add(MOVE_PATTERN.DIAGONAL_ON_ENEMY);
        movePatterns.add(MOVE_PATTERN.TWO_INITIALLY);

        setMovePatterns(movePatterns);
    }



}
