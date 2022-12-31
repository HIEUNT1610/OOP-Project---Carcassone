package dominos.gui;

import dominos.model.*;
import dominos.view.MainActivaty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    JFrame parent;
    JButton btnLeft = new JButton("Turn left");
    JButton btnRight = new JButton("Turn right");
    JLabel currentPlayer = new JLabel("Player0");
    JComboBox<Case> availableCases = new JComboBox<>();
    PiecePanel currentPiece;
    Board board;
    Bag bag;
    int nbPiece;
    Player[] players;


    public MainWindow(JFrame parent, int nbPiece, Player[] players) {
        this.parent = parent;
        setSize(700,500);
        this.nbPiece = nbPiece;
        board = new Board(nbPiece);
        board.getCase(10,10).put(new Piece());
        board.getCase(10,11).put(new Piece());
        board.getCase(11,10).put(new Piece());
        board.getCase(12,10).put(new Piece());
        bag = new Bag(nbPiece);
        players = players;
        currentPiece = new PiecePanel(new Piece());
//        this.setContentPane(getBoardPanel());
        this.setLayout(new BorderLayout());
        add(getBoardPanel(), BorderLayout.CENTER);

        // Right
        Box actionPanel = Box.createVerticalBox();
        actionPanel.setPreferredSize(new Dimension(150,0));
        actionPanel.setBorder(new EmptyBorder(10,10,10,10));
        add(actionPanel, BorderLayout.EAST);
        actionPanel.add(currentPlayer);
        actionPanel.add(Box.createVerticalStrut(20));
        actionPanel.add(currentPiece);
//        actionPanel.add(Box.createVerticalStrut(20));
        actionPanel.add(btnLeft);
//        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(btnRight);
//        actionPanel.add(Box.createVerticalStrut(20));
        actionPanel.add(new JLabel("Actions"));
        actionPanel.add(availableCases);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });

    }

    public Container getBoardPanel() {
//        JScrollPane boardPanel = new JScrollPane();
//        JPanel content = new JPanel(new GridLayout())
        int top = board.getUpLimit();
        int left = board.getLeftLimit();
        int bottom = board.getBottomLimit();
        int right = board.getRighttLimit();
//        JPanel content = new JPanel(new GridLayout(bottom-top+3, right-left+3));
        JPanel content = new JPanel(null);
        for(int i=top-1; i<=bottom+1; i++) {
            for(int j=left-1; j<=right+1; j++) {
                int index_x = j-left+1;
                int index_y = i-top+1;
                CasePanel casePanel = new CasePanel(board.getCase(i,j));
                content.add(casePanel);
                casePanel.setBounds(105*index_x, 105*index_y, 100,100);
            }
        }
        System.out.println(board.toAdaptedString());
        content.setPreferredSize(new Dimension((right-left+3)*105,(bottom-top+3)*105));
//        boardPanel.add(content);
        return new JScrollPane(content);
    }


//    public static void main(String[] args) {
//        (new MainWindow(10)).setVisible(true);
//    }



}
