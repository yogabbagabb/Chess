import junit.framework.TestCase;

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
        gameInstance = new Game (whiteBelow);
        board = gameInstance.getChessBoard();
        players = gameInstance.getPlayers();
    }

    public void testStalemateSimple()
    {
        Piece pawnOneWhite = board.getPieceAtPosition(4,1);
        gameInstance.movePiece(board, pawnOneWhite, Coordinate.getCoordinate(4,2) );

        Piece pawnOneBlack = board.getPieceAtPosition(0,6);
        gameInstance.movePiece(board, pawnOneBlack, Coordinate.getCoordinate(0,4) );

        Piece whiteQueen = board.getPieceAtPosition(3,0);
        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(7,4));

        Piece rookOneBlack = board.getPieceAtPosition(0,7);
        gameInstance.movePiece(board, rookOneBlack, Coordinate.getCoordinate(0,5));

        gameInstance.movePiece(board, whiteQueen, pawnOneBlack.getPosition());

        Piece pawnTwoBlack = board.getPieceAtPosition(7,6);
        gameInstance.movePiece(board, pawnTwoBlack, Coordinate.getCoordinate(7,4) );

        Piece pawnTwoWhite = board.getPieceAtPosition(7,1);
        gameInstance.movePiece(board, pawnTwoWhite, Coordinate.getCoordinate(7,3) );

        gameInstance.movePiece(board, rookOneBlack, Coordinate.getCoordinate(7,5));

        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(2,6));

        Piece pawnThreeBlack = board.getPieceAtPosition(5,6);
        gameInstance.movePiece(board, pawnThreeBlack, Coordinate.getCoordinate(5,5));

        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(3,6));

        assertTrue(gameInstance.isChecked(board, players.get(1)));

        Piece blackKing = board.getPieceAtPosition(4,7);
        gameInstance.movePiece(board, blackKing, Coordinate.getCoordinate(5,6));

        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(1,6));

        Piece blackQueen = board.getPieceAtPosition(3,7);
        gameInstance.movePiece(board, blackQueen, Coordinate.getCoordinate(3,2));

        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(1,7));

        gameInstance.movePiece(board, blackQueen, Coordinate.getCoordinate(7,6));

        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(2,7));

        gameInstance.movePiece(board, blackKing, Coordinate.getCoordinate(6,5));

        gameInstance.movePiece(board, whiteQueen, Coordinate.getCoordinate(4,5));

        assertFalse(gameInstance.isChecked(board, players.get(1)));
        assertTrue(gameInstance.isStalemated(players.get(1), board));


    }
}
