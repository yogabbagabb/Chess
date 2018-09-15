import java.util.ArrayList;
import java.util.List;


public class Game {

    private List <Player> players;
    private Board chessBoard;
    private int numPlayers;
    private int currentPlayerID;

    public Game(boolean whiteBelow)
    {
        initializeStandardBoard(whiteBelow);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Board chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    private void initializeStandardBoard(boolean whiteBelow)
    {
        numPlayers = 2;
        currentPlayerID = 0;

        int boardLength = 8;
        int boardWidth = 8;

        List <Piece> playerOnePieces;
        List <Piece> playerTwoPieces;

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

        int [] secondaryPieceIdx = {0,7};
        int [] PawnIdx = {1, 6};
        int [] playerIDs = {bottomPlayerID, topPlayerID};
        Player [] playerArray = {new Player (bottomPlayerID), new Player (topPlayerID)};
        MOVE_PATTERN [] pawnDir = {MOVE_PATTERN.NORTH, MOVE_PATTERN.SOUTH};
        
        List <Piece> currentPiecesList = new ArrayList <> ();
        Piece aPiece;

        for (int pIndex = 0; pIndex < numPlayers; pIndex++)
        {
            aPiece = new Rook(new Coordinate(0,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[0][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Knight(new Coordinate(1,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[1][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Bishop(new Coordinate(2,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[2][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Queen(new Coordinate(3,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[3][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new King(new Coordinate(4,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[4][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);
            playerArray[pIndex].setKing(aPiece);

            aPiece = new Bishop(new Coordinate(5,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[5][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Knight(new Coordinate(6,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[6][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Rook(new Coordinate(7,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[7][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            for (int colIndex = 0; colIndex < boardWidth; colIndex++)
            {
                aPiece = new Pawn(new Coordinate(colIndex, PawnIdx[pIndex]), playerIDs[pIndex],
                        pawnDir[pIndex]);
                piecePositions[colIndex][PawnIdx[pIndex]] = aPiece;
                currentPiecesList.add(aPiece);
            }

            if (pIndex == 0)
            {
                playerOnePieces = new ArrayList<>(currentPiecesList);
                playerArray[pIndex].setPieces(playerOnePieces);
            }

            else
            {
                playerTwoPieces = new ArrayList<>(currentPiecesList);
                playerArray[pIndex].setPieces(playerTwoPieces);
            }
            currentPiecesList.clear();
        }

        chessBoard = new Board (boardLength, boardWidth, piecePositions);
        players = new ArrayList<>(List.of(playerArray[0], playerArray[1]));

    }



}



