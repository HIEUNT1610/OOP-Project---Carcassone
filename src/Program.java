import java.awt.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("POOIG");
//        (new MainActivaty()).start();
        EventQueue.invokeLater(() -> (new HomePage()).setVisible(true));
    }



}
