package carcassonne.model;

public enum Landscape {
    A(9, Side.LAWN, Side.ROAD, Side.ROAD, Side.LAWN),
    B(3, Side.TOWN, Side.LAWN, Side.ROAD, Side.ROAD),
    C(2, Side.TOWN, Side.TOWN, Side.ROAD, Side.ROAD),
    D(1, Side.TOWN, Side.TOWN, Side.ROAD, Side.TOWN),
    E(1, Side.TOWN, Side.TOWN, Side.LAWN, Side.TOWN),
    F(3, Side.TOWN, Side.TOWN, Side.ROAD, Side.ROAD),
    G(3, Side.TOWN, Side.ROAD, Side.ROAD, Side.ROAD),
    H(8, Side.ROAD, Side.LAWN, Side.ROAD, Side.LAWN),
    I(4, Side.LAWN, Side.ROAD, Side.ROAD, Side.ROAD),
    J(5, Side.TOWN, Side.LAWN, Side.LAWN, Side.LAWN),
    K(2, Side.TOWN, Side.LAWN, Side.LAWN, Side.TOWN),
    L(3, Side.TOWN, Side.TOWN, Side.LAWN, Side.TOWN),
    M(4, Side.LAWN, Side.LAWN, Side.LAWN, Side.LAWN),
    N(2, Side.LAWN, Side.LAWN, Side.ROAD, Side.LAWN),
    O(3, Side.TOWN, Side.TOWN, Side.LAWN, Side.LAWN),
    P(2, Side.LAWN, Side.TOWN, Side.LAWN, Side.TOWN),
    Q(4, Side.TOWN, Side.ROAD, Side.LAWN, Side.ROAD),
    R(3, Side.TOWN, Side.ROAD, Side.ROAD, Side.LAWN),
    S(1, Side.LAWN, Side.TOWN, Side.LAWN, Side.TOWN),
    T(2, Side.TOWN, Side.TOWN, Side.ROAD, Side.TOWN),
    U(1, Side.TOWN, Side.TOWN, Side.TOWN, Side.TOWN),
    V(1, Side.ROAD, Side.ROAD, Side.ROAD, Side.ROAD),
    W(2, Side.TOWN, Side.TOWN, Side.LAWN, Side.LAWN),
    X(3, Side.LAWN, Side.TOWN, Side.LAWN, Side.TOWN);


    private final Side[] sides;
    private final int number;

    Landscape(int number, Side top, Side left, Side button, Side right){
        this.number = number;
        sides = new Side[]{top, left, button, right};
    }

    public Side[] getSides() {
        return this.sides;
    }

    public int getNumber() {return number;}

    public String getImgName() {
        return (char)(this.ordinal() + 'A') + "";
    }
}
