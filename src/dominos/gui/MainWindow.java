package dominos.gui;

import carcassonne.model.Card;
import dominos.model.*;

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
    JLabel currentPlayer = new JLabel("Player 0");
    JComboBox<ComboBoxItem> availableCases = new JComboBox<>();
    PiecePanel currentPiece;
    JScrollPane boardPanel;
    JButton btnAction = new JButton("Action");
    Board board;
    Bag bag;
    int nbPiece;
    Player[] players;
    int round = 0;

    public MainWindow(JFrame parent, Bag bag, Board board, Player[] players) {
        this.parent = parent;
//        this.nbPiece = nbPiece;
        this.bag = bag;
        this.board = board;
        this.players = players;

        initWindow();
        initComponents();
        setEvents();
        this.setVisible(true);
        start();
    }


//    public MainWindow(JFrame parent, int nbPiece, Player[] players) {
//        this.parent = parent;
//        this.nbPiece = nbPiece;
//        bag = new Bag(nbPiece);
//        board = new Board(nbPiece, bag.drawPiece());
//        this.players = players;
//
//        initWindow();
//        initComponents();
//        setEvents();
//        this.setVisible(true);
//        start();
//    }

    private void initWindow() {
        setSize(700,500);
        setLocation(300,200);
        this.parent.setVisible(false);
    }

    private void initComponents() {
        currentPiece = new PiecePanel(bag.drawPiece());
        refreshComboBox();
        this.setLayout(new BorderLayout());
        this.boardPanel = new JScrollPane(getBoardContent());
        add(boardPanel, BorderLayout.CENTER);

        // Right part ( action part )
        //contain panel
        JPanel divBody = new JPanel(new FlowLayout());
        Box actionPanel = Box.createVerticalBox();
        divBody.setPreferredSize(new Dimension(150,0));
        divBody.setBorder(new EmptyBorder(10,10,10,10));
        add(divBody, BorderLayout.EAST);
        divBody.add(actionPanel);
        actionPanel.add(currentPlayer);

        actionPanel.add(Box.createVerticalStrut(20));
        JPanel divPiece = new JPanel();
        divPiece.add(currentPiece);
        actionPanel.add(divPiece);

        actionPanel.add(Box.createVerticalStrut(10));
        JPanel divButton = new JPanel(new GridLayout(2,1,5,5));
        divButton.add(btnLeft);
        divButton.add(btnRight);
        actionPanel.add(divButton);
        actionPanel.add(Box.createVerticalStrut(20));
        actionPanel.add(new JLabel("Actions"));
        actionPanel.add(availableCases);

        actionPanel.add(Box.createVerticalStrut(10));
        JPanel divAction = new JPanel(new GridLayout(1,1));
        divAction.add(btnAction);
        actionPanel.add(divAction);
    }

    /**
     * Get the Container which contain the whole border
     * @return the <code>Container</code>
     */
    private Container getBoardContent() {
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
        return content;
    }

    private void refreshComboBox() {
        availableCases.removeAllItems();
        availableCases.addItem(new ComboBoxItem(null));
        for(Case item : board.getAvailableCases(currentPiece.getPiece())) {
            availableCases.addItem(new ComboBoxItem(item));
        }
    }

    private void refreshBorder() {
        boardPanel.setViewportView(getBoardContent());
        this.revalidate();
    }

    private void setEvents() {
        btnLeft.addActionListener(this::btnLeft_onClick);
        btnRight.addActionListener(this::btnRight_onClick);
        btnAction.addActionListener(this::btnAction_onClick);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });
    }

    private void btnLeft_onClick(ActionEvent e) {
        this.currentPiece.turnLeft();
        refreshComboBox();
    }

    private void btnRight_onClick(ActionEvent e) {
        this.currentPiece.turnRight();
        refreshComboBox();
    }

    private void btnAction_onClick(ActionEvent e) {
        ComboBoxItem choice = (ComboBoxItem) availableCases.getSelectedItem();
        if(! choice.willAbandon()) {
            int score = choice.getCase().put(currentPiece.getPiece());
            // add score
            players[round].addScore(score);
            refreshBorder();
        }
        next();
    }

    private void start() {
        round = 0;
        if(players[round] instanceof AIPlayer) {
            players[round].action(board, currentPiece.getPiece());
            refreshBorder();
            next();
        }
    }

    /**
     * When a human player finish one action, prepare the next step.
     * We draw a piece, and increment the <code>round</code>.
     * <p>
     * If the next player is an AI, we action automatically
     */
    private void next() {
        if(! bag.hasPiece()) { // game over
            // disable action button
            btnAction.setEnabled(false);
            StringBuilder result = new StringBuilder();
            if(currentPiece.getPiece() instanceof Card) {
                result.append("END");
            } else {
                for (int i = 0; i < players.length; i++) {
                    result.append(String.format("Player %d : %d\n", i, players[i].getScore()));
                }
            }
            JOptionPane.showMessageDialog(this, result.toString(), "Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        round = (round+1) % players.length;
        currentPlayer.setText("Player " + round);
        currentPiece.setPiece(bag.drawPiece());
        if(players[round] instanceof AIPlayer) { // AI
            players[round].action(board, currentPiece.getPiece());
            refreshBorder();
            next();
        }
        refreshComboBox();
    }


}
