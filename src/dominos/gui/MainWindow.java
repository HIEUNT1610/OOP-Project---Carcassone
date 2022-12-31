package dominos.gui;

import dominos.model.*;
import dominos.view.MainActivaty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    JFrame parent;
    JButton btnLeft = new JButton("Turn left");
    JButton btnRight = new JButton("Turn right");
    JLabel currentPlayer = new JLabel("Player0");
    JComboBox<Case> availableCases = new JComboBox<>();
    PiecePanel currentPiece;
    JButton btnAction = new JButton("Action");
    Board board;
    Bag bag;
    int nbPiece;
    Player[] players;


    public MainWindow(JFrame parent, int nbPiece, Player[] players) {
        this.parent = parent;
        setSize(700,500);
        setLocation(300,200);
        this.nbPiece = nbPiece;
        bag = new Bag(nbPiece);
        board = new Board(nbPiece, bag.drawPiece());
//        board.getCase(10,10).put(new Piece());
//        board.getCase(10,11).put(new Piece());
//        board.getCase(11,10).put(new Piece());
//        board.getCase(12,10).put(new Piece());
        this.players = players;

        currentPiece = new PiecePanel(new Piece());
        this.setLayout(new BorderLayout());
        add(getBoardPanel(), BorderLayout.CENTER);

        // Right
        //contain panel
        JPanel divBody = new JPanel(new FlowLayout());
        Box actionPanel = Box.createVerticalBox();
        divBody.setPreferredSize(new Dimension(150,0));
        divBody.setBorder(new EmptyBorder(10,10,10,10));
//        actionPanel.setBorder(new EmptyBorder(10,10,10,10));
        add(divBody, BorderLayout.EAST);
        divBody.add(actionPanel);
        actionPanel.add(currentPlayer);

        actionPanel.add(Box.createVerticalStrut(20));
//        actionPanel.add(currentPiece);
        JPanel divPiece = new JPanel();
        divPiece.add(currentPiece);
        actionPanel.add(divPiece);

        actionPanel.add(Box.createVerticalStrut(10));
        JPanel divButton = new JPanel(new GridLayout(2,1,5,5));
        divButton.add(btnLeft);
        btnLeft.addActionListener(this::btnLeft_onClick);
        divButton.add(btnRight);
        btnRight.addActionListener(this::btnRight_onClick);
        actionPanel.add(divButton);

        actionPanel.add(Box.createVerticalStrut(20));
        actionPanel.add(new JLabel("Actions"));
        actionPanel.add(availableCases);

        actionPanel.add(Box.createVerticalStrut(10));
        JPanel divAction = new JPanel(new GridLayout(1,1));
        divAction.add(btnAction);
        actionPanel.add(divAction);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });
    }



    public Container getBoardPanel() {
        int top = board.getUpLimit();
        int left = board.getLeftLimit();
        int bottom = board.getBottomLimit();
        int right = board.getRighttLimit();
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
        content.setPreferredSize(new Dimension((right-left+3)*105,(bottom-top+3)*105));
        return new JScrollPane(content);
    }


    private void btnLeft_onClick(ActionEvent e) {
        this.currentPiece.turnLeft();
    }

    private void btnRight_onClick(ActionEvent e) {
        this.currentPiece.turnRight();
    }

    public void start() {

    }


}
