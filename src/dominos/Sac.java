package dominos;

import java.util.ArrayList;
import java.util.Collections;

public class Sac {
    ArrayList<Tuile> sac;

    //Constructing a Sac with nbTuile number of pieces
    public Sac(int nbTuile){
        this.sac = new ArrayList<Tuile>(nbTuile);
        for (int i = 0; i < nbTuile+1; i++){
            Tuile tuile = new Tuile();
            this.sac.add(tuile);
        }
    }

    //Methods for Sac
    //1. Shuffle the sac. Collections class method allows directly shuffling the content of the sac
    public void melanger(){
        Collections.shuffle(this.sac);
    }
    //2. Taking pieces from the sac. Using i as the index, as the game is implied to start at turn 0 (picking the first
    //piece from the sac)
    public Tuile piocherTuile(int i){
        return this.sac.get(i);
    }
}

