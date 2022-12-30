package dominos.model;

import java.util.*;

public class Bag {
    LinkedList<Piece> bag;

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

    //Methods for Bag
    //1. Shuffle the bag. Collections class method allows directly shuffling the content of the sac
    public void shuffle(){
        Collections.shuffle(this.bag);
    }
    //2. Taking pieces from the bag. I think the index should always
    //be 0, because we remove the piece from the bag everytime we draw.
    public Piece drawPiece(){
//        Piece currPiece = this.bag.get(0);
//        this.bag.remove(0);
//        return currPiece;
        try{
            return bag.pop();
        }catch (NoSuchElementException e) {
            return null;
        }

    }
}

