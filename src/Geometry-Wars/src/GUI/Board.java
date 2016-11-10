package GUI;

import Game.Schip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Created by Yentl-PC on 9/11/2016.
 */
public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Schip schip;
    private final int DELAY = 10;

    public Board(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        schip = new Schip(1, 100, 10, "src/Media/schip1.png");

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(schip.getR()), schip.getX() + schip.getWidth()/2, schip.getY() + schip.getHeight()/2);
        g2d.drawImage(schip.getImage(), schip.getX(), schip.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        schip.beweegSchip();
        repaint();
    }

    private class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            schip.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            schip.keyReleased(e);
        }
    }
}
