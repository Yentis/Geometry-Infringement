package GUI;

import Game.Enemy;
import Game.Kogel;
import Game.Schip;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Created by Yentl-PC on 9/11/2016.
 */
public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Schip schip;
    private Enemy enemy;
    private final int DELAY = 10;
    //private Movement rotation;

    public Board() {
        addKeyListener(new Board.TAdapter());
        addMouseListener(new Board.MAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        schip = new Schip(1, 100, 10, "src/Media/schip1.png");
        enemy = new Enemy(1, "WutFace", "euh wa moek ier zetten", 100, 10, "src/Media/vijand1.png", 20, 20);

        //rotation = new Movement(this, schip);

        timer = new Timer(DELAY, this);
        timer.start();

       }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBullets(g);
        drawShip(g);
        drawEnemy(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawShip(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform t = new AffineTransform();

        t.translate(schip.getLocation().getX(), schip.getLocation().getY());
        t.rotate(Math.toRadians(schip.getAngle()), schip.getWidth() / 2, schip.getHeight() / 2);
        t.translate(-schip.getLocation().getX(), -schip.getLocation().getY());
        g2d.transform(t);


        g2d.drawImage(schip.getImage(), schip.getLocation().x, schip.getLocation().y, this);
        try{
            g2d.transform(t.createInverse());
        } catch (NoninvertibleTransformException e){
            e.printStackTrace();
        }
    }

    private void drawBullets(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        ArrayList kogels = schip.getKogels();


        for (Object item : kogels) {
            Kogel k = (Kogel) item;
            AffineTransform t = new AffineTransform();
            t.translate(k.getX(), k.getY());
            t.rotate(Math.toRadians(schip.getDirection(k.gettarget(), k.getStartingPoint())));
            g2d.drawImage(k.getImage(), t, this);
            try{
                g2d.transform(t.createInverse());
            } catch (NoninvertibleTransformException e){
                e.printStackTrace();
            }
        }
    }

    private void drawEnemy(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //TODO approach enemy towards ship
        AffineTransform t = new AffineTransform();
        t.translate(enemy.getLocation().getX(),enemy.getLocation().getY());
        t.rotate(Math.toRadians(schip.getDirection(schip.getLocation(), enemy.getLocation())));
        t.translate(-enemy.getLocation().getX(), -enemy.getLocation().getY());
        g2d.transform(t);
        //g2d.translate( -enemy.getLocation().getX(), -enemy.getLocation().getY());
        //System.out.println(enemy.getLocation());
        g2d.drawImage(enemy.getImage(),enemy.getLocation().x, enemy.getLocation().y, this);
        try{
            g2d.transform(t.createInverse());
        } catch (NoninvertibleTransformException e){
            e.printStackTrace();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateKogels();
        approachShip();
        schip.beweegSchip();
        repaint();

    }

    //TODO problem: rotate mee met schip wat dus niet zou mogen
    private void approachShip(){
        double length;
        double velocityX;
        double velocityY;
        double verschilX;
        double verschilY;

       // ArrayList enemies = schip.getKogels();
        // enemy spawnpoint -
        verschilX = schip.getLocation().getX() - enemy.getLocation().getX();
        verschilY = schip.getLocation().getY() - enemy.getLocation().getY();

            /* verschil x / vierkantswortel van ( verschilx^2 + verschilY^2) om de lengte naar 1 stuk te brengen
            *  dit bepaalt de snelheid van de bullet en kan versneld worden door gewoon de kogelsnelheid te veranderen.*/
        length = Math.sqrt(Math.pow(verschilX, 2) + Math.pow(verschilY, 2));
        velocityX = ((verschilX) / length);
        velocityY = ((verschilY) / length);


        if (enemy.getLocation() != schip.getLocation()){

            enemy.move(velocityX, velocityY);
        } else{
            //enemy.setVisible(false);
            System.out.println("point reached");
        }

    }

    private void updateKogels() {
        double length;
        double velocityX;
        double velocityY;
        double verschilX;
        double verschilY;

        ArrayList kogel = schip.getKogels();
        ArrayList coords = schip.getCoordinateList();
        for (int i = 0; i < kogel.size(); i++) {
            Kogel k = (Kogel) kogel.get(i);

            verschilX = k.gettarget().getX() - k.getStartingPoint().getX();
            verschilY = k.gettarget().getY() - k.getStartingPoint().getY();
            System.out.println(k.getX());

            /* verschil x / vierkantswortel van ( verschilx^2 + verschilY^2) om de lengte naar 1 stuk te brengen
            *  dit bepaalt de snelheid van de bullet en kan versneld worden door gewoon de kogelsnelheid te veranderen.*/
            length = Math.sqrt(Math.pow(verschilX, 2) + Math.pow(verschilY, 2));
            velocityX = ((verschilX) / length) * k.getKogelSnelheid();
            velocityY = ((verschilY) / length) * k.getKogelSnelheid();

            if (k.isVisible()) {
                k.move(velocityX, velocityY);
            } else {
                kogel.remove(i);
            }
        }
    }



    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            schip.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            schip.keyReleased(e);
        }
    }

    private class MAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            schip.mousePressed(e);
        }
    }
}
