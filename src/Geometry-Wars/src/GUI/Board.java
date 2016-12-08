package GUI;

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

        timer = new Timer(DELAY, this);
        timer.start();
        spawnEnemies();


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

        if (schip.getRectangle() == null) {
            schip.setRectangle(new Rectangle2D.Double(schip.getLocation().getX(), schip.getLocation().getY(), schip.getWidth(), schip.getHeight()));
        } else {
            schip.getRectangle().setRect(schip.getLocation().getX(), schip.getLocation().getY(), schip.getWidth(), schip.getHeight());
        }

        /*if (schip.collisionDetect(enemy.getRectangle())){
            System.out.println("ej twerkt");
        }*/


        g2d.draw(schip.getRectangle());

        g2d.drawImage(schip.getImage(), schip.getLocation().x, schip.getLocation().y, this);
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
            t.translate(k.getLocation().getX(), k.getLocation().getY());
            t.rotate(Math.toRadians(angle), k.getWidth() / 2, k.getHeight() / 2);
            t.translate(-k.getLocation().getX(), -k.getLocation().getY());
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
            g2d.drawImage(k.getImage(), k.getLocation().x, k.getLocation().y, this);
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
            t.translate(enemy.getLocation().getX(), enemy.getLocation().getY());
            t.rotate(Math.toRadians(schip.getDirection(schip.getLocation(), enemy.getLocation()) + 90), enemy.getWidth() / 2, enemy.getHeight() / 2);
            t.translate(-enemy.getLocation().getX(), -enemy.getLocation().getY());
            g2d.transform(t);


            // Gewoon een rectangle voor de hitbox - Renzie
            if (enemy.getRectangle() == null) {
                enemy.setRectangle(new Rectangle2D.Double(enemy.getLocation().getX(), enemy.getLocation().getY(), enemy.getWidth(), enemy.getHeight()));
            } else {
                enemy.getRectangle().setRect(enemy.getLocation().getX(), enemy.getLocation().getY(), enemy.getWidth(), enemy.getHeight());
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
            g2d.drawImage(enemy.getImage(), enemy.getLocation().x, enemy.getLocation().y, this);

            //Restore terug naar vorige transform - Renzie
            g2d.setTransform(old);

        }

    }


    private void approachShip() {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ){
            Enemy enemy = enemyIterator.next();
            enemy.updateLocation(schip.getLocation(), enemy.getLocation(), 1);
            if (enemy.isHit()){
                enemyIterator.remove();
            }
        }
    }

    private void updateKogels() {
        for (Iterator<Kogel> kogeliterator = schip.getKogels().iterator(); kogeliterator.hasNext();){
            Kogel k = kogeliterator.next();
            k.updateLocation(k.gettarget(), k.getStartingPoint(), 5);
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
                    System.out.println("spawned");

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
