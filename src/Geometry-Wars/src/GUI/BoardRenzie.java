package GUI;

import Game.Kogel;
import Game.KogelRenzie;
import Game.Schip;
import Game.SchipRenzie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by Renzie on 19/11/2016.
 */
public class BoardRenzie extends JPanel implements ActionListener {

    private Timer timer;
    private SchipRenzie schip;
    private final int DELAY = 10;

    public BoardRenzie(){
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        schip = new SchipRenzie(1, 100, 10, "src/Media/schip1.png");

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
        g2d.rotate(Math.toRadians(schip.getAngle(schip.getLocation())));

        g2d.drawImage(schip.getImage(), schip.getLocation().x, schip.getLocation().y, this);
    }
    private void drawBullets(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        ArrayList kogels = schip.getKogels();


        for(Object item: kogels){
            KogelRenzie k = (KogelRenzie) item;
            AffineTransform t = new AffineTransform();
            t.translate(k.getX(), k.getY());
            t.rotate(Math.toRadians(schip.getAngle(k.getDirection())));
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
            KogelRenzie k = (KogelRenzie) kogel.get(i);


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
