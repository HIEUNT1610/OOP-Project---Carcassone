package dominos.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Case[][] board;
    private int nbPiece;

    /**
     * Constructor.
     */
    public Board(int nbPiece, Piece initPiece){
        this.nbPiece = nbPiece;
        //Creating a board size nbPiecex2.
        board = new Case[nbPiece*2][nbPiece*2];
        //Fill the board with cases.
        for (int x = 0; x < nbPiece*2; x++) {
            for (int y = 0; y < nbPiece*2; y++) {
                board[x][y] = new Case(this, x, y);
            }
        }
        board[nbPiece-1][nbPiece-1].put(initPiece);
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
                if (!board[i][j].isOccupied()
                        && board[i][j].isConnected(this)
                        && canPut(p, board[i][j])) {
                    res.add(board[i][j]);
                }
            }
        }
        return res.toArray(new Case[0]);
    }

    /**
     * Check if the piece cen be put in given case.
     * @param p
     * @param c
     * @return
     */
    public boolean canPut(Piece p, Case c) {
        boolean res = true;
        for (Direction d : Direction.values()) {
            Case comparedCase = c.getNeighbour(this, d);
            if(comparedCase.isOccupied()){
                res = res & p.compareSide(comparedCase.getOccupyingPiece(), d);
            }
        }
        return res;
    }

    public Case getCase(int i, int j) { return board[i][j]; }
    public int getNbPiece() {return nbPiece;}
    public boolean isCaseOccupied(int i, int j) { return getCase(i,j).isOccupied();}


    public int getUpLimit() {
        int up = nbPiece*2-1;
        for(int j=0; j<nbPiece*2; j++) {
            int i = 0;
            while( i < nbPiece*2 && !board[i][j].isOccupied()) {
                i++;
            }
            up = Math.min(up, i);
        }
        return up;
    }

    public int getLeftLimit() {
        int left = nbPiece*2-1;
        for(int i=0; i<nbPiece*2; i++) {
            int j = 0;
            while( j < nbPiece*2 && !board[i][j].isOccupied()) {
                j++;
            }
            left = Math.min(left, j);
        }
        return left;
    }

    public int getBottomLimit() {
        int bottom = 0;
        for(int j=0; j<nbPiece*2; j++) {
            int i = nbPiece*2-1;
            while( i >= 0 && !board[i][j].isOccupied()) {
                i -= 1;
            }
            bottom = Math.max(bottom, i);
        }
        return bottom;
    }

    public int getRighttLimit() {
        int right = 0;
        for(int i=0; i<nbPiece*2; i++) {
            int j = nbPiece*2-1;
            while( j >= 0 && !board[i][j].isOccupied()) {
                j --;
            }
            right = Math.max(right, j);
        }
        return right;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i=0; i<nbPiece*2; i++){
            List<String[]> oneLine = new ArrayList<>();
            for(int j=0; j<nbPiece*2; j++) {
                String[] tmp = board[i][j].toString().split("\\n");
                oneLine.add(tmp);
            }
            for(int k=0; k<5; k++) {
                for(int j=0; j<nbPiece*2; j++) {
                    res.append(oneLine.get(j)[k]).append("   ");
                }
                res.append("\n");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public String toString(int up, int left, int bottom, int right) {
        up = Math.max(up, 0);
        left = Math.max(left, 0);
        bottom = Math.min(bottom, nbPiece*2-1);
        right = Math.min(right, nbPiece*2-1);
        StringBuilder res = new StringBuilder();
        for(int i=up; i<=bottom; i++){
            List<String[]> oneLine = new ArrayList<>();
            for(int j=left; j<=right; j++) {
                String[] tmp = board[i][j].toString().split("\\n");
                oneLine.add(tmp);
            }
            for(int k=0; k<5; k++) {
                for(int j=left; j<=right; j++) {
                    res.append(oneLine.get(j-left)[k]).append("   ");
                }
                res.append("\n");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public String toAdaptedString() {
        return toString(
                getUpLimit()-1,
                getLeftLimit()-1,
                getBottomLimit()+1,
                getRighttLimit()+1
        );
    }

























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
        Piece currPiece = this.board[x][y].getOccupyingPiece();
        if (!board[x][y+1].isOccupied()
                && (board[x][y+1].getOccupyingPiece().compareSide(currPiece, Direction.LEFT))) {
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
