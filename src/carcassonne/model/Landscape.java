package carcassonne.model;

public enum Landscape {
    A(9, Side.LAWN, Side.ROAD, Side.ROAD, Side.LAWN),
    B(3, Side.TOWN, Side.LAWN, Side.ROAD, Side.ROAD),
    C(2, Side.TOWN, Side.TOWN, Side.ROAD, Side.ROAD),
    D(1, Side.TOWN, Side.TOWN, Side.ROAD, Side.TOWN)
//    E(1, Side.TOWN, Side.TOWN, )


    private Side[] sides;
    private int number;

    Landscape(int number, Side top, Side left, Side button, Side right){
        this.number = number;
        sides = new Side[]{top, left, button, right};
    }

    public int getNumber() {return number;}
}
