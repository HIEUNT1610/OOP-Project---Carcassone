package carcassonne.view;

import carcassonne.model.Deck;
import dominos.gui.MainWindow;
import dominos.gui.PlayersTypeWindow;
import dominos.model.Board;
import dominos.model.HumanPlayer;
import dominos.model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InitWindow extends JFrame {
    JComboBox<Integer> selectNbPlayer = new JComboBox<>(new Integer[]{2,3,4,5});
//    JSpinner inputNbPiece = new JSpinner(new SpinnerNumberModel(20,5,50,1));
    JButton btnSetPlayers = new JButton("Set Players");
    JButton btnStart = new JButton("Start");
    int nbPlayer = 2;
    Player[] players = new Player[2];

    public InitWindow(JFrame homepage) {
        super();
        this.setTitle("Settings for Carcassonne");
        this.setSize(300,300);
        setLocation(120,120);
        for(int i=0; i<nbPlayer; i++) {
            players[i] = new HumanPlayer();
        }

        // set main layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.add(layout, BorderLayout.CENTER);
        this.add(panel);

        // create main panel as a grid layout
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        layout.add(mainPanel);

        // players
        mainPanel.add(new JLabel("Players"));
        mainPanel.add(new JLabel()); // placeholder
        // number of player
        mainPanel.add(new JLabel("Number of player"));
        mainPanel.add(selectNbPlayer);
        selectNbPlayer.addActionListener(this::selectNbPlayer_OnChange);
        // set player type
        mainPanel.add(new JLabel("Type of Players"));
        mainPanel.add(btnSetPlayers);
        btnSetPlayers.addActionListener(this::btnSetPlayers);

        // start
        JPanel startPanel = new JPanel();
        startPanel.add(btnStart);
        panel.add(startPanel, BorderLayout.SOUTH);
        btnStart.addActionListener(e -> start());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homepage.setVisible(true);
            }
        });
    }

    public void btnSetPlayers(ActionEvent e){
        PlayersTypeWindow window = new PlayersTypeWindow(this, players);
        window.setVisible(true);
    }

    public void selectNbPlayer_OnChange(ActionEvent e) {
        nbPlayer = selectNbPlayer.getSelectedIndex()+2;
        players = new Player[nbPlayer];
        for(int i=0; i<nbPlayer; i++) {
            players[i] = new HumanPlayer();
        }
    }

    public void start() {
        Deck bag = new Deck();
        Board board = new Board(72, bag.drawPiece());
        new MainWindow(this, bag, board, players);
    }
}
