import junit.framework.TestCase;

import java.util.List;

public class GameTest extends TestCase {

    Game gameInstance;
    Board board;
    List<Player> players;

    public void setUp() throws Exception {
        super.setUp();
        boolean whiteBelow = true;
        gameInstance = new Game (whiteBelow);
        board = gameInstance.getChessBoard();
        players = gameInstance.getPlayers();
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
//        assertEquals((players.get(0)), "Player{pieces=[R , Kn, Bi, Q , K , Bi, Kn, R , P , P , P , P , P , P , P , P ], playerID=0, king=K }");
//        System.out.println(gameInstance.getPlayers().get(1));
    }

}