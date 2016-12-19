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
    private Schip schipp2;
    private Drone drone;
    private Drone dronep2;
    private ArrayList<Enemy> enemyOnField = new ArrayList<Enemy>();
    private int enemyCounter = 1;
    private Timer spawnTimer;
    private GLabel combo;
    private GLabel combop2;
    private GLabel score;
    private GLabel scorep2;
    private JProgressBar currentHealthBar;
    private JProgressBar currentHealthBarp2;
    private double healthBarWidth;
    private double healthBarWidthp2;
    private double ratio;
    private boolean coop;
    private boolean gameFinished;

    public GamePanel() throws IOException, FontFormatException {
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);
        spawnEnemies();

    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        //make Components
        if(coop){
            combop2 = new GLabel("x 0" , 36f, 580, 620, 100, 60, false, Color.white);
            scorep2 = new GLabel("0", 30f, 950, 65, 300, 60, false, Color.white);
        }
        combo = new GLabel("x 0" , 36f, 30, 620, 100, 60, false, Color.white);
        score = new GLabel("0", 30f, 140, 65, 300, 60, false, Color.white);
        currentHealthBar = new JProgressBar();
        currentHealthBar.setBounds(20, 27, 425, 40);
        currentHealthBar.setBackground(new Color(0, 200, 0));
        currentHealthBar.setOpaque(true);
        if (coop) {
            currentHealthBarp2 = new JProgressBar();
            currentHealthBarp2.setBounds(575, 27, 425, 40);
            currentHealthBarp2.setBackground(new Color(0, 200, 0));
            currentHealthBarp2.setOpaque(true);
        }

        //score rechts uitlijnen
        score.setHorizontalAlignment(SwingConstants.RIGHT);

        //Add components to panel
        if(coop){
            panel.add(combop2);
            panel.add(scorep2);
            panel.add(currentHealthBarp2);
        }
        panel.add(combo);
        panel.add(score);
        panel.add(currentHealthBar);


        setAllComponentsVisible();
    }


    public void startGame(){
        gameFinished = false;
        spawnTimer.start();
        try{
            initComponents();
        } catch (IOException e){
            e.printStackTrace();
        } catch (FontFormatException e){
            e.printStackTrace();
        }
        schip = new Schip(1, 100, 10, "src/Media/schip1.png", 0, 0, 37, 39, 38, 40);
        drone = new Drone(1, "Drone1", "a", 100, 5, "src/Media/drone1.png", 1, 0);
        if(coop){
            schipp2 = new Schip(1, 100, 10, "src/Media/schip1.png", 0, 0, 81, 68, 90, 83);
            dronep2 = new Drone(1, "Drone1", "a", 100, 5, "src/Media/drone1.png", 1, 0);
        }
    }

    public void pauseGame() { spawnTimer.stop(); }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBullets(g, schip);
        drawShip(g, schip);
        drawDrone(g, drone, schip);
        drawEnemy(g);
        if(coop){
            drawBullets(g, schipp2);
            drawShip(g, schipp2);
            drawDrone(g, dronep2, schipp2);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawShip(Graphics g, Schip schip) {
        Graphics2D g2d = (Graphics2D) g;
        schip.draw(g2d, schip.getCurrentAngle());
        schipHit(schip);
    }

    private void schipHit(Schip schip){
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

    private void drawDrone(Graphics g, Drone drone, Schip schip) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        AffineTransform t = new AffineTransform();
        g2d.setTransform(t);

        drone.drawAccordingToShip(g2d, schip);
        g2d.transform(t);

        drone.setCurrentAngle(drone.getCurrentAngle() + 1);
        drone.drawHitBox(g2d);
        drone.setCurrentLocation(schip.getCurrentLocation());

        g2d.drawImage(drone.getImage(), drone.getCurrentLocation().x, drone.getCurrentLocation().y, this);
        g2d.setTransform(old);
    }

    private void drawBullets(Graphics g, Schip schip) {
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
                    System.out.println(schip.getHp());
                    System.out.println(schip.isLifesteal());
                    if (schip.getHp() < 100 && schip.isLifesteal()){
                        schip.addHp(2);
                    }
                }
            }
        }
    }

    private void drawEnemy(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //loop over alle enemies, zodat ze allemaal geupdate worden
        for (Iterator<Enemy> iterator = enemyOnField.iterator(); iterator.hasNext(); ) {
            Enemy enemy = iterator.next();
            if(coop){
                enemy.draw(g2d, enemy.getDirection(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation()));

                for (Kogel k : closestShip(enemy).getKogels()) {
                    if (k.collisionDetect(enemy.getHitBox())) {
                        enemy.setHit(true);

                    }
                }
            } else {
                enemy.draw(g2d, enemy.getDirection(schip.getCurrentLocation(), enemy.getCurrentLocation()));

                //check als een kogel geland is op de enemy
                for (Kogel k : schip.getKogels()) {
                    if (k.collisionDetect(enemy.getHitBox())) {

                        enemy.loseHP(50);
                        if (enemy.getHP() == 0){

                            enemy.setHit(true);

                        }
                        System.out.println(enemy.getHP());

                    }
                    
                }
            }
        }
    }

    private Schip closestShip(Enemy enemy){
        //berekent distance tussen 2 points
        int distancep1 = (int)(enemy.getCurrentLocation().distanceSq(schip.getCurrentLocation()));
        int distancep2 = (int)(enemy.getCurrentLocation().distanceSq(schipp2.getCurrentLocation()));

        if(distancep1 < distancep2){
            return schip;
        } else {
            return schipp2;
        }
    }

    private void approachShip() {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if(coop){
                enemy.updateLocation(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation(), 1);
            } else {
                enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), 1);
            }

            if (enemy.isHit()) {
                enemyIterator.remove();

            }
        }
    }

    private void updateKogels(Schip schip) {
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

    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    public void update(){
        updateKogels(schip);
        approachShip();
        schip.beweegSchip();
        combo.setText("x " + schip.getCombo());
        score.setText("" + schip.getScore());
        currentHealthBar.setSize((int) updateHealthBar(schip, healthBarWidth, currentHealthBar), currentHealthBar.getHeight());
        if(coop){
            updateKogels(schipp2);
            schipp2.beweegSchip();
            combop2.setText("x " + schipp2.getCombo());
            scorep2.setText("" + schipp2.getScore());
            currentHealthBarp2.setSize((int) updateHealthBar(schipp2, healthBarWidthp2, currentHealthBarp2), currentHealthBarp2.getHeight());
        };
    }

    private double updateHealthBar(Schip schip, double healthBarWidth, JProgressBar currentHealthBar){
        if (schip.getHp() != 0){
            ratio = 425 / schip.getMaxhp();
            healthBarWidth = (int)ratio * schip.getHp();
        } else {
            //TODO
            gameFinished = true;
        }
        return healthBarWidth;
    }

    private void updateCombo(){
        //TODO cleanup
    }



    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            schip.keyPressed(e);
            if(coop){schipp2.keyPressed(e);}
        }

        @Override
        public void keyReleased(KeyEvent e) {
            schip.keyReleased(e);
            if(coop){schipp2.keyReleased(e);}
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
