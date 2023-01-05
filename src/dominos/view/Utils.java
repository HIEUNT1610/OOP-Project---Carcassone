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
}
