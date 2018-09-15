import junit.framework.TestCase;

public class PawnTest extends TestCase {

    Pawn p;
    public void setUp() throws Exception {
        super.setUp();
        p = new Pawn (new Coordinate(1,1), 0, MOVE_PATTERN.DOWN);
    }

    public void testGetter()
    {
        Coordinate pawnCoor = p.getPosition();
        assertTrue(pawnCoor.getPosX() == 1);
        assertTrue(pawnCoor.getPosY() == 1);
    }

    public void testSetter()
    {
        Coordinate pawnCoor = p.getPosition();
        pawnCoor.setPosX(2);
        pawnCoor.setPosY(2);
        assertTrue(pawnCoor.getPosX() == 2);
        assertTrue(pawnCoor.getPosY() == 2);
    }

}
