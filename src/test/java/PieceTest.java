import junit.framework.TestCase;
import logic.*;

public class PieceTest extends TestCase {

    Piece p;
    Piece firstPiece;
    Piece secondPiece;
    Piece thirdPiece;
    Piece fourthPiece;

    public void setUp() throws Exception {
        super.setUp();
        boolean north = true;
        p = new Pawn(new Square(0, 0), 0, !north);
        firstPiece = new Pawn(new Square(0, 0), 0, north);
        secondPiece = new Pawn(new Square(0, 0), 0, !north);
        thirdPiece = new Rook(new Square(2,4), 1);
        fourthPiece = new King(new Square(5,5), 1);
    }

    public void testSet()
    {
        p.setPosition(new Square(3,3));
        assertEquals(p.getPosition().getPosX(), new Square(3,3).getPosX());
        assertEquals(p.getPosition().getPosY(), new Square(3,3).getPosY());
    }

    public void testEquals()
    {
        assertTrue(firstPiece.equals(secondPiece));
    }

    public void testEqualHashCode()
    {
        assertEquals(firstPiece.hashCode(),secondPiece.hashCode());
    }

    public void testToString() { assertEquals(thirdPiece.toString(),"R ");assertEquals(fourthPiece.toString(),"K ");}
}