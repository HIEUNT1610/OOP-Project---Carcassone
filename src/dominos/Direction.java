package dominos;

public enum Direction {
    TOP, LEFT, BOTTOM, RIGHT;

    public Direction getOpposite() {
        switch (this){
            case LEFT: return RIGHT;
            case TOP: return BOTTOM;
            case RIGHT: return LEFT;
            default: return TOP;
        }
    }
}
