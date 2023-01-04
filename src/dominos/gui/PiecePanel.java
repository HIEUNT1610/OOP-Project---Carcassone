package dominos.gui;

import carcassonne.model.Card;
import dominos.Direction;
import dominos.model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PiecePanel extends JPanel {

    Piece piece;
    BufferedImage bgPiece;

    public PiecePanel(Piece piece) {
        this.piece = piece;
        bgPiece = getImage();
//        this.setPreferredSize(new Dimension(bgPiece.getWidth(),bgPiece.getHeight()));
        this.setPreferredSize(new Dimension(100,100));
        this.setSize(100,100);

    }

    public BufferedImage getImage() {
        BufferedImage res = null;
        try{
            File img = new File(piece instanceof Card ? ((Card)piece).getImagePath() : "img/bg.jpg");
            res = ImageIO.read(img);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.piece == null)
            return;
        if(!(piece instanceof Card)) {
            g.drawImage(this.bgPiece, 0, 0, bgPiece.getWidth(), bgPiece.getHeight(), null);
            int[][] values = new int[4][3];
            for(Direction d : Direction.values()) {
                values[d.ordinal()] = piece.getSideAt(d);
            }
            // top
            g.drawString(values[0][0] + "", 27, 15);
            g.drawString(values[0][1] + "", 45, 15);
            g.drawString(values[0][2] + "", 63, 15);
            // left
            g.drawString(values[1][0] + "", 8, 73);
            g.drawString(values[1][1] + "", 8, 54);
            g.drawString(values[1][2] + "", 8, 36);
            // bottom
            g.drawString(values[2][0] + "", 63, 92);
            g.drawString(values[2][1] + "", 45, 92);
            g.drawString(values[2][2] + "", 27, 92);
            // right
            g.drawString(values[3][0] + "", 85, 36);
            g.drawString(values[3][1] + "", 85, 54);
            g.drawString(values[3][2] + "", 85, 73);
        } else {
            bgPiece = getRotatedImage(getImage(), ((Card)piece).getRotationRadio());
            g.drawImage(this.bgPiece, 0, 0, bgPiece.getWidth(), bgPiece.getHeight(), null);
        }
        g.drawRect(0,0,100,100);
    }

    public Piece getPiece() {
        return piece;
    }
    public PiecePanel setPiece(Piece p) {
        this.piece = p;
        repaint();
        return this;
    }

    public void turnLeft() {
        piece.turnLeft();
        System.out.println(piece);
        System.out.println(((Card)piece).getRotationRadio());
        repaint();
    }

    public void turnRight() {
        piece.turnRight();
        System.out.println(piece);
        System.out.println(((Card)piece).getRotationRadio());
        repaint();
    }

    public BufferedImage getRotatedImage(BufferedImage image, int rotateRadio) {
        final double rads = Math.toRadians(rotateRadio);
//        final double sin = Math.abs(Math.sin(rads));
//        final double cos = Math.abs(Math.cos(rads));
//        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
//        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(100, 100, image.getType());
        final AffineTransform at = new AffineTransform();
//        at.translate(w / 2, h / 2);
        at.rotate(rads,50, 50);
//        at.translate(- image.getWidth() / 2, - image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        return rotatedImage;
    }
}
