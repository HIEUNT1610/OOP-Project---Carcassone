package dominos.model;

public class Case {
    private Board board;
    private Piece occupyingPiece;
    private final int row;
    private final int col;

    public Case(Board board, int row, int col) {
        this.board = board;
        this.row = row;
        this.col = col;
        this.occupyingPiece = null;
    }

    /**
     * Methods related to occupying piece
     * @return occupying Piece
     */
    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    /**
     * Checking if case is occupied
     * @return value True if it is occupied
     */
    public boolean isOccupied() {
        return (this.occupyingPiece != null);
    }

    /**
     * Coordinate X getter
     */
    public int getRow() {
        return this.row;
    }
    /**
     * Coordinate Y getter
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Get the neighbour case of current case by given <code>direction</code>.
     * @param board The board
     * @param direction The direction
     * @return The case object
     */
    public Case getNeighbour(Board board, Direction direction) {
        if(direction == Direction.TOP)
            return board.getCase(this.row - 1, this.col);
        if(direction == Direction.BOTTOM)
            return board.getCase(this.row + 1, this.col);
        if(direction == Direction.LEFT)
            return board.getCase(this.row, this.col - 1);
        if(direction == Direction.RIGHT)
            return board.getCase(this.row, this.col + 1);
        return null;
    }

    /**
     * Put a piece in a case, and return the score
     * @param p The piece taken from the sac in this turn
     */
    public int put(Piece p) {
        this.occupyingPiece = p;
        p.setPosition(this);
        // calculate the score
        int score = 0;
        for(Direction d : Direction.values()) {
            Case c = getNeighbour(board, d);
            if(c.isOccupied()) {
                score += p.getSideSumAt(d);
            }
        }
        return score;
    }

    /**
     * test if this case is connected with the "main land", or just alone without connection with any occupied case
     *
     * @return A boolean
     */
    public boolean isConnected() {
        boolean res = false;
        if(row != 0)
            res = board.isCaseOccupied(row - 1, col);
        if(row != board.getNbPiece()*2-1)
            res |= board.isCaseOccupied(row +1, col);
        if(col != 0)
            res |= board.isCaseOccupied(row, col -1);
        if(col != board.getNbPiece()*2-1)
            res |= board.isCaseOccupied(row, col +1);
        return res;
    }

    @Override
    public String toString() {
        if(isOccupied()){
            return getOccupyingPiece().toString('+');
        }
        StringBuilder res = new StringBuilder("* * * * *");
        res.append("\n* * * * *");
        if(row <10){
            res.append("\n* (").append(row).append(",");
        }else{
            res.append("\n*(").append(row).append(",");
        }
        if(col <10) {
            res.append(col).append(") *");
        }else{
            res.append(col).append(")*");
        }
        for(int i=0; i<2; i++) {
            res.append("\n* * * * *");
        }
        return res.toString();
    }
}
