package dominos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    List<Piece> bag;

    /**
     * Constructing a Sac with nbPiece number of pieces
     */
    public Bag(int nbPiece){
        this.bag = new ArrayList<Piece>(nbPiece);
        for (int i = 0; i < nbPiece+1; i++){
            Piece piece = new Piece();
            this.bag.add(piece);
        }
    }

    //Methods for Bag
    //1. Shuffle the bag. Collections class method allows directly shuffling the content of the sac
    public void shuffle(){
        Collections.shuffle(this.bag);
    }
    //2. Taking pieces from the bag. I think the index should always
    //be 0, because we remove the piece from the bag everytime we draw.
    public Piece drawPiece(){
        Piece currPiece = this.bag.get(0);
        this.bag.remove(0);
        return currPiece;
    }
}

