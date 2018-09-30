import junit.framework.TestCase;
import logic.*;

import java.util.List;

public class UndoTest extends TestCase{

    private Game game;
    private Board board;
    private List<Player> players;

    public String stripNewlines(String aString)
    {
        String newLinesGoneString = aString.replace("\n","");
        return newLinesGoneString.replace("\r","");
    }

    public void setUp() throws Exception {
        super.setUp();
        boolean whiteBelow = true;
        boolean newPieces = false;
        game = new Game(whiteBelow, newPieces);
        board = game.getChessBoard();
        players = game.getPlayers();
    }

    // Test a simple move forward and then an undo at the start of the game
    public void testMovingStress()
    {
       Piece pawnOne = board.getPieceAtPosition(0,1);
       game.movePieceInGame(pawnOne, Square.getCoordinate(0,2));

       game.undoMove();
       String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertEquals(0, game.getCurrentPlayerID());
        assertEquals(0, game.getTurnParity());
        assertEquals(null, game.getCurrentPiece());


    }

    // Test that if we repeatedly both make a single move and then undo it, then the game state should not change
    public void testRepeatedMovingStress()
    {
        Piece pawnOne;
        int trials = 10;
        for(int i = 0; i < trials; ++i)
        {
            pawnOne = board.getPieceAtPosition(0,1);
            game.movePieceInGame(pawnOne, Square.getCoordinate(0,2));

            game.undoMove();
            String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                    "P  P  P  P  P  P  P  P  \n" +
                    "X  X  X  X  X  X  X  X  \n" +
                    "X  X  X  X  X  X  X  X  \n" +
                    "X  X  X  X  X  X  X  X  \n" +
                    "X  X  X  X  X  X  X  X  \n" +
                    "P  P  P  P  P  P  P  P  \n" +
                    "R  Kn Bi Q  K  Bi Kn R  \n";
            assertEquals(expectedVisual, board.toString());
        }
    }

    // Test that if we undo at a point when there is nothing to undo, then the undo is functional
    public void testDoNothing()
    {
        game.undoMove();
        String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual, board.toString());

    }

    public void testScholarMate(){
        String expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        Piece pawnOne = board.getPieceAtPosition(4, 1);
        game.movePieceInGame(pawnOne, Square.getCoordinate(4,3) );
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
        expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  P  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";

        Piece pawnTwo = board.getPieceAtPosition(4,6);
        game.movePieceInGame(pawnTwo, Square.getCoordinate(4,4) );
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
        expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  P  X  X  X  \n" +
                "X  X  X  X  P  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";

        Piece bishopOne = board.getPieceAtPosition(5,0);
        game.movePieceInGame(bishopOne, Square.getCoordinate(2,3));
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
        expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  P  X  X  X  \n" +
                "X  X  Bi X  P  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "R  Kn Bi Q  K  X  Kn R  \n";

        Piece knightOne = board.getPieceAtPosition(1,7);
        game.movePieceInGame(knightOne, Square.getCoordinate(2,5));
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
        expectedVisual =
                "R  X  Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "X  X  Kn X  X  X  X  X  \n" +
                "X  X  X  X  P  X  X  X  \n" +
                "X  X  Bi X  P  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "R  Kn Bi Q  K  X  Kn R  \n";

        Piece queenOne = board.getPieceAtPosition(3, 0);
        game.movePieceInGame(queenOne, Square.getCoordinate(5,2));
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
        expectedVisual =
                "R  X  Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "X  X  Kn X  X  X  X  X  \n" +
                "X  X  X  X  P  X  X  X  \n" +
                "X  X  Bi X  P  X  X  X  \n" +
                "X  X  X  X  X  Q  X  X  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "R  Kn Bi X  K  X  Kn R  \n";

        Piece bishopTwo = board.getPieceAtPosition(5,7);
        game.movePieceInGame(bishopTwo, Square.getCoordinate(2,4));
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
        expectedVisual =
                "R  X  Bi Q  K  X  Kn R  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "X  X  Kn X  X  X  X  X  \n" +
                "X  X  Bi X  P  X  X  X  \n" +
                "X  X  Bi X  P  X  X  X  \n" +
                "X  X  X  X  X  Q  X  X  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "R  Kn Bi X  K  X  Kn R  \n";

        game.movePieceInGame(queenOne, Square.getCoordinate(5,6));

        assertFalse(game.isChecked(players.get(0)));
        assertTrue(game.isChecked(players.get(1)));
        assertTrue(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));

        game.undoMove();
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));

        game.undoMove();
        expectedVisual =
                "R  X  Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "X  X  Kn X  X  X  X  X  \n" +
                        "X  X  X  X  P  X  X  X  \n" +
                        "X  X  Bi X  P  X  X  X  \n" +
                        "X  X  X  X  X  Q  X  X  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "R  Kn Bi X  K  X  Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));

        game.undoMove();
        expectedVisual =
                "R  X  Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "X  X  Kn X  X  X  X  X  \n" +
                        "X  X  X  X  P  X  X  X  \n" +
                        "X  X  Bi X  P  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "R  Kn Bi Q  K  X  Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));

        game.undoMove();
        expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  P  X  X  X  \n" +
                        "X  X  Bi X  P  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "R  Kn Bi Q  K  X  Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));

        game.undoMove();
        expectedVisual =
                        "R  Kn Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  P  X  X  X  \n" +
                        "X  X  X  X  P  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));

        game.undoMove();
        expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  P  P  P  P  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  P  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "P  P  P  P  X  P  P  P  \n" +
                        "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));

        game.undoMove();
        expectedVisual =
                "R  Kn Bi Q  K  Bi Kn R  \n" +
                        "P  P  P  P  P  P  P  P  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "P  P  P  P  P  P  P  P  \n" +
                        "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual, board.toString());
        assertFalse(game.isCheckmated(players.get(1)));
        assertFalse(game.isStalemated(players.get(1)));
        assertFalse(game.isCheckmated(players.get(0)));
        assertFalse(game.isStalemated(players.get(0)));
    }

    // Test that if we put back a piece on the board through the use of undo, then the piece's state in player is what it was

    // Test a long sequence of moves


}
