package dominos.gui;

import dominos.model.Bag;
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
    JSpinner inputNbPiece = new JSpinner(new SpinnerNumberModel(20,5,50,1));
    JButton btnSetPlayers = new JButton("Set Players");
    JButton btnStart = new JButton("Start");
    int nbPlayer = 2;
    Player[] players = new Player[2];

    public InitWindow(JFrame homepage) {
        super();
        this.setTitle("Settings for Domino's");
        this.setSize(300,300);
        this.setLocation(120,120);
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
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        layout.add(mainPanel);

        // number of pieces
        mainPanel.add(new JLabel("Number of pieces"));
        mainPanel.add(inputNbPiece);

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
        btnSetPlayers.addActionListener(this::btnSetPlayers_onClick);

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

    public void btnSetPlayers_onClick(ActionEvent e){
        PlayersTypeWindow window = new PlayersTypeWindow(this, players);
        window.setVisible(true);
    }

    public void selectNbPlayer_OnChange(ActionEvent e) {
        nbPlayer = selectNbPlayer.getSelectedIndex() + 2;
        players = new Player[nbPlayer];
        for(int i=0; i<nbPlayer; i++) {
            // human player as default
            players[i] = new HumanPlayer();
        }
    }

    public void start() {
        int nbPiece = (Integer) inputNbPiece.getValue();
        Bag bag = new Bag(nbPiece);
        Board board = new Board(nbPiece, bag.drawPiece());
        new MainWindow(this, bag, board, players);
    }

}
