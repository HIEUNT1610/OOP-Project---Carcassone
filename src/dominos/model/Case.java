package dominos.model;

import dominos.Direction;

public class Case {
    private Board board;
    private Piece occupyingPiece;
    private int xNum;
    private int yNum;

    /**
     * Constructor
     */
    public Case(Board board, int xNum, int yNum) {
        this.board = board;
        this.xNum = xNum;
        this.yNum = yNum;
        this.occupyingPiece = null;
    }

    public Case(Board board, int xNum, int yNum, Piece piece) {
        this.board = board;
        this.xNum = xNum;
        this.yNum = yNum;
        this.occupyingPiece = piece;
    }

    public Case(int xNum, int yNum) {
        this.board = null;
        this.xNum = xNum;
        this.yNum = yNum;
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
    public int getX() {
        return this.xNum;
    }
    /**
     * Coordinate Y getter
     */
    public int getY() {
        return this.yNum;
    }

    /**
     * Get the neighbour case of current case by given direction.
     * @param board
     * @param direction
     * @return
     */
    public Case getNeighbour(Board board, Direction direction) {
        if(direction == Direction.TOP)
            return board.getCase(this.xNum - 1, this.yNum);
        if(direction == Direction.BOTTOM)
            return board.getCase(this.xNum + 1, this.yNum);
        if(direction == Direction.LEFT)
            return board.getCase(this.xNum, this.yNum - 1);
        if(direction == Direction.RIGHT)
            return board.getCase(this.xNum, this.yNum + 1);
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

    // in fact I want to remove the field `board` to avoid the cycle, and try to pass it by parameter
    // and to reduce the number of field
    /**
     * test if this case is connected with the "main land", or just alone without connection with any occupied case
     * @param board the whole board
     * @return
     */
    public boolean isConnected(Board board) {
        boolean res = false;
        if(xNum != 0)
            res |= board.isCaseOccupied(xNum - 1, yNum);
        if(xNum != board.getNbPiece()*2-1)
            res |= board.isCaseOccupied(xNum+1, yNum);
        if(yNum != 0)
            res |= board.isCaseOccupied(xNum, yNum-1);
        if(yNum != board.getNbPiece()*2-1)
            res |= board.isCaseOccupied(xNum, yNum+1);
        return res;
    }

    @Override
    public String toString() {
        if(isOccupied()){
            return getOccupyingPiece().toString('+');
        }
        StringBuilder res = new StringBuilder("* * * * *");
        res.append("\n* * * * *");
        if(xNum<10){
            res.append("\n* (").append(xNum).append(",");
        }else{
            res.append("\n*(").append(xNum).append(",");
        }
        if(yNum<10) {
            res.append(yNum).append(") *");
        }else{
            res.append(yNum).append(")*");
        }
        for(int i=0; i<2; i++) {
            res.append("\n* * * * *");
        }
        return res.toString();
    }
}
