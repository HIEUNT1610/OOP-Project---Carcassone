package dominos.gui;

import dominos.model.AIPlayer;
import dominos.model.HumanPlayer;
import dominos.model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * A Dialog to specialize the type of those players (human or AI)
 */
public class PlayersTypeWindow extends JDialog {

    JButton btnValide = new JButton("Valide");
    Player[] players;
    boolean[] isHuman;
    JComboBox<?>[] type;

    public PlayersTypeWindow(JFrame owner, Player[] players) {
        super(owner, "Players type", true);
        this.players = players;
        this.setSize(200,50*(players.length+1));
        setLocation(350,250);

        Container panel = this.getContentPane();
        panel.setLayout(new BorderLayout());

        // players
        isHuman = new boolean[players.length];
        type = new JComboBox[players.length];
        JPanel playersPanel = new JPanel(new GridLayout(players.length, 2));
        for(int i=0; i<players.length; i++)  {
            type[i] = new JComboBox<>(new String[]{"Human", "AI"});
            type[i].setSelectedIndex(players[i] instanceof HumanPlayer ? 0 : 1);
            playersPanel.add(new JLabel("Player "+ i));
            playersPanel.add(type[i]);
        }
        panel.add(playersPanel, BorderLayout.CENTER);
        playersPanel.setBorder(new EmptyBorder(5,5,5,5));

        // add button
        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(btnValide);
        btnValide.addActionListener(this::btnValide_onClick);
    }

    public void btnValide_onClick(ActionEvent e) {
        for (int i = 0; i < type.length; i++) {
            if(type[i].getSelectedIndex()==0) {
                players[i] = new HumanPlayer();
            }else{
                players[i] = new AIPlayer();
            }
        }
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }

}
