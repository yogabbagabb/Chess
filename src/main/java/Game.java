import java.util.*;


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
        boolean north = true;
        boolean [] pawnDir = {north, !north};
        
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


    /**
     * @param aPiece The piece that will be moved; we assume it exists on the board.
     * @param newPosition The new position where aPiece will be moved to; we assume it is a valid position.
     *                    Move an existent piece to a legal position. Kill the piece originally at the new position;
     *                    we assume it is an enemy.
     */
    public void  movePiece(Piece aPiece, Coordinate newPosition)
    {
        Coordinate curPosition = aPiece.getPosition();
        chessBoard.setPieceAtPosition(curPosition, null);

        Piece newPosPiece = chessBoard.getPieceAtPosition(newPosition);

        if (newPosPiece != null)
        {
           deletePiece(newPosPiece, newPosition);
        }

        chessBoard.setPieceAtPosition(newPosition, aPiece);
        aPiece.setPosition(newPosition);
    }

    /**
     * @param aPiece The piece that will be removed; we assume it exists on the board.
     * @param position The position of the piece that will be removed; we assume it exists.
     *               Delete a piece from the board and from the list of pieces belonging to the player owning the piece.
     */
    public void  deletePiece(Piece aPiece, Coordinate position)
    {
        if (aPiece == null)
        {
            throw new java.lang.Error("You are trying to delete a non-existent piece");
        }

        Player playerOfPiece = getPlayerOfPiece(aPiece);
        if (playerOfPiece != null)
        {
            chessBoard.setPieceAtPosition(position,null);
            playerOfPiece.removePiece(aPiece);
        }
        else
        {
            throw new java.lang.Error("There is no player with the piece you wish to delete!");
        }
    }

    /**
     * @param aPiece The piece whose player we want to return.
     * @return The player possessing aPiece.
     * We make no assumption that this piece is actually on the board. If its player id is existent, then the
     * corresponding player will be returned even if the player doesn't own the piece.
     */
    public Player getPlayerOfPiece(Piece aPiece)
    {
        // Get the piece's player id
        int pid = aPiece.getPlayerID();
        Player playerOfPiece = null;

        for (Player aPlayer : players)
        {
            if (aPlayer.getPlayerID() == pid)
            {
                playerOfPiece = aPlayer;
                return playerOfPiece;
            }
        }

        return null;
    }


    public boolean isSuicidal(Player aPlayer, int posX, int posY)
    {
        return isSuicidal(aPlayer, new Coordinate(posX, posY));
    }

    /**
     * @param aPlayer The player who wishes to move a piece.
     * @param aCoor The coordinate aPlayer wishes to move his or her piece to.
     * @return Whether the coordinate is occupied by another of aPlayer's pieces (true) or not (false)
     */
    public boolean isSuicidal(Player aPlayer, Coordinate aCoor)
    {
        Piece pieceAtCoor = chessBoard.getPieceAtPosition(aCoor);
        if (pieceAtCoor == null)
        {
            return false;
        }

        if (pieceAtCoor.getPlayerID() == aPlayer.getPlayerID())
        {
            return true;
        }

        return false;
    }

    public boolean isEnemyOccupied(Player aPlayer, int posX, int posY)
    {
       return isEnemyOccupied(aPlayer, Coordinate.getCoordinate(posX, posY));
    }

    public boolean isEnemyOccupied(Player aPlayer, Coordinate aCoor)
    {
        Piece pieceAtCoor = chessBoard.getPieceAtPosition(aCoor);
        if (pieceAtCoor == null)
        {
            return false;
        }

        if (pieceAtCoor.getPlayerID() != aPlayer.getPlayerID())
        {
            return true;
        }

        return false;
    }

    public boolean isUnoccupied(int posX, int posY)
    {
        return isUnoccupied(Coordinate.getCoordinate(posX, posY));
    }

    public boolean isUnoccupied(Coordinate aCoor)
    {
       return chessBoard.getPieceAtPosition(aCoor) == null;
    }

    /**
     *
     * @param aPiece The piece whose set of moves we will identify.
     * @param aPlayer The player possessing the piece; we assume aPlayer is accurate.
     * @param aBoard The board on which we can determine the set of moves we can make
     * @return A set of coordinates that the piece can move to, not taking into consideration that, by moving to a coordinate,
     * the opponent(s) can now kill our king.
     */
    public Set<Coordinate> getPieceMoves(Piece aPiece, Player aPlayer, Board aBoard)
    {
        List <MOVE_PATTERN> movePatterns = aPiece.getMovePatterns();
        Coordinate pieceCoor = aPiece.getPosition();
        int pieceX = pieceCoor.getPosX();
        int pieceY = pieceCoor.getPosY();
        int boardLength = aBoard.getLength();
        int boardWidth = aBoard.getWidth();

        HashSet<Coordinate> pieceMoves = new HashSet <Coordinate>();
        HashSet<Coordinate> pieceMovesToAdd = new HashSet <Coordinate>();

        for (MOVE_PATTERN move : movePatterns)
        {
            int curX = pieceX;
            int curY = pieceY;
            switch (move)
            {
                case NORTH:
                    if (chessBoard.contains(curX, curY + 1)&& !isSuicidal(aPlayer, curX, curY + 1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + 1));
                    }
                    break;
                case NORTH_NO_KILL:
                    if (chessBoard.contains(curX, curY + 1) && isUnoccupied(curX, curY+1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + 1));
                    }
                    break;
                case SOUTH:
                    if (chessBoard.contains(curX, curY - 1) && !isSuicidal(aPlayer, curX, curY - 1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - 1));
                    }
                    break;
                case SOUTH_NO_KILL:
                    if (chessBoard.contains(curX, curY - 1) && isUnoccupied(curX, curY-1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - 1));
                    }
                    break;
                case DIAGONAL_ON_ENEMY_NORTH:
                    if (chessBoard.contains(curX + 1, curY + 1) && isEnemyOccupied(aPlayer, curX + 1, curY +1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX + 1, curY + 1));
                    }
                    if (chessBoard.contains(curX - 1, curY + 1) && isEnemyOccupied(aPlayer, curX - 1, curY +1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX - 1, curY + 1));
                    }
                    break;
                case DIAGONAL_ON_ENEMY_SOUTH:
                    if (chessBoard.contains(curX + 1, curY + 1) && isEnemyOccupied(aPlayer, curX + 1, curY +1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX + 1, curY + 1));
                    }
                    if (chessBoard.contains(curX - 1, curY + 1) && isEnemyOccupied(aPlayer, curX - 1, curY +1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX - 1, curY + 1));
                    }
                    break;
                case TWO_INITIALLY_NORTH:
                    if (curY == 1 && isUnoccupied(curX, curY+1) && isUnoccupied(curX, curY + 2))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + 2));
                    }
                    break;
                case TWO_INITIALLY_SOUTH:
                    if (curY == 6 && isUnoccupied(curX, curY-1) && isUnoccupied(curX, curY - 2))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - 2));
                    }
                    break;
                default:
                    pieceMovesToAdd.addAll(getPieceMovesAux(aPiece, aPlayer, aBoard, move));
                    break;
            }

        }
        return pieceMovesToAdd;

    }

    public Set<Coordinate> getPieceMovesAux(Piece aPiece, Player aPlayer, Board aBoard, MOVE_PATTERN move)
    {
        Coordinate pieceCoor = aPiece.getPosition();
        int pieceX = pieceCoor.getPosX();
        int pieceY = pieceCoor.getPosY();
        int boardLength = aBoard.getLength();
        int boardWidth = aBoard.getWidth();

        int curX = pieceX;
        int curY = pieceY;
        HashSet<Coordinate> pieceMovesToAdd = new HashSet <Coordinate>();
        switch (move)
        {
            case UNRESTRICTED_NORTH:
                break;
            case UNRESTRICTED_EAST:
                break;
            case UNRESTRICTED_SOUTH:
                break;
            case UNRESTRICTED_WEST:
                break;
            case UNRESTRICTED_NORTH_EAST:
                break;
            case UNRESTRICTED_NORTH_WEST:
                break;
            case UNRESTRICTED_SOUTH_EAST:
                break;
            case UNRESTRICTED_SOUTH_WEST:
                break;
            case L_SHAPE:
                break;
            case ONE_ANYWHERE:
                break;
        }

        return pieceMovesToAdd;

    }



    /**
     *
     * @param aPiece The piece whose set of moves from possibleMoves we will refine.
     * @param aPlayer The player possessing the piece; we assume aPlayer is accurate.
     * @param aBoard The board on which we can determine the set of moves we can make.
     * @param possibleMoves A set of coordinates that the piece can move to, not taking into consideration that, by moving to a coordinate,
     * the opponent(s) can now kill our king.
     * @return A set of coordinates that we can move to without being checked
     */
    public Set<Coordinate> getSafePieceMoves(Piece aPiece, Player aPlayer, Board aBoard,Set<Coordinate> possibleMoves)
    {
        return null;
    }


    /**
     * @param aBoard The board on which we can determine the set of moves we can make.
     * @param aPlayer The player who wishes to end his turn having moved in such a way that the board is has the state of aBoard.
     * @return Whether the configuration is legal: is aPlayer's king not at risk?
     */
    public boolean isBoardLayoutSafe(Board aBoard, Player aPlayer)
    {
        return false;
    }



}



