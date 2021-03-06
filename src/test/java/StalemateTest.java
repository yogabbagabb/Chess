import junit.framework.TestCase;
import logic.*;

import java.util.List;

public class StalemateTest extends TestCase {
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
        gameInstance = new Game(whiteBelow);
        board = gameInstance.getChessBoard();
        players = gameInstance.getPlayers();
    }

    public void testStalemateSimple()
    {
        Piece pawnOneWhite = board.getPieceAtPosition(4,1);
        gameInstance.movePiece(pawnOneWhite, Square.getCoordinate(4,2) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnOneBlack = board.getPieceAtPosition(0,6);
        gameInstance.movePiece(pawnOneBlack, Square.getCoordinate(0,4) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece whiteQueen = board.getPieceAtPosition(3,0);
        gameInstance.movePiece(whiteQueen, Square.getCoordinate(7,4));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece rookOneBlack = board.getPieceAtPosition(0,7);
        gameInstance.movePiece(rookOneBlack, Square.getCoordinate(0,5));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, pawnOneBlack.getPosition());
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnTwoBlack = board.getPieceAtPosition(7,6);
        gameInstance.movePiece(pawnTwoBlack, Square.getCoordinate(7,4) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnTwoWhite = board.getPieceAtPosition(7,1);
        gameInstance.movePiece(pawnTwoWhite, Square.getCoordinate(7,3) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(rookOneBlack, Square.getCoordinate(7,5));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, Square.getCoordinate(2,6));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnThreeBlack = board.getPieceAtPosition(5,6);
        gameInstance.movePiece(pawnThreeBlack, Square.getCoordinate(5,5));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, Square.getCoordinate(3,6));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        assertTrue(gameInstance.isChecked(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece blackKing = board.getPieceAtPosition(4,7);
        gameInstance.movePiece(blackKing, Square.getCoordinate(5,6));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, Square.getCoordinate(1,6));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece blackQueen = board.getPieceAtPosition(3,7);
        gameInstance.movePiece(blackQueen, Square.getCoordinate(3,2));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, Square.getCoordinate(1,7));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(blackQueen, Square.getCoordinate(7,6));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, Square.getCoordinate(2,7));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(blackKing, Square.getCoordinate(6,5));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(whiteQueen, Square.getCoordinate(4,5));

        assertFalse(gameInstance.isChecked(players.get(1)));
        assertTrue(gameInstance.isStalemated(players.get(1)));
        System.out.println(board);


    }
}
