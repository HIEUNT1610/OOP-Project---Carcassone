package dominos.view;

import java.util.Scanner;

public class Utils {
    public static char readKey() {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()) {
            String line = sc.next();
            if(line.length()>1)
                return ' ';
            return line.charAt(0);
        }
        return ' ';
    }

    public static int readInt() {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()) {
            return sc.nextInt();
        }
        return -1;
    }

    public static double readDouble() {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextDouble()) {
            return sc.nextDouble();
        }
        return -1;
    }

    public static void pressToContinue(){
        System.out.println("-------------");
        System.out.print("c to continue\n> ");
        while(readKey() != 'c'){
            System.out.print("(c to continue)> ");
        }
    }

    public static char getUpperCase(char c) {
        return c>=97? (char)(c-32) : c;
    }
}
