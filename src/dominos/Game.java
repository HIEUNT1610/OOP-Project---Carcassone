package dominos;

import dominos.model.Bag;
import dominos.model.Board;
import dominos.model.Player;

public class Game {
    private Board board;
    private Bag bag;
    private Player[] players;

    public Game(int nbPlayer) {
        players = new Player[nbPlayer];

    }

    //The turn system probably needs to be in here.

    //The nextPlayble method should probably be in here too,
    //so that it can take advantage of currPiece to compare.

    public void start() {

    }
}
