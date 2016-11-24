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
        addKeyListener(new Board.TAdapter());
        addMouseListener(new Board.MAdapter());
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

        drawBullets(g);
        drawShip(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawShip(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(schip.getAngle()), schip.getLocation().getX() + schip.getWidth() / 2, schip.getLocation().getY() + schip.getHeight()/2);

        g2d.drawImage(schip.getImage(), schip.getLocation().x, schip.getLocation().y, this);
    }
    private void drawBullets(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        ArrayList kogels = schip.getKogels();


        for(Object item: kogels){
            Kogel k = (Kogel) item;
            AffineTransform t = new AffineTransform();
            t.translate(k.getX(), k.getY());
            t.rotate(Math.toRadians(schip.getDirection(k.getDirection())));
            g2d.drawImage(k.getImage(), t, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateKogels();
        schip.beweegSchip();
        repaint();
    }

    private void updateKogels(){
        double length;
        double velocityX;
        double velocityY;
        double verschilX;
        double verschilY;

        ArrayList kogel = schip.getKogels();

        for(int i = 0; i < kogel.size(); i++){
            Kogel k = (Kogel) kogel.get(i);


            verschilX = k.getDirection().getX() - schip.getLocation().getX();
            verschilY = k.getDirection().getY() - schip.getLocation().getY();



            /* verschil x / vierkantswortel van ( verschilx^2 + verschilY^2) om de lengte naar 1 stuk te brengen
            *  dit bepaalt de snelheid van de bullet en kan versneld worden door gewoon de kogelsnelheid te veranderen.
            */

            length = Math.sqrt(Math.pow(verschilX,2) + Math.pow(verschilY,2));
            velocityX = ((verschilX) / length)*  k.getKogelSnelheid();
            velocityY =  ((verschilY) / length)* k.getKogelSnelheid();

            if(k.isVisible()){
                k.move(velocityX, velocityY);
            } else {
                kogel.remove(i);
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            schip.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            schip.keyReleased(e);
        }
    }

    private class MAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) { schip.mousePressed(e);}
    }
}
