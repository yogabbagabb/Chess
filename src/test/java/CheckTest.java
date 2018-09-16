import junit.framework.TestCase;

import java.util.List;

public class CheckTest extends TestCase {

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

    public void testIsBoardLayoutSafe_basic() {
        assertTrue(gameInstance.isBoardLayoutSafe(board, players.get(0)));

        gameInstance.setPieceAtPosition(board, 4, 4, new Queen (Coordinate.getCoordinate(4, 4),1));
        gameInstance.setPieceAtPosition(board, 4, 1, null);
        System.out.println(board.toString());
        assertFalse(gameInstance.isBoardLayoutSafe(board, players.get(0)));
    }

    public void testFoolMate(){
        Piece pawnOne = board.getPieceAtPosition(5,1);
        gameInstance.movePiece(board, pawnOne, Coordinate.getCoordinate(5,3) );

        Piece pawnTwo = board.getPieceAtPosition(4,6);
        gameInstance.movePiece(board, pawnTwo, Coordinate.getCoordinate(4,4) );

        Piece pawnThree = board.getPieceAtPosition(6,1);
        gameInstance.movePiece(board, pawnThree, Coordinate.getCoordinate(6,3) );

        Piece queenOne = board.getPieceAtPosition(3, 7);
        gameInstance.movePiece(board, queenOne, Coordinate.getCoordinate(7,3) );

        assertFalse(gameInstance.isBoardLayoutSafe(board, players.get(0)));
        assertTrue(gameInstance.isChecked(board, players.get(0)));

        assertTrue(gameInstance.isBoardLayoutSafe(board, players.get(1)));
        assertFalse(gameInstance.isChecked(board, players.get(1)));
    }

    public void testScholarMate(){
        Piece pawnOne = board.getPieceAtPosition(4, 1);
        gameInstance.movePiece(board, pawnOne, Coordinate.getCoordinate(4,3) );

        Piece pawnTwo = board.getPieceAtPosition(4,6);
        gameInstance.movePiece(board, pawnTwo, Coordinate.getCoordinate(4,4) );

        Piece bishopOne = board.getPieceAtPosition(5,0);
        gameInstance.movePiece(board, bishopOne, Coordinate.getCoordinate(2,3));

        Piece knightOne = board.getPieceAtPosition(1,7);
        gameInstance.movePiece(board, knightOne, Coordinate.getCoordinate(2,5));

        Piece queenOne = board.getPieceAtPosition(3, 0);
        gameInstance.movePiece(board, queenOne, Coordinate.getCoordinate(5,2));

        Piece bishopTwo = board.getPieceAtPosition(5,7);
        gameInstance.movePiece(board, bishopTwo, Coordinate.getCoordinate(2,4));

        gameInstance.movePiece(board, queenOne, Coordinate.getCoordinate(5,6));

        System.out.println((board));

        assertTrue(gameInstance.isBoardLayoutSafe(board, players.get(0)));
        assertFalse(gameInstance.isChecked(board, players.get(0)));

        assertFalse(gameInstance.isBoardLayoutSafe(board, players.get(1)));
        assertTrue(gameInstance.isChecked(board, players.get(1)));
    }


    public void testSmyslovKarpov()
    {

        Piece pawnOne = board.getPieceAtPosition(4, 1);
        gameInstance.movePiece(board, pawnOne, Coordinate.getCoordinate(4,3) );

        Piece pawnTwo = board.getPieceAtPosition(2,6);
        gameInstance.movePiece(board, pawnTwo, Coordinate.getCoordinate(2,5) );

        Piece pawnThree = board.getPieceAtPosition(3,1);
        gameInstance.movePiece(board, pawnThree, Coordinate.getCoordinate(3,3) );

        Piece pawnFour = board.getPieceAtPosition(3,6);
        gameInstance.movePiece(board, pawnFour, Coordinate.getCoordinate(3,4) );

        Piece knightOne = board.getPieceAtPosition(1,0);
        gameInstance.movePiece(board, knightOne, Coordinate.getCoordinate(2,2) );

        gameInstance.movePiece(board, pawnFour, pawnOne.getPosition());

        gameInstance.movePiece(board, knightOne, pawnFour.getPosition());

        Piece knightTwo = board.getPieceAtPosition(1,7);
        gameInstance.movePiece(board, knightTwo, Coordinate.getCoordinate(3,6));

        Piece queenOne = board.getPieceAtPosition(3,0);
        gameInstance.movePiece(board, queenOne, Coordinate.getCoordinate(4,1));

        Piece knightThree = board.getPieceAtPosition(6,7);
        gameInstance.movePiece(board, knightThree, Coordinate.getCoordinate(5,5));

        gameInstance.movePiece(board, knightOne, Coordinate.getCoordinate(3,5));

        System.out.println(board);
        assertTrue(gameInstance.isBoardLayoutSafe(board, players.get(0)));
        assertFalse(gameInstance.isChecked(board, players.get(0)));

        assertFalse(gameInstance.isBoardLayoutSafe(board, players.get(1)));
        assertTrue(gameInstance.isChecked(board, players.get(1)));
    }
}