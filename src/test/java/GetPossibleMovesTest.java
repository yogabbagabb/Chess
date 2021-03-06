import junit.framework.TestCase;
import logic.*;

import java.util.List;
import java.util.Set;

public class GetPossibleMovesTest extends TestCase {

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


    public void testGetPieceMovesPawnNorth() {
        Piece aPawn = board.getPieceAtPosition(1,1);
        Set<Square> possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(0));
        String expectedOutput = stripNewlines("[logic.Square{posX=1, posY=2}, logic.Square{posX=1, posY=3}]");
        String actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        boolean north = false;
        board.setPieceAtPosition(1,2, new Pawn (Square.getCoordinate(1,2),1,north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(0));
        expectedOutput = stripNewlines("[]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(1,2, null);
        board.setPieceAtPosition(1,3, new Pawn (Square.getCoordinate(1,3),1,north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=1, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(2,2, new Pawn (Square.getCoordinate(2,2),1,north));
        board.setPieceAtPosition(0,2, new Pawn (Square.getCoordinate(0,2),1,north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=1, posY=2}, logic.Square{posX=2, posY=2}, logic.Square{posX=0, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(2,2, new Pawn (Square.getCoordinate(2,2),0, !north));
        board.setPieceAtPosition(0,2, new Pawn (Square.getCoordinate(0,2),0, !north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=1, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

    }

        public void testGetPieceMovesPawnSouth() {
        Piece aPawn = board.getPieceAtPosition(1,6);
        Set<Square> possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(1));
        String expectedOutput = stripNewlines("[logic.Square{posX=1, posY=5}, logic.Square{posX=1, posY=4}]");
        String actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        boolean north = true;
        board.setPieceAtPosition(1,5, new Pawn (Square.getCoordinate(1,2),0,north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(1));
        expectedOutput = stripNewlines("[]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(1,5, null);
        board.setPieceAtPosition(1,4, new Pawn (Square.getCoordinate(1,4),0,north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(1));
        expectedOutput = stripNewlines("[logic.Square{posX=1, posY=5}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(2,5, new Pawn (Square.getCoordinate(2,5),0,north));
        board.setPieceAtPosition(0,5, new Pawn (Square.getCoordinate(0,5),0,north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(1));
        expectedOutput = stripNewlines("[logic.Square{posX=1, posY=5}, logic.Square{posX=0, posY=5}, logic.Square{posX=2, posY=5}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(2,5, new Pawn (Square.getCoordinate(2,5),1, !north));
        board.setPieceAtPosition(0,5, new Pawn (Square.getCoordinate(0,5),1, !north));
        possibleMoves = gameInstance.getSafePieceMoves(aPawn, players.get(1));
        expectedOutput = stripNewlines("[logic.Square{posX=1, posY=5}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

    }


    public void testCompassDirections()
    {

        board.setPieceAtPosition(3,3, new Rook(Square.getCoordinate(3,3), 0));
        Piece aPiece = board.getPieceAtPosition(3,3);
        Set<Square> possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        String expectedOutput = stripNewlines("[logic.Square{posX=4, posY=3}, logic.Square{posX=5, posY=3}, logic.Square{posX=6, posY=3}, logic.Square{posX=7, posY=3}, logic.Square{posX=2, posY=3}, logic.Square{posX=1, posY=3}, logic.Square{posX=0, posY=3}, logic.Square{posX=3, posY=4}, logic.Square{posX=3, posY=5}, logic.Square{posX=3, posY=6}, logic.Square{posX=3, posY=2}]");
        String actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(3,4, new Rook(Square.getCoordinate(3,4), 1));
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=3}, logic.Square{posX=5, posY=3}, logic.Square{posX=6, posY=3}, logic.Square{posX=7, posY=3}, logic.Square{posX=2, posY=3}, logic.Square{posX=1, posY=3}, logic.Square{posX=0, posY=3}, logic.Square{posX=3, posY=4}, logic.Square{posX=3, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(3,4, new Rook(Square.getCoordinate(3,4), 0));
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=3}, logic.Square{posX=5, posY=3}, logic.Square{posX=6, posY=3}, logic.Square{posX=7, posY=3}, logic.Square{posX=2, posY=3}, logic.Square{posX=1, posY=3}, logic.Square{posX=0, posY=3}, logic.Square{posX=3, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(3,2, new Rook(Square.getCoordinate(3,2), 0));
        board.setPieceAtPosition(2,3, new Rook(Square.getCoordinate(2,3), 0));
        board.setPieceAtPosition(4,3, new Rook(Square.getCoordinate(5,3), 0));
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(3,2, new Rook(Square.getCoordinate(3,2), 1));
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=3, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);


    }

    public void testDiagonalDirections() {

        board.setPieceAtPosition(3,3, new Bishop(Square.getCoordinate(3,3), 0));
        Piece aPiece = board.getPieceAtPosition(3,3);
        Set<Square> possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        String expectedOutput = stripNewlines("[logic.Square{posX=4, posY=4}, logic.Square{posX=5, posY=5}, logic.Square{posX=6, posY=6}, logic.Square{posX=2, posY=4}, logic.Square{posX=1, posY=5}, logic.Square{posX=0, posY=6}, logic.Square{posX=4, posY=2}, logic.Square{posX=2, posY=2}]");
        String actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(4,4, new Rook(Square.getCoordinate(4,4), 1));
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=4}, logic.Square{posX=2, posY=4}, logic.Square{posX=1, posY=5}, logic.Square{posX=0, posY=6}, logic.Square{posX=4, posY=2}, logic.Square{posX=2, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(2,2, new Rook(Square.getCoordinate(2,2), 0));
        board.setPieceAtPosition(2,4, new Rook(Square.getCoordinate(4,4), 0));
        board.setPieceAtPosition(4,2, new Rook(Square.getCoordinate(4,4),0));
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=4}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(4, 4, null);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=4}, logic.Square{posX=5, posY=5}, logic.Square{posX=6, posY=6}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);
    }

    public void testKnightMovement()
    {

        board.setPieceAtPosition(3,3, new Knight(Square.getCoordinate(3,3), 1));
        Piece aPiece = board.getPieceAtPosition(3,3);
        Set<Square> possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        String expectedOutput = stripNewlines("[logic.Square{posX=1, posY=4}, logic.Square{posX=2, posY=5}, logic.Square{posX=4, posY=5}, logic.Square{posX=5, posY=4}, logic.Square{posX=5, posY=2}, logic.Square{posX=4, posY=1}, logic.Square{posX=2, posY=1}, logic.Square{posX=1, posY=2}]");
        String actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(1,4, new Knight(Square.getCoordinate(1,4), 1));
        expectedOutput = stripNewlines("[logic.Square{posX=2, posY=5}, logic.Square{posX=4, posY=5}, logic.Square{posX=5, posY=4}, logic.Square{posX=5, posY=2}, logic.Square{posX=4, posY=1}, logic.Square{posX=2, posY=1}, logic.Square{posX=1, posY=2}]");
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(4,5, new Knight(Square.getCoordinate(4, 5), 0));
        expectedOutput = stripNewlines("[logic.Square{posX=2, posY=5}, logic.Square{posX=4, posY=5}, logic.Square{posX=5, posY=4}, logic.Square{posX=5, posY=2}, logic.Square{posX=4, posY=1}, logic.Square{posX=2, posY=1}, logic.Square{posX=1, posY=2}]");
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(5,2, new Knight(Square.getCoordinate(5, 2), 1));
        expectedOutput = stripNewlines("[logic.Square{posX=2, posY=5}, logic.Square{posX=4, posY=5}, logic.Square{posX=5, posY=4}, logic.Square{posX=4, posY=1}, logic.Square{posX=2, posY=1}, logic.Square{posX=1, posY=2}]");
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(4,3, new Knight(Square.getCoordinate(4, 3), 1));
        expectedOutput = stripNewlines("[logic.Square{posX=2, posY=5}, logic.Square{posX=4, posY=5}, logic.Square{posX=5, posY=4}, logic.Square{posX=4, posY=1}, logic.Square{posX=2, posY=1}, logic.Square{posX=1, posY=2}]");
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(7, 7, new Knight(Square.getCoordinate(7, 7), 1));
        aPiece = board.getPieceAtPosition(7, 7);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        expectedOutput = stripNewlines("[logic.Square{posX=6, posY=5}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);
    }

    public void testKingMovement()
    {
        board.setPieceAtPosition(3,3, new King(Square.getCoordinate(3,3), 1));
        Piece aPiece = board.getPieceAtPosition(3,3);
        Set<Square> possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        String expectedOutput = stripNewlines("[logic.Square{posX=3, posY=4}, logic.Square{posX=4, posY=4}, logic.Square{posX=4, posY=3}, logic.Square{posX=4, posY=2}, logic.Square{posX=3, posY=2}, logic.Square{posX=2, posY=2}, logic.Square{posX=2, posY=3}, logic.Square{posX=2, posY=4}]");
        String actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(3, 4, new King(Square.getCoordinate(3,4), 1));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=4}, logic.Square{posX=4, posY=3}, logic.Square{posX=4, posY=2}, logic.Square{posX=3, posY=2}, logic.Square{posX=2, posY=2}, logic.Square{posX=2, posY=3}, logic.Square{posX=2, posY=4}]");
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(4, 4, new King(Square.getCoordinate(4,4), 0));
        expectedOutput = stripNewlines("[logic.Square{posX=4, posY=4}, logic.Square{posX=4, posY=3}, logic.Square{posX=4, posY=2}, logic.Square{posX=3, posY=2}, logic.Square{posX=2, posY=2}, logic.Square{posX=2, posY=3}, logic.Square{posX=2, posY=4}]");
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        board.setPieceAtPosition(7, 7, new King(Square.getCoordinate(7, 7), 0));
        aPiece = board.getPieceAtPosition(7, 7);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=7, posY=6}, logic.Square{posX=6, posY=6}, logic.Square{posX=6, posY=7}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);
    }


}