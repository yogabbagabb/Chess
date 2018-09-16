import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class PieceInterfacesTest extends TestCase {

    Pawn p;
    public void setUp() throws Exception {
        super.setUp();
        boolean north = true;
        p = new Pawn (new Coordinate(1,1), 0, !north);
    }

    public void testGetter()
    {
        Coordinate pawnCoor = p.getPosition();
        assertTrue(pawnCoor.getPosX() == 1);
        assertTrue(pawnCoor.getPosY() == 1);

        int ID = p.getPlayerID();
        assertEquals(ID, 0);
        
        List<MOVE_PATTERN> patterns = p.getMovePatterns();
        System.out.println(patterns.toString());
        assertTrue(patterns.toString().equals("[SOUTH_NO_KILL, DIAGONAL_ON_ENEMY_SOUTH, TWO_INITIALLY_SOUTH]"));
    }

    public void testSetter()
    {
        Coordinate pawnCoor = p.getPosition();
        pawnCoor.setPosX(2);
        pawnCoor.setPosY(2);
        assertTrue(pawnCoor.getPosX() == 2);
        assertTrue(pawnCoor.getPosY() == 2);

        p.setPlayerID(3);
        assertTrue(p.getPlayerID() == 3);

        p.setMovePatterns(new ArrayList<>(List.of(MOVE_PATTERN.SOUTH_NO_KILL)));
        List<MOVE_PATTERN> patterns = p.getMovePatterns();
        assertTrue(patterns.toString().equals("[SOUTH_NO_KILL]"));

        // Add another line for testing setPosition
        p.setPosition(new Coordinate (4,4));
        pawnCoor = p.getPosition();
        assertTrue(pawnCoor.getPosX() == 4);
        assertTrue(pawnCoor.getPosY() == 4);

    }

}
