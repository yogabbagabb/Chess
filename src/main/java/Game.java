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

        int bottomPlayerID;
        int topPlayerID;
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
    private void deletePiece(Piece aPiece, Coordinate position)
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
    private Player getPlayerOfPiece(Piece aPiece)
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


    private boolean isSuicidal(Board board, Player aPlayer, int posX, int posY)
    {
        return isSuicidal(board, aPlayer, new Coordinate(posX, posY));
    }

    /**
     * @param aPlayer The player who wishes to move a piece.
     * @param aCoor The coordinate aPlayer wishes to move his or her piece to.
     * @return Whether the coordinate is occupied by another of aPlayer's pieces (true) or not (false)
     */
    public boolean isSuicidal(Board board, Player aPlayer, Coordinate aCoor)
    {
        Piece pieceAtCoor = board.getPieceAtPosition(aCoor);
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

    private boolean isValidAndEnemyOccupied(Board aBoard, Player aPlayer, int posX, int posY)
    {
        return aBoard.contains(posX, posY) && isEnemyOccupied(aBoard, aPlayer, posX, posY);
    }
    boolean isEnemyOccupied(Board board, Player aPlayer, int posX, int posY)
    {
       return isEnemyOccupied(board, aPlayer, Coordinate.getCoordinate(posX, posY));
    }

    boolean isEnemyOccupied(Board board, Player aPlayer, Coordinate aCoor)
    {
        Piece pieceAtCoor = board.getPieceAtPosition(aCoor);
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

    boolean isUnoccupied(Board board, int posX, int posY)
    {
        return isUnoccupied(board, Coordinate.getCoordinate(posX, posY));
    }

    boolean isUnoccupied(Board board, Coordinate aCoor)
    {
       return board.getPieceAtPosition(aCoor) == null;
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
        int curX = pieceCoor.getPosX();
        int curY = pieceCoor.getPosY();

        LinkedHashSet<Coordinate> pieceMovesToAdd = new LinkedHashSet <>();

        for (MOVE_PATTERN move : movePatterns)
        {
            switch (move)
            {
                case NORTH:
                    if (aBoard.contains(curX, curY + 1)&& !isSuicidal(aBoard, aPlayer, curX, curY + 1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + 1));
                    }
                    break;
                case NORTH_NO_KILL:
                    if (aBoard.contains(curX, curY + 1) && isUnoccupied(aBoard, curX, curY+1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + 1));
                    }
                    break;
                case SOUTH:
                    if (aBoard.contains(curX, curY - 1) && !isSuicidal(aBoard, aPlayer, curX, curY - 1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - 1));
                    }
                    break;
                case SOUTH_NO_KILL:
                    if (aBoard.contains(curX, curY - 1) && isUnoccupied(aBoard, curX, curY-1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - 1));
                    }
                    break;
                case DIAGONAL_ON_ENEMY_NORTH:
                    if (aBoard.contains(curX + 1, curY + 1) && isEnemyOccupied(aBoard, aPlayer, curX + 1, curY +1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX + 1, curY + 1));
                    }
                    if (aBoard.contains(curX - 1, curY + 1) && isEnemyOccupied(aBoard, aPlayer, curX - 1, curY +1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX - 1, curY + 1));
                    }
                    break;
                case DIAGONAL_ON_ENEMY_SOUTH:
                    if (aBoard.contains(curX - 1, curY - 1) && isEnemyOccupied(aBoard, aPlayer, curX - 1, curY - 1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX - 1, curY - 1));
                    }
                    if (aBoard.contains(curX + 1, curY - 1) && isEnemyOccupied(aBoard, aPlayer, curX + 1, curY - 1))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX + 1, curY - 1));
                    }
                    break;
                case TWO_INITIALLY_NORTH:
                    if (curY == 1 && isUnoccupied(aBoard, curX, curY+1) && isUnoccupied(aBoard, curX, curY + 2))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + 2));
                    }
                    break;
                case TWO_INITIALLY_SOUTH:
                    if (curY == 6 && isUnoccupied(aBoard, curX, curY-1) && isUnoccupied(aBoard, curX, curY - 2))
                    {
                        pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - 2));
                    }
                    break;
                default:
                    pieceMovesToAdd.addAll(getMoves_unlimitedMotions(aPiece, aPlayer, aBoard, move));
                    break;
            }

        }
        return pieceMovesToAdd;

    }

    private Set<Coordinate> getMoves_unlimitedMotions(Piece aPiece, Player aPlayer, Board aBoard, MOVE_PATTERN move)
    {
        Coordinate pieceCoor = aPiece.getPosition();
        int curX = pieceCoor.getPosX();
        int curY = pieceCoor.getPosY();
        int boardLength = aBoard.getLength();
        int boardWidth = aBoard.getWidth();

        LinkedHashSet<Coordinate> pieceMovesToAdd = new LinkedHashSet <>();
        int vertCounter;
        int horCounter;
        switch (move)
        {
            case UNRESTRICTED_NORTH:
                for (vertCounter = 1;
                     vertCounter < boardLength && chessBoard.contains(curX, curY + vertCounter) &&
                     isUnoccupied(aBoard, curX, curY + vertCounter) ;++vertCounter)
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + vertCounter));
                }

                if(isValidAndEnemyOccupied(aBoard,aPlayer, curX, curY + vertCounter))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY + vertCounter));
                }
                break;
            case UNRESTRICTED_EAST:
                for (horCounter = 1;
                     horCounter < boardWidth && chessBoard.contains(curX + horCounter, curY) &&
                     isUnoccupied(aBoard, curX + horCounter, curY);++horCounter){
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX + horCounter, curY));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX + horCounter, curY))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX + horCounter, curY));
                }
                break;
            case UNRESTRICTED_SOUTH:
                for (vertCounter = 1;
                     vertCounter < boardLength && chessBoard.contains(curX, curY - vertCounter) &&
                     isUnoccupied(aBoard, curX, curY - vertCounter); ++vertCounter){
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - vertCounter));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX, curY - vertCounter))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY - vertCounter));
                }
                break;
            case UNRESTRICTED_WEST:
                for (horCounter = 1;
                     horCounter < boardWidth && chessBoard.contains(curX - horCounter, curY) &&
                     isUnoccupied(aBoard, curX - horCounter, curY);++horCounter)
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX - horCounter, curY));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX - horCounter, curY))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX - horCounter, curY));
                }
                break;

            case UNRESTRICTED_NORTH_EAST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX + horCounter, curY + vertCounter) &&
                     isUnoccupied(aBoard, curX + horCounter, curY + vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX + horCounter, curY + vertCounter));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX + horCounter, curY + vertCounter))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX + horCounter, curY + vertCounter));
                }
                break;

            case UNRESTRICTED_NORTH_WEST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX - horCounter, curY + vertCounter) &&
                             isUnoccupied(aBoard, curX - horCounter, curY + vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX - horCounter, curY + vertCounter));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX - horCounter, curY + vertCounter))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX - horCounter, curY + vertCounter));
                }
                break;
            case UNRESTRICTED_SOUTH_EAST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX + horCounter, curY - vertCounter) &&
                             isUnoccupied(aBoard, curX + horCounter, curY - vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX + horCounter, curY - vertCounter));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX + horCounter, curY - vertCounter))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX + horCounter, curY - vertCounter));
                }
                break;
            case UNRESTRICTED_SOUTH_WEST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX - horCounter, curY - vertCounter) &&
                             isUnoccupied(aBoard, curX - horCounter, curY - vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX - horCounter, curY - vertCounter));
                }
                if (isValidAndEnemyOccupied(aBoard,aPlayer, curX - horCounter, curY - vertCounter))
                {
                    pieceMovesToAdd.add(Coordinate.getCoordinate(curX - horCounter, curY - vertCounter));
                }
                break;
            case L_SHAPE:
                pieceMovesToAdd.addAll(getMoves_lShape(aPiece, aPlayer, aBoard));
                break;
            case ONE_ANYWHERE:
                pieceMovesToAdd.addAll(getMoves_oneAnywhere(aPiece, aPlayer, aBoard));
                break;
        }

        return pieceMovesToAdd;

    }

    private Set<Coordinate> getMoves_lShape(Piece aPiece, Player aPlayer, Board aBoard)
    {
        final int [] possibleXCoor = {-2, -1, 1, 2, 2, 1, -1, -2};
        final int [] possibleYCoor = {1, 2, 2, 1, -1, -2, -2, -1};

        return getCoordinatesFromOffsets(aPiece, aPlayer, aBoard, possibleXCoor, possibleYCoor);

    }


    private Set<Coordinate> getMoves_oneAnywhere(Piece aPiece, Player aPlayer, Board aBoard)
    {
        final int [] possibleXCoor = {0,1,1,1,0,-1,-1,-1};
        final int [] possibleYCoor = {1,1,0,-1,-1,-1,0,1};

        return getCoordinatesFromOffsets(aPiece, aPlayer, aBoard, possibleXCoor, possibleYCoor);

    }

    private Set<Coordinate> getCoordinatesFromOffsets(Piece aPiece, Player aPlayer, Board aBoard, int[] possibleXCoor, int[] possibleYCoor) {
        Coordinate pieceCoor = aPiece.getPosition();
        int posX = pieceCoor.getPosX();
        int posY = pieceCoor.getPosY();
        int curX;
        int curY;
        LinkedHashSet<Coordinate> pieceMovesToAdd = new LinkedHashSet<>();

        final int numArrangements = 8;

        for (int idx = 0; idx < numArrangements; ++idx) {
            curX = posX + possibleXCoor[idx];
            curY = posY + possibleYCoor[idx];

            if (aBoard.contains(curX, curY) && (isUnoccupied(aBoard, curX, curY) || isEnemyOccupied(aBoard, aPlayer, curX, curY))) {
                pieceMovesToAdd.add(Coordinate.getCoordinate(curX, curY));
            }

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



