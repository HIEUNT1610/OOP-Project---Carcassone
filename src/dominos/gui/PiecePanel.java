package dominos.gui;

import dominos.model.Case;
import dominos.model.Direction;
import dominos.model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PiecePanel extends JPanel {

    Piece piece;
    BufferedImage bgPiece;

    public PiecePanel(Piece piece) {
        this.piece = piece;
        try{
            File img = new File("img/bg.jpg");
            bgPiece = ImageIO.read(img);
        }catch (IOException e) {
            e.printStackTrace();
        }
//        this.setPreferredSize(new Dimension(bgPiece.getWidth(),bgPiece.getHeight()));
        this.setPreferredSize(new Dimension(100,100));
        this.setSize(100,100);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bgPiece, 0, 0, bgPiece.getWidth(), bgPiece.getHeight(), null);
        g.drawRect(0,0,100,100);
        int[][] values = new int[4][3];
        for(Direction d : Direction.values()) {
            values[d.ordinal()] = piece.getSideAt(d);
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
    }

    public Piece getPiece() {
        return piece;
    }

    public void turnLeft() {
        piece.turnLeft();
        repaint();
    }

    public void turnRight() {
        piece.turnRight();
        repaint();
    }
}
