import junit.framework.TestCase;
import logic.*;

import java.util.List;

public class MovementTest extends TestCase {

    private Game gameInstance;
    private Board board;
    private List<Player> players;

    public void setUp() throws Exception {
        super.setUp();
        boolean whiteBelow = true;
        gameInstance = new Game(whiteBelow);
        board = gameInstance.getChessBoard();
        players = gameInstance.getPlayers();
    }

    public String stripNewlines(String aString)
    {
        String newLinesGoneString = aString.replace("\n","");
        return newLinesGoneString.replace("\r","");
    }

    public void testInitialization()
    {
        String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  P  P  P  P  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "P  P  P  P  P  P  P  P  \n" +
                        "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        String expectedOutput = "logic.Player{pieces=[R , Kn, Bi, Q , K , Bi, Kn, R , P , P , P , P , P , P , P , P ], playerID=0, king=K }";
        assertEquals(stripNewlines(expectedOutput), stripNewlines(players.get(0).toString()));
    }

    public void testMovementDeletion()
    {
        Piece aPawn = board.getPieceAtPosition(1,1);
        gameInstance.movePiece(aPawn, new Square(1,3));
        Piece shouldBeNull = board.getPieceAtPosition(1,1);
        assertNull(shouldBeNull);

        String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  P  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        //System.out.println(board.toString());

        gameInstance.movePiece(aPawn, new Square(3,3));
        shouldBeNull = board.getPieceAtPosition(1,3);
        assertNull(shouldBeNull);
        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  P  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        //System.out.println(board.toString());

        gameInstance.movePiece(aPawn, new Square(3,0));
        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "R  Kn Bi P  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        //System.out.println(board.toString());

        gameInstance.movePiece(aPawn, new Square(4,0));
        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "R  Kn Bi X  P  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        //System.out.println(board.toString());

        gameInstance.movePiece(aPawn, new Square(5,0));
        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "R  Kn Bi X  X  P  Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        //System.out.println(board.toString());

        shouldBeNull = board.getPieceAtPosition(4, 0);
        assertEquals(shouldBeNull, null);

        Piece shouldNotBeNull = board.getPieceAtPosition(5, 0);
        assertNotNull(shouldNotBeNull);

        gameInstance.movePiece(aPawn, new Square(5,7));
        expectedVisual = "R  Kn Bi Q  K  P  Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "R  Kn Bi X  X  X  Kn R  \n";

        assertEquals(board.toString(), expectedVisual);
        expectedVisual = "logic.Player{pieces=[R , Kn, Bi, Q , K , Kn, R , P , P , P , P , P , P , P , P ], playerID=1, king=K }\n";

        Player playerOne = players.get(1);
        String actualString = playerOne.toString().replace("\n","").replace("\r","");
        String expectedVisual_noCRLF = expectedVisual.replace("\n","").replace("\r","");
        assertEquals(expectedVisual_noCRLF, actualString);


    }

//    public void testSuicide()
//    {
//        logic.Player bottomPlayer = players.get(0);
//        boolean isSuicide = gameInstance.isSuicidal(bottomPlayer, logic.Square.getCoordinate(0,0));
//        assertTrue(isSuicide);
//
//        isSuicide = gameInstance.isSuicidal(bottomPlayer, logic.Square.getCoordinate(0,3));
//        assertFalse(isSuicide);
//
//        logic.Piece aPawn = board.getPieceAtPosition(1,1);
//        gameInstance.movePiece(aPawn, new logic.Square(1,3));
//        isSuicide = gameInstance.isSuicidal(bottomPlayer, logic.Square.getCoordinate(1,3));
//        assertTrue(isSuicide);
//
//        isSuicide = gameInstance.isSuicidal(bottomPlayer, logic.Square.getCoordinate(1,1));
//        assertFalse(isSuicide);
//    }
//
//    public void testOccupation()
//    {
//        logic.Player bottomPlayer = players.get(0);
//        assertTrue(gameInstance.isEnemyOccupied(bottomPlayer, 0,7));
//        assertFalse(gameInstance.isEnemyOccupied(bottomPlayer, 0,1));
//        assertFalse(gameInstance.isEnemyOccupied(bottomPlayer, 0,3));
//        assertTrue(gameInstance.isUnoccupied(0,3));
//        assertFalse(gameInstance.isUnoccupied(0,7));
//    }




}