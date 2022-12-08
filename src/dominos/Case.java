package dominos;

import javax.swing.border.Border;

public class Case {
    private Board board;
    private Piece occupyingPiece;
    private int xNum;
    private int yNum;

    /**
     * Constructor
     */
    public Case(Board board,int xNum, int yNum) {
        this.board = board;
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
     * Put a piece in a case
     * @param p The piece taken from the sac in this turn
     */
    public void put(Piece p) {
        this.occupyingPiece = p;
        p.setPosition(this);
    }

    // in fact I want to remove the field `board` to avoid the cycle, and try to pass it by parameter
    // and to reduce the number of field
    /**
     * test if this case is connected with the "main land", or just alone without connection with any occupied case
     * @param board the whole board
     * @return
     */
    public boolean isConnected(Board board) {
        boolean res = board.isCaseOccupied(xNum-1, yNum-1);
        res |= board.isCaseOccupied(xNum-1, yNum+1);
        res |= board.isCaseOccupied(xNum+1, yNum-1);
        res |= board.isCaseOccupied(xNum+1, yNum+1);
        return res;
    }

}
