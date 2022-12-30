package dominos.model;

import dominos.view.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    @Override
    public Case choose(Case[] choises) {
        return null;
    }

    public int readInt(int bound) {
        return readInt("Your choise");
    }

    public int readInt(String hint) {
        Scanner sc = new Scanner(System.in);
        System.out.print(hint + " : ");
        return sc.nextInt();
    }

    /**
     * check if the input choise is in the possible choises
     * @param c input choise from user
     * @param choise all the possible choise
     * @return
     */
    public boolean isValide(char c, List<Character> choise) {
        boolean res = false;
        for (char c1 : choise) {
            res |= c1==c;
        }
        return res;
    }

    public void action(Board board, Piece p) {

        String hint = "a. abandon\nl. turn left\nr. turn right";
        List<Character> choices = new LinkedList<>();
        choices.add('a');
        char choice = ' ';
        while(!isValide(choice, choices)){
            System.out.println(hint);
            Case[] availableCases = board.getAvailableCases(p);
            if(availableCases.length==0){
                System.out.println("You have no place to put down");
            }else{
                for (int i = 0; i < availableCases.length; i++) {
                    choices.add((char)(i+'0'));
                    System.out.printf("%d. (%d, %d)%n", i,  availableCases[i].getX(), availableCases[i].getY());
                }
            }
            choice = Utils.readKey();
            switch (choice) {
                case 'a': break;
                case 'l':
                    p.turnLeft();
                    System.out.println(p);
                    break;
                case 'r':
                    p.turnRight();
                    System.out.println(p);
                    break;
            }
        }




    }
}
