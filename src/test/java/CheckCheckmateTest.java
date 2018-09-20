import junit.framework.TestCase;
import logic.*;

import java.util.List;

public class CheckCheckmateTest extends TestCase {

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

    public void testIsBoardLayoutSafe_basic() {
        assertFalse(gameInstance.isChecked(players.get(0)));

        gameInstance.putPieceOnBoard(4, 4, new Queen (Square.getCoordinate(4, 4),1));
        gameInstance.putPieceOnBoard(4, 1, null);
        assertTrue(gameInstance.isChecked(players.get(0)));
    }

    public void testFoolMate(){
        Piece pawnOne = board.getPieceAtPosition(5,1);
        gameInstance.movePiece(pawnOne, Square.getCoordinate(5,3) );

        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));


        Piece pawnTwo = board.getPieceAtPosition(4,6);
        gameInstance.movePiece(pawnTwo, Square.getCoordinate(4,4) );

        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnThree = board.getPieceAtPosition(6,1);
        gameInstance.movePiece(pawnThree, Square.getCoordinate(6,3) );

        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece queenOne = board.getPieceAtPosition(3, 7);
        gameInstance.movePiece(queenOne, Square.getCoordinate(7,3) );

        assertTrue(gameInstance.isChecked(players.get(0)));

        assertFalse(gameInstance.isChecked(players.get(1)));

        assertTrue(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));
        System.out.println(board);
    }

    public void testScholarMate(){
        Piece pawnOne = board.getPieceAtPosition(4, 1);
        gameInstance.movePiece(pawnOne, Square.getCoordinate(4,3) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnTwo = board.getPieceAtPosition(4,6);
        gameInstance.movePiece(pawnTwo, Square.getCoordinate(4,4) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece bishopOne = board.getPieceAtPosition(5,0);
        gameInstance.movePiece(bishopOne, Square.getCoordinate(2,3));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece knightOne = board.getPieceAtPosition(1,7);
        gameInstance.movePiece(knightOne, Square.getCoordinate(2,5));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece queenOne = board.getPieceAtPosition(3, 0);
        gameInstance.movePiece(queenOne, Square.getCoordinate(5,2));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece bishopTwo = board.getPieceAtPosition(5,7);
        gameInstance.movePiece(bishopTwo, Square.getCoordinate(2,4));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(queenOne, Square.getCoordinate(5,6));

        assertFalse(gameInstance.isChecked(players.get(0)));

        assertTrue(gameInstance.isChecked(players.get(1)));

        assertTrue(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        System.out.println(board);
    }


    public void testSmyslovKarpov()
    {

        Piece pawnOne = board.getPieceAtPosition(4, 1);
        gameInstance.movePiece(pawnOne, Square.getCoordinate(4,3) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnTwo = board.getPieceAtPosition(2,6);
        gameInstance.movePiece(pawnTwo, Square.getCoordinate(2,5) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnThree = board.getPieceAtPosition(3,1);
        gameInstance.movePiece(pawnThree, Square.getCoordinate(3,3) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece pawnFour = board.getPieceAtPosition(3,6);
        gameInstance.movePiece(pawnFour, Square.getCoordinate(3,4) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece knightOne = board.getPieceAtPosition(1,0);
        gameInstance.movePiece(knightOne, Square.getCoordinate(2,2) );
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(pawnFour, pawnOne.getPosition());
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(knightOne, pawnFour.getPosition());
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece knightTwo = board.getPieceAtPosition(1,7);
        gameInstance.movePiece(knightTwo, Square.getCoordinate(3,6));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece queenOne = board.getPieceAtPosition(3,0);
        gameInstance.movePiece(queenOne, Square.getCoordinate(4,1));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        Piece knightThree = board.getPieceAtPosition(6,7);
        gameInstance.movePiece(knightThree, Square.getCoordinate(5,5));
        assertFalse(gameInstance.isCheckmated(players.get(1)));
        assertFalse(gameInstance.isStalemated(players.get(1)));
        assertFalse(gameInstance.isCheckmated(players.get(0)));
        assertFalse(gameInstance.isStalemated(players.get(0)));

        gameInstance.movePiece(knightOne, Square.getCoordinate(3,5));

        assertFalse(gameInstance.isChecked(players.get(0)));

        assertTrue(gameInstance.isChecked(players.get(1)));

        assertTrue(gameInstance.isCheckmated(players.get(1)));
        System.out.println(board);
    }
}