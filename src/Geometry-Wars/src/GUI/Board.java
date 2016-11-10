package GUI;

import Game.Kogel;
import Game.Schip;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        addMouseListener(new MAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

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

        ArrayList kogels = schip.getKogels();

        for(Object item: kogels){
            Kogel k = (Kogel) item;
            g2d.drawImage(k.getImage(), k.getX(), k.getY(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateKogels();
        schip.beweegSchip();
        repaint();
    }

    private void updateKogels(){
        float length;
        float velocityX;
        float velocityY;

        ArrayList kogel = schip.getKogels();

        for(int i = 0; i < kogel.size(); i++){
            Kogel k = (Kogel) kogel.get(i);

            length = (float) Math.sqrt((k.getMousex() - schip.getX())*(k.getMousex() - schip.getX()) + (k.getMousey() - schip.getY())*(k.getMousey() - schip.getY()));
            velocityX = (float) (k.getMousex() - schip.getX()) /length * (float) k.getKogelSnelheid();
            velocityY = (float) (k.getMousey() - schip.getY()) /length * (float) k.getKogelSnelheid();

            if(k.isVisible()){
                k.move(velocityX, velocityY);
            } else {
                kogel.remove(i);
            }
        }
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

    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) { schip.mousePressed(e);}
    }
}
