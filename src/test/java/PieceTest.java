import junit.framework.TestCase;

public class PieceTest extends TestCase {

    Piece p;
    Piece firstPiece;
    Piece secondPiece;

    public void setUp() throws Exception {
        super.setUp();
        p = new Pawn(new Coordinate(0, 0), 0, MOVE_PATTERN.SOUTH);
        firstPiece = new Pawn(new Coordinate(0, 0), 0, MOVE_PATTERN.NORTH);
        secondPiece = new Pawn(new Coordinate(0, 0), 0, MOVE_PATTERN.SOUTH);
    }

    public void testSet()
    {
        p.setPosition(new Coordinate(3,3));
        assertEquals(p.getPosition().getPosX(), new Coordinate(3,3).getPosX());
        assertEquals(p.getPosition().getPosY(), new Coordinate(3,3).getPosY());
    }

    public void testEquals()
    {
        assertTrue(firstPiece.equals(secondPiece));
    }

    public void testEqualHashCode()
    {
        assertEquals(firstPiece.hashCode(),secondPiece.hashCode());
    }
}