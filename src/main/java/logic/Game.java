package logic;

import java.util.*;


/**
 * logic.Game moves the pieces belonging to a player on a board in a legal way.
 */
public class Game {

    private List <Player> players;
    private Board chessBoard;
    private int numPlayers;

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



    /**
     * Setup a standard chess board.
     * @param whiteBelow Signals that white pieces the ones closer to the bottom edge of the board if true.
     */
    private void initializeStandardBoard(boolean whiteBelow)
    {
        numPlayers = 2;

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
            aPiece = new Rook(new Square(0,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[0][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Knight(new Square(1,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[1][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Bishop(new Square(2,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[2][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Queen(new Square(3,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[3][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new King(new Square(4,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[4][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);
            playerArray[pIndex].setKing(aPiece);

            aPiece = new Bishop(new Square(5,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[5][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Knight(new Square(6,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[6][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            aPiece = new Rook(new Square(7,secondaryPieceIdx[pIndex]), playerIDs[pIndex]);
            piecePositions[7][secondaryPieceIdx[pIndex]] = aPiece;
            currentPiecesList.add(aPiece);

            for (int colIndex = 0; colIndex < boardWidth; colIndex++)
            {
                aPiece = new Pawn(new Square(colIndex, PawnIdx[pIndex]), playerIDs[pIndex],
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
    public void  movePiece(Piece aPiece, Square newPosition)
    {
        Square curPosition = aPiece.getPosition();
        // Vacate the location of the piece being moved.
        chessBoard.setPieceAtPosition(curPosition, null);

        // Get the piece at the new location, if it exists.
        Piece newPosPiece = chessBoard.getPieceAtPosition(newPosition);

        if (newPosPiece != null)
        {
           deletePiece(newPosPiece, newPosition);
        }

        // Update the board's record of pieces and the location of the now moved piece.
        chessBoard.setPieceAtPosition(newPosition, aPiece);
        aPiece.setPosition(newPosition);
    }

    /**
     * @param aPiece The piece that will be removed; we assume it exists on the board.
     * @param position The position of the piece that will be removed; we assume it exists.
     *               Delete a piece from the board and from the list of pieces belonging to the player owning the piece.
     */
    private void deletePiece(Piece aPiece, Square position)
    {
        if (aPiece == null)
        {
            throw new java.lang.Error("You are trying to delete a non-existent piece");
        }

        Player playerOfPiece = getPlayerOfPiece(aPiece);
        if (playerOfPiece != null)
        {
            // Vacate the position of aPiece.
            chessBoard.setPieceAtPosition(position,null);
            // Remove the piece from the player's list of pieces.
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
        int pid = aPiece.getPlayerID();
        Player playerOfPiece = null;

        // Find which player in players has the same id as that stored by aPiece.
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

    /**
     *
     * Set a piece at a position, update its coordinate to be at the new position and add the piece
     * to its owner's (player's) list of pieces.
     * @param xPos X coordinate of piece.
     * @param yPos Y coordinate of piece.
     * @param replacementPiece The piece that will occupy xPos, yPos.
     */
    public void putPieceOnBoard(int xPos, int yPos, Piece replacementPiece)
    {
        chessBoard.setPieceAtPosition(xPos, yPos, replacementPiece);

        if (replacementPiece  != null)
        {
            replacementPiece.setPosition(Square.getCoordinate(xPos,yPos));
            Player playerOfPiece = getPlayerOfPiece(replacementPiece);
            playerOfPiece.addPiece(replacementPiece);
        }
    }


    private boolean isValidAndEnemyOccupied(Player aPlayer, int posX, int posY)
    {
        return chessBoard.contains(posX, posY) && isEnemyOccupied(aPlayer, posX, posY);
    }

    private boolean isEnemyOccupied(Player aPlayer, int posX, int posY)
    {
       return isEnemyOccupied(aPlayer, Square.getCoordinate(posX, posY));
    }

    /**
     * Check if a coordinate is occupied by a piece other than a given player.
     * @param aPlayer The player relative to whom opponent is understood.
     * @param aCoor The coordinate to check.
     * @return Whether the coordinate is occupied by an opponent's piece.
     */
    private boolean isEnemyOccupied(Player aPlayer, Square aCoor)
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

    private boolean isUnoccupied(int posX, int posY)
    {
        return isUnoccupied(Square.getCoordinate(posX, posY));
    }

    private boolean isUnoccupied(Square aCoor)
    {
       return chessBoard.getPieceAtPosition(aCoor) == null;
    }

    /**
     * @param aPiece The piece whose set of moves we will identify.
     * @param aPlayer The player possessing the piece; we assume aPlayer is accurate.
     * @return A set of coordinates that the piece can move to, not taking into consideration that, by moving to a coordinate,
     * the opponent(s) can now kill our king.
     */
    private Set<Square> getPieceMoves(Piece aPiece, Player aPlayer)
    {
        // Get the patterns that describe how aPiece can move.
        List <MOVE_PATTERN> movePatterns = aPiece.getMovePatterns();
        Square pieceCoor = aPiece.getPosition();
        // Get the current locations of aPiece.
        int curX = pieceCoor.getPosX();
        int curY = pieceCoor.getPosY();

        // Make a set to store the coordinates aPiece can take on.
        LinkedHashSet<Square> pieceMovesToAdd = new LinkedHashSet <>();

        for (MOVE_PATTERN move : movePatterns)
        {
            switch (move)
            {
                case NORTH_NO_KILL:
                    // Make sure the board contains the proposed coordinate and is unoccupied
                    if (chessBoard.contains(curX, curY + 1) && isUnoccupied(curX, curY+1))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX, curY + 1));
                    }
                    break;
                case SOUTH_NO_KILL:
                    if (chessBoard.contains(curX, curY - 1) && isUnoccupied(curX, curY-1))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX, curY - 1));
                    }
                    break;
                case DIAGONAL_ON_ENEMY_NORTH:
                    // Make sure the board contains the proposed location and has an enemy on it.
                    if (chessBoard.contains(curX + 1, curY + 1) && isEnemyOccupied(aPlayer, curX + 1, curY +1))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX + 1, curY + 1));
                    }
                    if (chessBoard.contains(curX - 1, curY + 1) && isEnemyOccupied(aPlayer, curX - 1, curY +1))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX - 1, curY + 1));
                    }
                    break;
                case DIAGONAL_ON_ENEMY_SOUTH:
                    if (chessBoard.contains(curX - 1, curY - 1) && isEnemyOccupied(aPlayer, curX - 1, curY - 1))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX - 1, curY - 1));
                    }
                    if (chessBoard.contains(curX + 1, curY - 1) && isEnemyOccupied(aPlayer, curX + 1, curY - 1))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX + 1, curY - 1));
                    }
                    break;
                case TWO_INITIALLY_NORTH:
                    // Make sure that the pawn is at its initial position and its path is unobstructed.
                    if (curY == 1 && isUnoccupied(curX, curY+1) && isUnoccupied(curX, curY + 2))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX, curY + 2));
                    }
                    break;
                case TWO_INITIALLY_SOUTH:
                    if (curY == 6 && isUnoccupied(curX, curY-1) && isUnoccupied(curX, curY - 2))
                    {
                        pieceMovesToAdd.add(Square.getCoordinate(curX, curY - 2));
                    }
                    break;
                default:
                    pieceMovesToAdd.addAll(getMoves_unlimitedMotions(aPiece, aPlayer,  move));
                    break;
            }

        }
        return pieceMovesToAdd;

    }

    /**
     * @param aPiece The piece whose set of moves we will identify.
     * @param aPlayer The player possessing the piece; we assume aPlayer is accurate.
     * @return A set of coordinates that the piece can move to, not taking into consideration that, by moving to a coordinate,
     * the opponent(s) can now kill our king. These are coordinates accessible by pieces other than pawns.
     */
    private Set<Square> getMoves_unlimitedMotions(Piece aPiece, Player aPlayer, MOVE_PATTERN move)
    {
        Square pieceCoor = aPiece.getPosition();
        int curX = pieceCoor.getPosX();
        int curY = pieceCoor.getPosY();
        int boardLength = chessBoard.getLength();
        int boardWidth = chessBoard.getWidth();

        LinkedHashSet<Square> pieceMovesToAdd = new LinkedHashSet <>();
        int vertCounter;
        int horCounter;
        switch (move)
        {
            case UNRESTRICTED_NORTH:
                // Find the maximum vertical offset that the piece can move north without BOTH running off the board
                // and crossing another piece.
                for (vertCounter = 1;
                     vertCounter < boardLength && chessBoard.contains(curX, curY + vertCounter) &&
                     isUnoccupied(curX, curY + vertCounter) ;++vertCounter)
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX, curY + vertCounter));
                }

                // If our vertical offset stopped incrementing because we encountered another piece,
                // then determine if this piece belongs to our enemy, in which case we can move to our enemy's spot.
                if(isValidAndEnemyOccupied(aPlayer, curX, curY + vertCounter))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX, curY + vertCounter));
                }
                break;
            case UNRESTRICTED_EAST:
                for (horCounter = 1;
                     horCounter < boardWidth && chessBoard.contains(curX + horCounter, curY) &&
                     isUnoccupied(curX + horCounter, curY);++horCounter){
                    pieceMovesToAdd.add(Square.getCoordinate(curX + horCounter, curY));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX + horCounter, curY))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX + horCounter, curY));
                }
                break;
            case UNRESTRICTED_SOUTH:
                for (vertCounter = 1;
                     vertCounter < boardLength && chessBoard.contains(curX, curY - vertCounter) &&
                     isUnoccupied(curX, curY - vertCounter); ++vertCounter){
                    pieceMovesToAdd.add(Square.getCoordinate(curX, curY - vertCounter));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX, curY - vertCounter))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX, curY - vertCounter));
                }
                break;
            case UNRESTRICTED_WEST:
                for (horCounter = 1;
                     horCounter < boardWidth && chessBoard.contains(curX - horCounter, curY) &&
                     isUnoccupied(curX - horCounter, curY);++horCounter)
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX - horCounter, curY));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX - horCounter, curY))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX - horCounter, curY));
                }
                break;

            // Find the maximum north-east offset that the piece can move north without BOTH running off the board
            // and crossing another piece.
            case UNRESTRICTED_NORTH_EAST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX + horCounter, curY + vertCounter) &&
                     isUnoccupied(curX + horCounter, curY + vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX + horCounter, curY + vertCounter));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX + horCounter, curY + vertCounter))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX + horCounter, curY + vertCounter));
                }
                break;

            case UNRESTRICTED_NORTH_WEST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX - horCounter, curY + vertCounter) &&
                             isUnoccupied(curX - horCounter, curY + vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX - horCounter, curY + vertCounter));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX - horCounter, curY + vertCounter))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX - horCounter, curY + vertCounter));
                }
                break;
            case UNRESTRICTED_SOUTH_EAST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX + horCounter, curY - vertCounter) &&
                             isUnoccupied(curX + horCounter, curY - vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX + horCounter, curY - vertCounter));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX + horCounter, curY - vertCounter))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX + horCounter, curY - vertCounter));
                }
                break;
            case UNRESTRICTED_SOUTH_WEST:
                for (vertCounter = 1, horCounter = 1;
                     vertCounter < boardLength && horCounter < boardWidth && chessBoard.contains(curX - horCounter, curY - vertCounter) &&
                             isUnoccupied(curX - horCounter, curY - vertCounter) ;++vertCounter, ++horCounter)
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX - horCounter, curY - vertCounter));
                }
                if (isValidAndEnemyOccupied(aPlayer, curX - horCounter, curY - vertCounter))
                {
                    pieceMovesToAdd.add(Square.getCoordinate(curX - horCounter, curY - vertCounter));
                }
                break;
            case L_SHAPE:
                // Get the possible coordinates that a knight can take on.
                pieceMovesToAdd.addAll(getMoves_lShape(aPiece, aPlayer));
                break;
            case ONE_ANYWHERE:
                // Get the possible coordinates that a king can take on.
                pieceMovesToAdd.addAll(getMoves_oneAnywhere(aPiece, aPlayer));
                break;
        }

        return pieceMovesToAdd;

    }

    /**
     * @param aPiece The object referencing a knight.
     * @param aPlayer The player owning the knight.
     * @return The coordinates a particular knight can taken on.
     */
    private Set<Square> getMoves_lShape(Piece aPiece, Player aPlayer)
    {
        final int [] possibleXCoor = {-2, -1, 1, 2, 2, 1, -1, -2};
        final int [] possibleYCoor = {1, 2, 2, 1, -1, -2, -2, -1};

        return getCoordinatesFromOffsets(aPiece, aPlayer,  possibleXCoor, possibleYCoor);

    }


    /**
     *
     * @param aPiece The object referencing a king.
     * @param aPlayer The player owning the king.
     * @return The coordinates a particular king can taken on.
     */
    private Set<Square> getMoves_oneAnywhere(Piece aPiece, Player aPlayer)
    {
        final int [] possibleXCoor = {0,1,1,1,0,-1,-1,-1};
        final int [] possibleYCoor = {1,1,0,-1,-1,-1,0,1};

        return getCoordinatesFromOffsets(aPiece, aPlayer,  possibleXCoor, possibleYCoor);

    }

    /**
     *
     * @param aPiece Any piece.
     * @param aPlayer The player owning the piece.
     * @param possibleXCoor An array of x coordinates specifying the positions relative to aPiece that can be taken on. Take
     *                      the right product of this array with possibleYCoor to get the actual board coordinates.
     * @param possibleYCoor An array of y coordinates specifying the positions relative to aPiece that can be taken on.
     * @return The coordinates a piece can go to given offsets from its current position.
     */
    private Set<Square> getCoordinatesFromOffsets(Piece aPiece, Player aPlayer, int[] possibleXCoor, int[] possibleYCoor) {
        Square pieceCoor = aPiece.getPosition();
        int posX = pieceCoor.getPosX();
        int posY = pieceCoor.getPosY();
        int curX;
        int curY;
        LinkedHashSet<Square> pieceMovesToAdd = new LinkedHashSet<>();

        final int numArrangements = 8;

        for (int idx = 0; idx < numArrangements; ++idx) {
            // Get candidate positions for aPiece based on offsets from its current locations.
            curX = posX + possibleXCoor[idx];
            curY = posY + possibleYCoor[idx];

            // If the board contains the position and the position is unoccupied or occupied by our enemy, we can go there.
            if (chessBoard.contains(curX, curY) && (isUnoccupied(curX, curY) || isEnemyOccupied(aPlayer, curX, curY))) {
                pieceMovesToAdd.add(Square.getCoordinate(curX, curY));
            }

        }
        return pieceMovesToAdd;
    }


    /**
     * Enumerate those coordinates that a piece can go to without causing its player to become checked.
     * @param aPiece The piece whose set of moves from possibleMoves we will refine.
     * @param aPlayer The player possessing the piece; we assume aPlayer is accurate.
     * the opponent(s) can now kill our king.
     * @return A set of coordinates that we can move to without being checked.
     */
    public Set<Square> getSafePieceMoves(Piece aPiece, Player aPlayer)
    {
        Set<Square> pieceMoves = getPieceMoves(aPiece, aPlayer);
        for (Iterator<Square> iterator = pieceMoves.iterator(); iterator.hasNext();)
        {
            // Get a move that aPiece can take, regardless of whether taking that move results in aPlayer being checked.
            Square possibleMove = iterator.next();

            // Get the old location of aPiece.
            Square oldLoc = aPiece.getPosition();

            // Get the piece, if it exists, at the location of possibleMove.
            // If the piece exists, it is necessarily an opponent's.
            Piece pieceAtMoveLoc = chessBoard.getPieceAtPosition(possibleMove);

            if (pieceAtMoveLoc != null)
            {
                // If we enter here, then the piece belongs to an opponent.
                // Remove our oppponent's piece temporarily
                deletePiece(pieceAtMoveLoc, possibleMove);
                movePiece(aPiece, possibleMove);

                if (isChecked(aPlayer))
                {
                    iterator.remove();
                }

                movePiece(aPiece, oldLoc);
                // Add back our opponent's piece
                putPieceOnBoard(possibleMove.getPosX(), possibleMove.getPosY(), pieceAtMoveLoc);
            }
            else
            {
                movePiece(aPiece, possibleMove);

                if (isChecked(aPlayer))
                {
                    iterator.remove();
                }

                movePiece(aPiece, oldLoc);
            }



        }
        return pieceMoves;
    }

    /**
     *
     * @param aPlayer The player for whom legal moves will be enumerated.
     * @return All legal moves a player can make without putting her king in check.
     */
    private Set<Square> getAllSafePieceMoves(Player aPlayer)
    {
        List <Piece> playerPieces = aPlayer.getPieces();
        Set <Square> allSafeMoves = new LinkedHashSet<>();
        // Get the safe moves that a player can make, over all of the player's pieces.
        for (Piece playerPiece: playerPieces)
        {
            Set <Square> safeMovesForPiece = getSafePieceMoves(playerPiece, aPlayer);
            allSafeMoves.addAll(safeMovesForPiece);
        }

        return allSafeMoves;
    }

    public boolean isCheckmated(Player aPlayer)
    {
        Set<Square> allSafeMoves = getAllSafePieceMoves(aPlayer);
        return isChecked(aPlayer) && allSafeMoves.isEmpty();
    }

    public boolean isStalemated(Player aPlayer)
    {
        Set<Square> allSafeMoves = getAllSafePieceMoves(aPlayer);
        return !isChecked(aPlayer) && allSafeMoves.isEmpty();

    }




    /**
     * @param aPlayer The player who wishes to end his turn having moved in such a way that the board is has the state of aBoard.
     * @return Whether the configuration is legal: is aPlayer's king at risk.
     */
    private boolean isBoardLayoutSafe(Player aPlayer)
    {
        Piece aPlayerKing = aPlayer.getKing();
        Square coorOfKing = aPlayerKing.getPosition();
        Set <Square> accessibleCoor = new LinkedHashSet<>();
        for (Player playerInst: players)
        {
            // We don't need to consider the set of possible moves for ourselves.
            if (playerInst.equals(aPlayer))
            {
               continue;
            }

            // Make a container to contain all possible moves that opponent(s) can take on with some piece
            List <Piece> playerInstPieces = playerInst.getPieces();
            for (Piece aPiece: playerInstPieces)
            {
                // Add all possible moves for a particular opponent piece to our container
               accessibleCoor.addAll(getPieceMoves(aPiece, playerInst));
            }
        }


        boolean safe = !accessibleCoor.contains(coorOfKing);
        return safe;
    }

    public boolean isChecked(Player aPlayer)
    {
        return !(isBoardLayoutSafe(aPlayer));
    }



}



