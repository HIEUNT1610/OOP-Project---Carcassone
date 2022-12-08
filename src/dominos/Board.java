package dominos;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Case[][] board;
    private int nbPiece;

    /**
     * Constructor.
     */
    public Board(int nbPiece){
        this.nbPiece = nbPiece;
        //Creating a board size nbPiecex2.
        board = new Case[nbPiece*2][nbPiece*2];
        //Fill the board with cases.
        for (int x = 0; x < nbPiece*2; x++) {
            for (int y = 0; y < nbPiece*2; y++) {
                board[x][y] = new Case(this, x, y);
            }
        }
    }

    /**
     * get all the cases that available to put by given piece `p`
     * @param p the piece holden by the player
     * @return
     */
    public Case[] getAvailableCases(Piece p) {
        ArrayList<Case> res = new ArrayList<>();
        for (int i=0; i<nbPiece*2; i++) {
            for (int j=0; j<nbPiece*2; j++) {
                if (board[i][j].isConnected(this)) {
                    // TODO
                }
            }
        }
        return res.toArray(new Case[0]);
    }

    public Case getCase(int i, int j) { return board[i][j]; }
    public boolean isCaseOccupied(int i, int j) { return getCase(i,j).isOccupied();}

    /**
     * Check if cases next to the case at (x,y) is occupied or not
     * @param x
     * @param y
     * @return list of empty cases next to (x,y)
     */
    public List<Case> nextEmpty(int x, int y){
        List<Case> notOccupied = new ArrayList<>();
        if (!board[x][y+1].isOccupied()) {
            notOccupied.add(board[x][y+1]);
        }
        else if (!board[x][y-1].isOccupied()) {
            notOccupied.add(board[x][y-1]);
        }
        else if (!board[x-1][y].isOccupied()) {
            notOccupied.add(board[x-1][y]);
        }
        else if (!board[x+1][y].isOccupied()) {
            notOccupied.add(board[x+1][y]);
        }
        return notOccupied;
        //this method is just to show a list empty cases that
        //may be playable, so that the player can choose to
        //put the current piece.
    }
    //I am thinking about if having separate empty check and playable
    //check is feasible or not. It probably not, and should all be in
    //the Game class instead.
    public List<Case> nextPlayable(int x, int y){
        List<Case> playble = new ArrayList<>();
        if (!board[x][y+1].isOccupied()
                && (board[x][y].getOccupyingPiece().compareSide(currPiece))) {
            playble.add(board[x][y+1]);
        }
        else if (!board[x][y-1].isOccupied()) {
            playble.add(board[x][y-1]);
        }
        else if (!board[x-1][y].isOccupied()) {
            playble.add(board[x-1][y]);
        }
        else if (!board[x+1][y].isOccupied()) {
            playble.add(board[x+1][y]);
        }
        return playble;
    }

}
