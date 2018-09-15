import java.util.ArrayList;
import java.util.List;


public class Game {
    public static final int STANDARD_BOARD = 1;
    List <Player> players;
    Board chessBoard;
    int numPlayers;
    int currentPlayerID;

    public Game(boolean whiteBelow)
    {

    }

    private void initializeStandardBoard(boolean whiteBelow)
    {
        numPlayers = 2;
        currentPlayerID = 0;
        List <Piece> playerOnePieces = new ArrayList<>();
        List <Piece> playerTwoPieces = new ArrayList<>();

        int bottomPlayerID = -1;
        int topPlayerID = -1;
        if (whiteBelow)
        {
            bottomPlayerID = 0;
            topPlayerID = 1;
        }
        else
        {
            bottomPlayerID = 1;
            topPlayerID = 0;
        }

        Piece [][] piecePositions = new Piece[8][8];

        int [] pIndexToPosition = new int [2];
        for (int pIndex = 0; pIndex < numPlayers; pIndex++)
        {
            piecePositions[0][0] = new Rook(new Coordinate(0,0), bottomPlayerID);
            piecePositions[1][0] = new Knight(new Coordinate(1,0), bottomPlayerID);
            piecePositions[2][0] = new Bishop(new Coordinate(2,0), bottomPlayerID);
            piecePositions[3][0] = new Queen(new Coordinate(3,0), bottomPlayerID);
            piecePositions[4][0] = new King(new Coordinate(4,0), bottomPlayerID);
            piecePositions[5][0] = new Bishop(new Coordinate(5,0), bottomPlayerID);
            piecePositions[6][0] = new Knight(new Coordinate(6,0), bottomPlayerID);
            piecePositions[7][0] = new Rook(new Coordinate(7,0), bottomPlayerID);
        }
    }



}



