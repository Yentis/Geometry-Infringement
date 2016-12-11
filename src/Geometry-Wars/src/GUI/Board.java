package GUI;

import Game.Drone;
import Game.Enemy;
import Game.Kogel;
import Game.Schip;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.*;


/**
 * Created by Yentl-PC on 9/11/2016.
 */
public class Board extends JPanel implements ActionListener {

    //TODO hitbox een beetje modifieen zodat het precies past

    private Timer timer;
    private Schip schip;
    private Drone drone;
    private final int DELAY = 10;
    private ArrayList<Enemy> enemyOnField = new ArrayList<Enemy>();
    private Iterator<Enemy> enemyIterator = enemyOnField.iterator();

    private int enemyCounter = 1;


    public Board() {
        addKeyListener(new Board.TAdapter());
        addMouseListener(new Board.MAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        schip = new Schip(1, 100, 10, "src/Media/schip1.png");
        drone = new Drone(1, "Drone1", "a", 100, 5, "src/Media/drone1.png", 1, 0);

        timer = new Timer(DELAY, this);
        timer.start();
        spawnEnemies();


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBullets(g);
        drawShip(g);
        drawDrone(g);
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


        if (schip.getRectangle() == null) {
            schip.setRectangle(new Rectangle2D.Double(schip.getLocation().getX(), schip.getLocation().getY(), schip.getWidth(), schip.getHeight()));
        } else {
            schip.getRectangle().setRect(schip.getLocation().getX(), schip.getLocation().getY(), schip.getWidth(), schip.getHeight());
        }

        /*if (schip.collisionDetect(enemy.getRectangle())){
            System.out.println("ej twerkt");
        }*/

        for ( Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ){
            Enemy enemy = enemyIterator.next();
            if (schip.collisionDetect(enemy.getRectangle())){
                schip.setHit(true);
                enemyIterator.remove();
            }
        }


        g2d.draw(schip.getRectangle());

        g2d.drawImage(schip.getImage(), schip.getLocation().x, schip.getLocation().y, this);
        //Returns an AffineTransform object representing the inverse transformation.   i dont get it
        try {
            g2d.transform(t.createInverse());
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void drawDrone(Graphics g){
        //drone.setX()
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        AffineTransform t = new AffineTransform();

        g2d.setTransform(t);

        g2d.translate(schip.getLocation().getX(), schip.getLocation().getY());
        g2d.rotate(Math.toRadians(drone.getCurrentAngle()) , drone.getWidth(), drone.getHeight() );
        //g2d.translate(-schip.getLocation().getX(), -schip.getLocation().getY() );
        g2d.transform(t);


        drone.setCurrentAngle(drone.getCurrentAngle() + 1);

        if (drone.getRectangle() == null) {
            drone.setRectangle(new Rectangle2D.Double(drone.getCurrentLocation().getX() -100, drone.getCurrentLocation().getY() -100, drone.getWidth(), drone.getHeight()));
        } else {
            drone.getRectangle().setRect(drone.getCurrentLocation().getX() -100, drone.getCurrentLocation().getY() -100, drone.getWidth() -100, drone.getHeight());
        }

        drone.setCurrentLocation(schip.getLocation());
        g2d.draw(drone.getRectangle());

        g2d.drawImage(drone.getImage(), t, this);
       g2d.setTransform(old);
        //Returns an AffineTransform object representing the inverse transformation.   i dont get it
        try {
            g2d.transform(t.createInverse());
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void drawBullets(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        ArrayList kogels = schip.getKogels();


        for (Iterator<Kogel> kogelIterator = kogels.iterator(); kogelIterator.hasNext(); ) {

            Kogel k = kogelIterator.next();
            AffineTransform old = g2d.getTransform();
            double angle = k.getDirection(k.gettarget(), k.getStartingPoint());
            AffineTransform t = new AffineTransform();
            t.translate(k.getCurrentLocation().getX(), k.getCurrentLocation().getY());
            t.rotate(Math.toRadians(angle), k.getWidth() / 2, k.getHeight() / 2);
            t.translate(-k.getCurrentLocation().getX(), -k.getCurrentLocation().getY());
            g2d.transform(t);

            if (k.getRectangle() == null) {
                k.setRectangle(new Rectangle2D.Double(k.getX(), k.getY(), k.getWidth(), k.getHeight()));
            } else {
                k.getRectangle().setRect(k.getX(), k.getY(), k.getWidth(), k.getHeight());
            }

            for (Enemy enemy : enemyOnField) {
                if (k.collisionDetect(enemy.getRectangle())) {
                    k.setHit(true);
                }
            }


            g2d.draw(k.getRectangle());
            g2d.drawImage(k.getImage(), k.getCurrentLocation().x, k.getCurrentLocation().y, this);
            g2d.setTransform(old);
        }
    }


    private void drawEnemy(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Kogel> kogels = schip.getKogels();
        for (Iterator<Enemy> iterator = enemyOnField.iterator(); iterator.hasNext(); ) {
            Enemy enemy = iterator.next();

            //uh.. dit wordt gebruikt om de vorige transform te restoren ofzoiets idk - Renzie
            AffineTransform old = g2d.getTransform();


            //Hier wordt alles veranderd op enkel t - Renzie
            AffineTransform t = new AffineTransform();
            t.translate(enemy.getCurrentLocation().getX(), enemy.getCurrentLocation().getY());
            t.rotate(Math.toRadians(schip.getDirection(schip.getLocation(), enemy.getCurrentLocation()) + 90), enemy.getWidth() / 2, enemy.getHeight() / 2);
            t.translate(-enemy.getCurrentLocation().getX(), -enemy.getCurrentLocation().getY());
            g2d.transform(t);


            // Gewoon een rectangle voor de hitbox - Renzie
            if (enemy.getRectangle() == null) {
                enemy.setRectangle(new Rectangle2D.Double(enemy.getCurrentLocation().getX(), enemy.getCurrentLocation().getY(), enemy.getWidth(), enemy.getHeight()));
            } else {
                enemy.getRectangle().setRect(enemy.getCurrentLocation().getX(), enemy.getCurrentLocation().getY(), enemy.getWidth(), enemy.getHeight());
            }


            //check als een kogel geland is op de enemy
            for (Kogel k : kogels) {

                if (k.collisionDetect(enemy.getRectangle())) {
                    //TODO MATTHIAS IER MOET ALLE STUFF IN WANNEER JE EEN ENEMY HIT - RENZIE
                    enemy.setHit(true);
                }
            }


            //Teken alles op t - Renzie
            g2d.draw(enemy.getRectangle());
            g2d.drawImage(enemy.getImage(), enemy.getCurrentLocation().x, enemy.getCurrentLocation().y, this);

            //Restore terug naar vorige transform - Renzie
            g2d.setTransform(old);

        }
    }


    private void approachShip() {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ){
            Enemy enemy = enemyIterator.next();
            enemy.updateLocation(schip.getLocation(), enemy.getCurrentLocation(), 1);
            if (enemy.isHit()){
                enemyIterator.remove();
            }
        }
    }

    private void updateKogels() {
        for (Iterator<Kogel> kogeliterator = schip.getKogels().iterator(); kogeliterator.hasNext();){
            Kogel k = kogeliterator.next();
            k.updateLocation(k.gettarget(), k.getStartingPoint(), k.getKogelSnelheid());
            if(k.isHit()){
                kogeliterator.remove();
            }
        }
    }


    private void spawnEnemies() {
        Timer spawnTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < enemyCounter; i++) {
                    enemyOnField.add(new Enemy(1, "WutFace", "euh wa moek ier zetten", 100, 10, "src/Media/vijand1.png", 20, 20));
                }
                enemyCounter++;
            }
        });
        spawnTimer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateKogels();
        approachShip();
        schip.beweegSchip();
        repaint();

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

        @Override
        public void mouseReleased(MouseEvent e) {
            schip.mouseReleased(e);
        }
    }
}
