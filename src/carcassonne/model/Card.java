package carcassonne.model;

import dominos.model.Piece;

public class Card extends Piece {
    Landscape landscape;
    int rotation;

    public Card(Landscape landscape) {
        super(landscape);
        this.landscape = landscape;
        rotation = 0;
    }

    public String getImagePath() {
        return "img/cards/" + landscape.getImgName() + ".jpg";
    }

    @Override
    public Piece turnLeft() {
        super.turnLeft();
        rotation = (rotation - 90) % 360;
        return this;
    }

    @Override
    public Piece turnRight() {
        super.turnRight();
//        rotation = (rotation + 90) % 360;
//        System.out.println("TrunRight" + rotation);
        return this;
    }

    public int getRotationRadio() {
        return this.rotation;
    }
}
