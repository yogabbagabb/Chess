package logic;

import java.util.ArrayList;
import java.util.List;


public class Pawn extends Piece {


    public Pawn(Square position, int playerID, boolean north) {
        super(position, playerID, new ArrayList <> (), playerID == 0 ? WHITE_PAWN : BLACK_PAWN);
        if (north)
        {
            this.setMovePatterns(List.of(MOVE_PATTERN.NORTH_NO_KILL, MOVE_PATTERN.DIAGONAL_ON_ENEMY_NORTH, MOVE_PATTERN.TWO_INITIALLY_NORTH));
        }
        else
        {
            this.setMovePatterns(List.of(MOVE_PATTERN.SOUTH_NO_KILL, MOVE_PATTERN.DIAGONAL_ON_ENEMY_SOUTH, MOVE_PATTERN.TWO_INITIALLY_SOUTH));
        }


    }



}
