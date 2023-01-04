import dominos.gui.InitWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {
    JButton btnDominos = new JButton("Domino's");
    JButton btnCarcassonne = new JButton("Carcassonne");

    public HomePage() {
        super();
        this.setSize(300,300);
        setLocation(100,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set main layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.add(layout, BorderLayout.CENTER);
        this.add(panel);

        JPanel mainPanel = new JPanel(new GridLayout(2,1,5,5));
        mainPanel.add(btnDominos);
        mainPanel.add(btnCarcassonne);
        layout.add(mainPanel);

        btnDominos.addActionListener(this::btnDominos_onClick);
        btnCarcassonne.addActionListener(this::btnCarcassonne_onClick);
    }


    public void btnDominos_onClick(ActionEvent e) {
        InitWindow window = new InitWindow(this);
        window.setVisible(true);
        this.setVisible(false);
    }

    public void btnCarcassonne_onClick(ActionEvent e) {
        carcassonne.view.InitWindow window = new carcassonne.view.InitWindow(this);
        window.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        (new HomePage()).setVisible(true);
    }
}
