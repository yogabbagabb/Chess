import junit.framework.TestCase;
import logic.Board;
import logic.Game;
import logic.Player;

import java.util.ArrayList;
import java.util.List;

public class GameTest extends TestCase {

    Game gameInstance;
    Board board;
    List<Player> players;
    boolean whiteBelow;

    public void setUp() throws Exception {
        super.setUp();
        setUpBoard();
    }

    public void setUpBoard() {
        whiteBelow = true;
        gameInstance = new Game (whiteBelow);
        board = gameInstance.getChessBoard();
        players = gameInstance.getPlayers();
    }

    public String stripNewlines(String aString)
    {
        String newLinesGoneString = aString.replace("\n","");
        return newLinesGoneString.replace("\r","");
    }

    public void testGetPlayers() {
        Player pOne = players.get(0);
        Player pTwo = players.get(1);

        String pOneExpectedRep = "logic.Player{pieces=[R , Kn, Bi, Q , K , Bi, Kn, R , P , P , P , P , P , P , P , P ], playerID=0, king=K }";
        String pTwoExpectedRep = "logic.Player{pieces=[R , Kn, Bi, Q , K , Bi, Kn, R , P , P , P , P , P , P , P , P ], playerID=1, king=K }";

        assertEquals(stripNewlines(pOne.toString()), stripNewlines(pOneExpectedRep));
        assertEquals(stripNewlines(pTwo.toString()), stripNewlines(pTwoExpectedRep));
    }

    public void testSetPlayers() {
        Player newPlayer = new Player(2);
        gameInstance.setPlayers(new ArrayList<Player>(List.of(newPlayer)));
        String expectedOutput =  "[logic.Player{pieces=null, playerID=2, king=null}]";
        assertEquals(stripNewlines(gameInstance.getPlayers().toString()), stripNewlines(expectedOutput));
    }

    public void testGetChessBoard() {
        // This is used in other tests.
    }

    public void testMovePiece() {
        // This is used in MovementTest
    }

    public void testPutPieceOnBoard() {
        // This is tested in CheckCheckmateTest
    }
}