package dominos.model;

import java.util.Random;

public class AIPlayer extends Player {

    @Override
    public Case choose(Case[] choises) {
        Random r = new Random();
        int index = r.nextInt(choises.length);
        return choises[index];
    }

    public void action(Board board, Piece p) {
        for(int i=0; i<4; i++) {
            Case[] availableCases = board.getAvailableCases(p);
            if(availableCases.length!=0){
                score += choose(availableCases).put(p);
                return;
            }
            p.turnLeft();
        }
    }
}
