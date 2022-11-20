package dominos;

import java.util.LinkedList;
import java.util.Random;

public class Tuile {
    LinkedList<LinkedList> tuile;
    //Constructing a piece having 4 sides with 3 numbers in each as a matrix of 4x3
    public Tuile(){
        //Create a piece
        this.tuile = new LinkedList<LinkedList>();

        //Create 4 sides and insert into a piece
        LinkedList<Integer> a = new LinkedList<Integer>();
        LinkedList<Integer> b = new LinkedList<Integer>();
        LinkedList<Integer> c = new LinkedList<Integer>();
        LinkedList<Integer> d = new LinkedList<Integer>();

        //Randomize 3 numbers in each side
        Random rand = new Random();
        for (int j = 0; j < 3; j++){
            a.add(rand.nextInt(5));
            b.add(rand.nextInt(5));
            c.add(rand.nextInt(5));
            d.add(rand.nextInt(5));
        }

        //Insert into a piece
        this.tuile.add(a);
        this.tuile.add(b);
        this.tuile.add(c);
        this.tuile.add(d);
    }

    //Methods for a piece
    //1. Getting a side from index
    public LinkedList getCoteAt(int i){
        return this.tuile.get(i);
    }
    // Getting value of a side from index
    public Integer getValueCoteAt(int i){
        LinkedList<Integer> value = this.getCoteAt(i);
        Integer total = 0;
        for (Integer j : value){
            total += j;
        }
        return total;
    }

    //2. Turning a piece left or right
    public LinkedList<LinkedList> tournerGauche(Tuile tuile){
        LinkedList<LinkedList> tuileG = new LinkedList<LinkedList>();
        tuileG.add(tuile.getCoteAt(1));
        tuileG.add(tuile.getCoteAt(2));
        tuileG.add(tuile.getCoteAt(3));
        tuileG.add(tuile.getCoteAt(0));
        return tuileG;
    }
    public LinkedList<LinkedList> tournerDroite(Tuile tuile){
        LinkedList<LinkedList> tuileD = new LinkedList<LinkedList>();
        tuileD.add(tuile.getCoteAt(3));
        tuileD.add(tuile.getCoteAt(0));
        tuileD.add(tuile.getCoteAt(1));
        tuileD.add(tuile.getCoteAt(2));
        return tuileD;
    }

    //TODO 3. Comparing sides for placement, need tweaking because it is not usable now. Needs to compare opposite sides
    public boolean comparerCote(Tuile t1, Tuile t2, int i){
        if (i == 0){
            return t1.getCoteAt(0) == t2.getCoteAt(2);
        }
        else if (i == 1){
            return t1.getCoteAt(1) == t2.getCoteAt(3);
        }
        else if (i == 2){
            return t1.getCoteAt(2) == t2.getCoteAt(0);
        }
        else if (i == 3) {
            return t1.getCoteAt(3) == t2.getCoteAt(1);
        }
        return false;
    }

    //TODO 4. Print out a piece, make it a square. But might have a problem.
    public void print() {
        System.out.println("x" + tuile.get(0) + "x");
        System.out.println(tuile.get(3).get(2) + "xxx" + tuile.get(1).get(0));
        System.out.println(tuile.get(3).get(1) + "xxx" + tuile.get(1).get(1));
        System.out.println(tuile.get(3).get(0) + "xxx" + tuile.get(1).get(2));
        System.out.println("x" + tuile.get(2).get(2) + tuile.get(2).get(1) + tuile.get(2).get(0) + "x");
    }
}

