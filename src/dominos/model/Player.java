package dominos.model;

public abstract class Player {

    int score = 0;

    public abstract Case choose(Case[] choises);

    public int getScore() { return this.score; }

    public void addScore(int s) { score += s; }

    public abstract void action(Board board, Piece p);

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                '}';
    }
}
