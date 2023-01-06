package dominos.model;

import java.util.*;

public class Bag {
    protected LinkedList<Piece> bag;

    /**
     * Constructing a Sac with nbPiece number of pieces
     */
    public Bag(int nbPiece){
        this.bag = new LinkedList<Piece>();
        for (int i = 0; i < nbPiece; i++){
            Piece piece = new Piece();
            this.bag.add(piece);
        }
        this.shuffle();
    }

    public Bag(){this.bag = new LinkedList<>();}

    public void shuffle(){
        Collections.shuffle(this.bag);
    }

    public Piece drawPiece(){
        try{
            return bag.pop();
        }catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean hasPiece() {
        return bag.size() > 0;
    }
}

