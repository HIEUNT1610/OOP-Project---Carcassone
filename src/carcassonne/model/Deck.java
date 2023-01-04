package carcassonne.model;

import dominos.model.Bag;

import java.util.Collections;

public class Deck extends Bag {

    public Deck() {
        super();
        for(Landscape landscape : Landscape.values()) {
            for(int i=0; i<landscape.getNumber(); i++) {
                bag.add(new Card(landscape));
            }
        }
        // shuffle
        Collections.shuffle(bag);
    }

}
