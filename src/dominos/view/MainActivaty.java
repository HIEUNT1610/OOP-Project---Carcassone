package dominos.view;

import dominos.model.*;

public class MainActivaty {
    Board board;
    Bag bag;
    int nbPlayer;
    Player[] players;

    public void init() {
        System.out.print("How many players? > ");
        nbPlayer = Utils.readInt();
        while(nbPlayer < 0){
            System.out.print("(The value should be positive Integer) > ");
            nbPlayer = Utils.readInt();
        }
        players = new Player[nbPlayer];
        for(int i=0; i<nbPlayer; i++){
            System.out.println("For player " + i);
            System.out.print("AI(a) or human(h)? > ");
            char c = Utils.readKey();
            while(c != 'a' && c != 'h') {
                System.out.print("(a or h) > ");
                c = Utils.readKey();
            }
            if(c=='a') {
                players[i] = new AIPlayer();
            } else {
                players[i] = new HumanPlayer();
            }
        }
        System.out.print("How many pieces? > ");
        int nbPiece = Utils.readInt();
        while(nbPiece < 0){
            System.out.print("(The value should be positive) > ");
            nbPiece = Utils.readInt();
        }
        bag = new Bag(nbPiece);
        board = new Board(nbPiece, bag.drawPiece());
        board.getCase(nbPiece-1, nbPiece-1).put(new Piece());
        System.out.println(board.toAdaptedString());
    }

    public void start() {
        init();
        int round = 0;
        Piece currentPiece;
        while((currentPiece = bag.drawPiece()) != null) {
            System.out.println("For player "+ round);
            System.out.println(currentPiece);
//            Case[] availableCases = board.getAvailableCases(currentPiece);
            players[round].action(board, currentPiece);

            round = (round+1) % nbPlayer;
            System.out.println(board.toAdaptedString());
        }
        for(Player player : players){
            System.out.println(player);
        }
    }
}
