package GUI;

import GComponents.GButton;
import GComponents.GLabel;
import GComponents.GPanel;
import Game.*;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.*;


/**
 * Created by Yentl-PC on 9/11/2016.
 */
public class GamePanel extends GPanel{
    private GamePanel panel = this;

    private Schip schip;
    private Drone drone;
    private ArrayList<Enemy> enemyOnField = new ArrayList<Enemy>();
    private int enemyCounter = 1;
    private Timer spawnTimer;
    private GLabel combo;
    private GLabel score;
    private JProgressBar currentHealthBar;
    private double healthBarWidth;
    private double ratio;



    public GamePanel() throws IOException, FontFormatException {
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        requestFocus();
        initComponents();
        setDoubleBuffered(true);

        schip = new Schip(1, 100, 10, "src/Media/schip1.png", 0, 0);
        drone = new Drone(1, "Drone1", "a", 100, 5, "src/Media/drone1.png", 1, 0);
        spawnEnemies();
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        //make Components
        combo = new GLabel("x 0" , 36f, 30, 665, 100, 60, false, Color.white);
        score = new GLabel("0", 30f, 700, 25, 300, 60, false, Color.white);
        currentHealthBar = new JProgressBar();
        currentHealthBar.setBounds(20, 27, 425, 40);
        currentHealthBar.setBackground(new Color(0, 200, 0));
        currentHealthBar.setOpaque(true);

        //score rechts uitlijnen
        score.setHorizontalAlignment(SwingConstants.RIGHT);

        //Add components to panel
        panel.add(combo);
        panel.add(score);
        panel.add(currentHealthBar);

        for (Component component : panel.getComponents()){
            component.setVisible(true);
        }
    }

    public void startGame() {
        spawnTimer.start();
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
        schip.draw(g2d, schip.getCurrentAngle());
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (schip.collisionDetect(enemy.getHitBox())) {
                schip.setHit(true);
                schip.loseHP(enemy.getKracht());
                schip.resetCombo();
                enemyIterator.remove();
            }
        }
    }

    private void drawDrone(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        AffineTransform t = new AffineTransform();
        g2d.setTransform(t);

        drone.drawAccordingToShip(g2d, schip);
        g2d.transform(t);


        drone.setCurrentAngle(drone.getCurrentAngle() + 1);
        drone.drawHitBox(g2d);
        drone.setCurrentLocation(schip.getCurrentLocation());

        g2d.drawImage(drone.getImage(), schip.getCurrentLocation().x -100, schip.getCurrentLocation().y -100, this);
        //Returns an AffineTransform object representing the inverse transformation.   i dont get it
        try {
            g2d.transform(t.createInverse());


        } catch (NoninvertibleTransformException e) {

            e.printStackTrace();

        }

        g2d.drawImage(drone.getImage(), drone.getCurrentLocation().x, drone.getCurrentLocation().y, this);
        g2d.setTransform(old);

    }

    private void drawBullets(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Iterator<Kogel> kogelIterator = schip.getKogels().iterator(); kogelIterator.hasNext(); ) {
            Kogel k = kogelIterator.next();
            k.draw(g2d, k.getDirection(k.getTarget(), k.getStartingPoint()));
            for (Enemy enemy : enemyOnField) {
                //wanneer isHit true is verdwijnt de bullet
                if (k.collisionDetect(enemy.getHitBox())) {
                    k.setHit(true);
                    //TODO combo bepalen en upgrades uitvoeren
                    schip.addCombo();
                    schip.checkForUpgrade(schip.getCombo());
                }
            }
        }
    }

    private void drawEnemy(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //loop over alle enemies, zodat ze allemaal geupdatet worden
        for (Iterator<Enemy> iterator = enemyOnField.iterator(); iterator.hasNext(); ) {
            Enemy enemy = iterator.next();
            enemy.draw(g2d, enemy.getDirection(schip.getCurrentLocation(), enemy.getCurrentLocation()));
            //check als een kogel geland is op de enemy
            for (Kogel k : schip.getKogels()) {
                if (k.collisionDetect(enemy.getHitBox())) {
                    enemy.setHit(true);

                }
            }
        }
    }

    private void approachShip() {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), 1);
            if (enemy.isHit()) {
                enemyIterator.remove();
            }
        }
    }

    private void updateKogels() {
        for (Iterator<Kogel> kogeliterator = schip.getKogels().iterator(); kogeliterator.hasNext(); ) {
            Kogel k = kogeliterator.next();
            k.updateLocation(k.getTarget(), k.getStartingPoint(), k.getKogelSnelheid());
            if (k.isHit()) {
                kogeliterator.remove();
            }
        }
    }


    public void spawnEnemies() {
        spawnTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < enemyCounter; i++) {
                    enemyOnField.add(new Enemy(1, "WutFace", "euh wa moek ier zetten", 100, 10, "src/Media/vijand1.png", 20, 20));
                }
                System.out.println("spawned");
                enemyCounter++;
            }
        });
    }




    public void update(){
        updateKogels();
        approachShip();
        schip.beweegSchip();
        updateHealthBar();
        combo.setText("x " + schip.getCombo());
        score.setText("" + schip.getScore());
        currentHealthBar.setSize((int) healthBarWidth, currentHealthBar.getHeight());
        repaint();
    }

    private void updateHealthBar(){
        ratio = currentHealthBar.getWidth() / schip.getHp();
        healthBarWidth = ratio * schip.getHp();
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
