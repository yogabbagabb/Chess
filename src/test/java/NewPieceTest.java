import junit.framework.TestCase;
import logic.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NewPieceTest extends TestCase {

    private Game gameInstance;
    private Board board;
    private List<Player> players;

    public void setUp() throws Exception {
        super.setUp();
        boolean whiteBelow = true;
        boolean newPieces = true;
        gameInstance = new Game(whiteBelow, newPieces);
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
                        "D  X  X  X  X  X  X  Pr \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "X  X  X  X  X  X  X  X  \n" +
                        "D  X  X  X  X  X  X  Pr \n" +
                        "P  P  P  P  P  P  P  P  \n" +
                        "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
        String expectedOutput = "logic.Player{pieces=[R , Kn, Bi, Q , K , Bi, Kn, R , P , P , P , P , P , P , P , P , D , Pr], playerID=0, king=K }";
        assertEquals(stripNewlines(expectedOutput), stripNewlines(players.get(0).toString()));

       expectedOutput = "logic.Player{pieces=[R , Kn, Bi, Q , K , Bi, Kn, R , P , P , P , P , P , P , P , P , D , Pr], playerID=1, king=K }";
        assertEquals(stripNewlines(expectedOutput), stripNewlines(players.get(1).toString()));
    }

    public void testPossibleSafeMoves()
    {
        Set<Square> possibleMoves;
        String expectedOutput;
        String actualOutput;
        Piece aPiece = board.getPieceAtPosition(0,2);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=0, posY=3}, logic.Square{posX=0, posY=4}, logic.Square{posX=0, posY=5}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        aPiece = board.getPieceAtPosition(7,2);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=6, posY=3}, logic.Square{posX=5, posY=4}, logic.Square{posX=4, posY=5}, logic.Square{posX=3, posY=6}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        aPiece = board.getPieceAtPosition(0,5);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        expectedOutput = stripNewlines("[logic.Square{posX=0, posY=4}, logic.Square{posX=0, posY=3}, logic.Square{posX=0, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        aPiece = board.getPieceAtPosition(7,5);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(1));
        expectedOutput = stripNewlines("[logic.Square{posX=6, posY=4}, logic.Square{posX=5, posY=3}, logic.Square{posX=4, posY=2}, logic.Square{posX=3, posY=1}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSpecialMoveDuke()
    {
        Set<Square> possibleMoves;
        String expectedOutput;
        String actualOutput;
        Piece aPiece = board.getPieceAtPosition(0,2);
        gameInstance.movePiece(aPiece, Square.getCoordinate(0,4));
        aPiece = board.getPieceAtPosition(1,6);
        gameInstance.movePiece(aPiece, Square.getCoordinate(1,4));
        aPiece = board.getPieceAtPosition(0,4);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=0, posY=5}, logic.Square{posX=0, posY=3}, logic.Square{posX=0, posY=2}, logic.Square{posX=1, posY=4}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  X  P  P  P  P  P  P  \n" +
                "D  X  X  X  X  X  X  Pr \n" +
                "D  P  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  Pr \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);

        aPiece = board.getPieceAtPosition(2,1);
        gameInstance.movePiece(aPiece, Square.getCoordinate(2,3));
        aPiece = board.getPieceAtPosition(2, 6);
        gameInstance.movePiece(aPiece, Square.getCoordinate(2, 4));

        aPiece = board.getPieceAtPosition(0,4);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=0, posY=5}, logic.Square{posX=0, posY=3}, logic.Square{posX=0, posY=2}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);
        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  X  X  P  P  P  P  P  \n" +
                "D  X  X  X  X  X  X  Pr \n" +
                "D  P  P  X  X  X  X  X  \n" +
                "X  X  P  X  X  X  X  X  \n" +
                "X  X  X  X  X  X  X  Pr \n" +
                "P  P  X  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(board.toString(), expectedVisual);
    }

    public void testSpecialMovePrincess()
    {
        Set<Square> possibleMoves;
        String expectedOutput;
        String actualOutput;
        Piece aPiece = board.getPieceAtPosition(7,2);
        gameInstance.movePiece(aPiece, Square.getCoordinate(5,4));
        aPiece = board.getPieceAtPosition(4,6);
        gameInstance.movePiece(aPiece, Square.getCoordinate(4,4));
        aPiece = board.getPieceAtPosition(5,4);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=6, posY=5}, logic.Square{posX=7, posY=6}, logic.Square{posX=6, posY=3}, logic.Square{posX=7, posY=2}, logic.Square{posX=4, posY=5}, logic.Square{posX=3, posY=6}, logic.Square{posX=4, posY=3}, logic.Square{posX=3, posY=2}, logic.Square{posX=4, posY=4}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);

        String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  P  P  X  P  P  P  \n" +
                "D  X  X  X  X  X  X  Pr \n" +
                "X  X  X  X  P  Pr X  X  \n" +
                "X  X  X  X  X  X  X  X  \n" +
                "D  X  X  X  X  X  X  X  \n" +
                "P  P  P  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual,board.toString());

        aPiece = board.getPieceAtPosition(2,1);
        gameInstance.movePiece(aPiece, Square.getCoordinate(2,3));
        aPiece = board.getPieceAtPosition(2, 6);
        gameInstance.movePiece(aPiece, Square.getCoordinate(2, 4));

        aPiece = board.getPieceAtPosition(5,4);
        possibleMoves = gameInstance.getSafePieceMoves(aPiece, players.get(0));
        expectedOutput = stripNewlines("[logic.Square{posX=6, posY=5}, logic.Square{posX=7, posY=6}, logic.Square{posX=6, posY=3}, logic.Square{posX=7, posY=2}, logic.Square{posX=4, posY=5}, logic.Square{posX=3, posY=6}, logic.Square{posX=4, posY=3}, logic.Square{posX=3, posY=2}, logic.Square{posX=2, posY=1}]");
        actualOutput = stripNewlines(possibleMoves.toString());
        assertEquals(expectedOutput, actualOutput);
        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
                "P  P  X  P  X  P  P  P  \n" +
                "D  X  X  X  X  X  X  Pr \n" +
                "X  X  P  X  P  Pr X  X  \n" +
                "X  X  P  X  X  X  X  X  \n" +
                "D  X  X  X  X  X  X  X  \n" +
                "P  P  X  P  P  P  P  P  \n" +
                "R  Kn Bi Q  K  Bi Kn R  \n";
        assertEquals(expectedVisual,board.toString());
        assertEquals(board.toString(), expectedVisual);
    }

//    public void testMovementDeletion()
//    {
//        Piece aPawn = board.getPieceAtPosition(1,1);
//        gameInstance.movePiece(aPawn, new Square(1,3));
//        Piece shouldBeNull = board.getPieceAtPosition(1,1);
//        assertNull(shouldBeNull);
//
//        String expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
//                "P  P  P  P  P  P  P  P  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  P  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "P  X  P  P  P  P  P  P  \n" +
//                "R  Kn Bi Q  K  Bi Kn R  \n";
//        assertEquals(board.toString(), expectedVisual);
//        //System.out.println(board.toString());
//
//        gameInstance.movePiece(aPawn, new Square(3,3));
//        shouldBeNull = board.getPieceAtPosition(1,3);
//        assertNull(shouldBeNull);
//        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
//                "P  P  P  P  P  P  P  P  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  P  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "P  X  P  P  P  P  P  P  \n" +
//                "R  Kn Bi Q  K  Bi Kn R  \n";
//        assertEquals(board.toString(), expectedVisual);
//        //System.out.println(board.toString());
//
//        gameInstance.movePiece(aPawn, new Square(3,0));
//        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
//                "P  P  P  P  P  P  P  P  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "P  X  P  P  P  P  P  P  \n" +
//                "R  Kn Bi P  K  Bi Kn R  \n";
//        assertEquals(board.toString(), expectedVisual);
//        //System.out.println(board.toString());
//
//        gameInstance.movePiece(aPawn, new Square(4,0));
//        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
//                "P  P  P  P  P  P  P  P  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "P  X  P  P  P  P  P  P  \n" +
//                "R  Kn Bi X  P  Bi Kn R  \n";
//        assertEquals(board.toString(), expectedVisual);
//        //System.out.println(board.toString());
//
//        gameInstance.movePiece(aPawn, new Square(5,0));
//        expectedVisual = "R  Kn Bi Q  K  Bi Kn R  \n" +
//                "P  P  P  P  P  P  P  P  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "P  X  P  P  P  P  P  P  \n" +
//                "R  Kn Bi X  X  P  Kn R  \n";
//        assertEquals(board.toString(), expectedVisual);
//        //System.out.println(board.toString());
//
//        shouldBeNull = board.getPieceAtPosition(4, 0);
//        assertEquals(shouldBeNull, null);
//
//        Piece shouldNotBeNull = board.getPieceAtPosition(5, 0);
//        assertNotNull(shouldNotBeNull);
//
//        gameInstance.movePiece(aPawn, new Square(5,7));
//        expectedVisual = "R  Kn Bi Q  K  P  Kn R  \n" +
//                "P  P  P  P  P  P  P  P  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "X  X  X  X  X  X  X  X  \n" +
//                "P  X  P  P  P  P  P  P  \n" +
//                "R  Kn Bi X  X  X  Kn R  \n";
//
//        assertEquals(board.toString(), expectedVisual);
//        expectedVisual = "logic.Player{pieces=[R , Kn, Bi, Q , K , Kn, R , P , P , P , P , P , P , P , P ], playerID=1, king=K }\n";
//
//        Player playerOne = players.get(1);
//        String actualString = playerOne.toString().replace("\n","").replace("\r","");
//        String expectedVisual_noCRLF = expectedVisual.replace("\n","").replace("\r","");
//        assertEquals(expectedVisual_noCRLF, actualString);
//
//
//    }





}