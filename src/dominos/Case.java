package dominos;

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

}
