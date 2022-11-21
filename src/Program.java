import dominos.Direction;
import dominos.Piece;
import sun.jvm.hotspot.debugger.cdbg.EnumType;

import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        System.out.println("Domino's");
        Piece p = new Piece();
        p.display();
    }

    public static void error1() {
        // about Tuile.comparerCote()
        List<Integer> a = new LinkedList<Integer>();
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(2);
        List<Integer> b = new LinkedList<Integer>();
        b.add(1);
        b.add(1);
        b.add(2);
        b.add(2);
        System.out.println(a==b); // false
    }
}
