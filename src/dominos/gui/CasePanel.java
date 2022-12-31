package dominos.gui;

import dominos.model.Case;
import dominos.model.Direction;
import dominos.model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CasePanel extends JPanel {

    Case c;
    BufferedImage bgPiece;

    public CasePanel(Case c) {
        this.c = c;
        try{
            File img = new File(c.isOccupied() ? "img/bg.jpg" : "img/bg-white.jpg");
            bgPiece = ImageIO.read(img);
        }catch (IOException e) {
            e.printStackTrace();
        }
//        this.setPreferredSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(bgPiece.getWidth(),bgPiece.getHeight()));

//        this.setLayout(new GridLayout(5,5,1,1));
//        for(int i=0; i<25; i++) {
//            this.add(new JLabel("x"));
//        }
//        this.setSize(50,50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bgPiece, 0, 0, bgPiece.getWidth(), bgPiece.getHeight(), null);
        g.drawRect(0,0,100,100);
        if(c.isOccupied()){
            int[][] values = new int[4][3];
            for(Direction d : Direction.values()) {
                values[d.ordinal()] = c.getOccupyingPiece().getSideAt(d);
            }
            // top
            g.drawString(values[0][0]+"", 27,15);
            g.drawString(values[0][1]+"", 45,15);
            g.drawString(values[0][2]+"", 63,15);
            // left
            g.drawString(values[1][0]+"", 8,73);
            g.drawString(values[1][1]+"", 8,54);
            g.drawString(values[1][2]+"", 8,36);
            // bottom
            g.drawString(values[2][0]+"", 63,92);
            g.drawString(values[2][1]+"", 45,92);
            g.drawString(values[2][2]+"", 27,92);
            // right
            g.drawString(values[3][0]+"", 85,36);
            g.drawString(values[3][1]+"", 85,54);
            g.drawString(values[3][2]+"", 85,73);
        }else{
            // position
            g.drawString(String.format("(%d,%d)", c.getX(), c.getY()), 30,54);
        }
    }


    public static void main(String[] args) {
        JFrame window = new JFrame();
        Case c = new Case(12,12);
//        c.put(new Piece());
        window.getContentPane().add(new CasePanel(c));
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
//        JPanel layout = new JPanel(new GridBagLayout());
//        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
//        layout.add(new CasePanel(new Case(0,0)));
//        panel.add(layout, BorderLayout.CENTER);
//        window.add(panel);
        window.setSize(500,500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        System.out.println(c);
    }
}
