import dominos.Board;
import dominos.Case;
import dominos.Direction;
import dominos.Piece;
//import sun.jvm.hotspot.debugger.cdbg.EnumType;

import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        System.out.println("Domino's Piece");
        Piece p = new Piece();
        System.out.println(p.toString('+'));

        System.out.println("Domino's Case");
        Case c = new Case(2,3);
        System.out.println(c);

        System.out.println("Domino's Board");
        Board board = new Board(5);
        board.getCase(2,3).put(p);
        System.out.println(board);

    }


}
