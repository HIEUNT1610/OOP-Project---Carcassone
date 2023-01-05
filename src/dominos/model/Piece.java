package dominos.model;

import carcassonne.model.Landscape;
import carcassonne.model.Side;

import java.util.Random;

public class Piece {

    int[][] pieceNumber;
    private Case currentCase;

    /**
     * Constructor
     */
    public Piece() {
        // allocate the array
        this.pieceNumber = new int[4][3];
        // create a Random object
        Random random = new Random();
        // generate random numbers to each side of piece
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                pieceNumber[i][j] = random.nextInt(5);
            }
        }
    }

    public Piece(Landscape landscape) {
        // allocate the array
        this.pieceNumber = new int[4][3];
        Side[] sides = landscape.getSides();
        // generate random numbers to each side of piece
        for(int i = 0; i < 4; i++){
            switch (sides[i]) {
                case LAWN:
                    pieceNumber[i] = new int[]{0,0,0};
                    break;
                case ROAD:
                    pieceNumber[i] = new int[]{0,1,0};
                    break;
                case TOWN:
                    pieceNumber[i] = new int[]{0,2,0};
                    break;
            }
        }
    }

    /**
     * Get the numbers of one side through the direction.
     * @param direction
     * @return
     */
    public int[] getSideAt(Direction direction) {
        return pieceNumber[direction.ordinal()];
    }

    /**
     * Get the sum of numbers of one side through the direction.
     * @param direction
     * @return
     */
    public int getSideSumAt(Direction direction) {
        int sum = 0;
        int[] side = getSideAt(direction);
        for (int item: side) { sum += item; }
        return sum;
    }

    /**
     * Turn left the piece.
     * @return
     */
    public Piece turnLeft() {
        int[] temp = this.getSideAt(Direction.LEFT).clone();
        pieceNumber[Direction.LEFT.ordinal()] = this.getSideAt(Direction.TOP).clone();
        pieceNumber[Direction.TOP.ordinal()] = this.getSideAt(Direction.RIGHT).clone();
        pieceNumber[Direction.RIGHT.ordinal()] = this.getSideAt(Direction.BOTTOM).clone();
        pieceNumber[Direction.BOTTOM.ordinal()] = temp;
        return this;
    }

    /**
     * Turn right the piece.
     * @return
     */
    public Piece turnRight() {
        for(int i = 0; i < 3; i++){ this.turnLeft(); }
        return this;
    }

    /**
     * Verify whether the numbers of the two sides are equal.
     * @param side1
     * @param side2
     * @return
     */
    private boolean equalSide(int[] side1, int[] side2) {
        for(int i = 0; i < 3; i++){
            if(side1[i] != side2[2 - i])
                return false;
        }
        return true;
    }

    /**
     * Compare itself with another piece on the side of `direction` of itself.
     * @param piece
     * @param direction
     * @return
     */
    public boolean compareSide(Piece piece, Direction direction) {
        return equalSide(this.getSideAt(direction), piece.getSideAt(direction.getOpposite()));
    }

    @Override
    public String toString() {
        return toString('*');
    }

    public String toString(char c) {
        StringBuilder res = new StringBuilder();
        int[] top = this.getSideAt(Direction.TOP);
        int[] left = this.getSideAt(Direction.LEFT);
        int[] right = this.getSideAt(Direction.RIGHT);
        int[] bottom = this.getSideAt(Direction.BOTTOM);
        res.append(c).append(" ").append(top[0]).append(" ").append(top[1]).append(" ").append(top[2]).append(" ").append(c).append("\n");
        res.append(left[2]).append(" ").append(c).append(" ").append(c).append(" ").append(c).append(" ").append(right[0]).append("\n");
        res.append(left[1]).append(" ").append(c).append(" ").append(c).append(" ").append(c).append(" ").append(right[1]).append("\n");
        res.append(left[0]).append(" ").append(c).append(" ").append(c).append(" ").append(c).append(" ").append(right[2]).append("\n");
        res.append(c).append(" ").append(bottom[2]).append(" ").append(bottom[1]).append(" ").append(bottom[0]).append(" ").append(c).append("\n");
        return res.toString();
    }

    /**
     * Display this piece in command line.
     */
    public void display() {
        System.out.println(this);
    }

    /**
     * Methods for position
     */
    public Case getPosition() {
        return currentCase;
    }
    public void setPosition(Case cas) {
        this.currentCase = cas;
    }
}
