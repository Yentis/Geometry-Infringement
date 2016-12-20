package GUI;

import GComponents.GLabel;
import Game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Renzie on 20/12/2016.
 */
public class Game {
    private Schip schip;
    private Schip schipp2;
    private Drone drone;
    private Drone dronep2;
    private ArrayList<Enemy> enemyOnField = new ArrayList<Enemy>();
    private int enemyCounter = 1;
    private Timer spawnTimer;
    private Timer invulnerabilityTimer;
    private Timer slowerEnemiesTimer;
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
    private int baseDamage = 50;
    private int wave = 1;
    private Timer shootingDroneTimer;


    public Game(boolean coop) { //TODO setup method
        startGame();
    }

    public void setupGame(boolean coop) {
        //make schip, drone
        schip = new Schip(1, 100, 10, "src/Media/schip1.png", 0, 0, 37, 39, 38, 40);
        drone = new Drone(1, "Drone1", "a", 100, 5, "src/Media/drone1.png", 1, 0);

        if (coop) {
            setupGameCoop();
        }

        gameFinished = false;
        setupSpawnTimer();
        spawnTimer.start();

        setSlowerEnemiesTimer();
        setInvulnerabilityTimer();


        //TODO StartTimers en stuff setuppen, daarna alles initializen in startgame, en startgame oproepen in constructor

    }

    public void setupGameCoop() {

        schipp2 = new Schip(1, 100, 10, "src/Media/schip1.png", 0, 0, 81, 68, 90, 83);
        dronep2 = new Drone(1, "Drone1", "a", 100, 5, "src/Media/drone1.png", 1, 0);

    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public void startGame() {
        setUpShootingDroneTimer(drone);
    }

    public void pauseGame() {
        spawnTimer.stop();
    }

    private void schipHit(Schip schip) {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (schip.collisionDetect(enemy.getHitBox())) {
                schip.setHit(true);
                if (!schip.isInvulnerability()) {
                    schip.loseHP(enemy.getKracht());
                    schip.resetCombo();
                }
                enemyIterator.remove();
            }
        }
    }

    public void collisionEffect(ArrayList<Kogel> kogels, Enemy enemy) {
        for (Kogel k : kogels) {
            if (k.collisionDetect(enemy.getHitBox())) {

                enemy.loseHP(baseDamage);
                if (enemy.getHP() == 0) {
                    enemy.setHit(true);
                }
                System.out.println(enemy.getHP());
            }
        }
    }

    private Schip closestShip(Enemy enemy) {
        //berekent distance tussen 2 points
        int distancep1 = (int) (enemy.getCurrentLocation().distanceSq(schip.getCurrentLocation()));
        int distancep2 = (int) (enemy.getCurrentLocation().distanceSq(schipp2.getCurrentLocation()));

        if (distancep1 < distancep2) {
            return schip;
        } else {
            return schipp2;
        }
    }

    private Point closestEnemy(Drone drone, ArrayList<Enemy> enemies) {
        Point closestEnemy = new Point(5000, 5000);

        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {

            Enemy enemy = enemyIterator.next();
            if (drone.getCurrentLocation().distanceSq(enemy.getCurrentLocation()) < drone.getCurrentLocation().distanceSq(closestEnemy)) {
                closestEnemy.setLocation(enemy.getCurrentLocation());

            }
        }
        return closestEnemy;
    }

    private void approachShip() {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();

            if (schip.isSlowerEnemies()) {
                enemy.setSpeed(1);
            }
            if (coop) {
                enemy.updateLocation(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
            } else {
                enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
            }

            if (enemy.isHit()) {
                schip.addCombo();
                schip.checkForUpgrade(schip.getCombo());
                if (schip.isDroneActive()) {
                    shootingDroneTimer.start();
                }
                //schip.addCurrentXp(enemy.get);

                enemyIterator.remove();

            }
        }
    }

    private void updateKogels(ArrayList<Kogel> kogels) {
        for (Iterator<Kogel> kogeliterator = kogels.iterator(); kogeliterator.hasNext(); ) {
            Kogel k = kogeliterator.next();
            k.updateLocation(k.getTarget(), k.getStartingPoint(), k.getKogelSnelheid());
            if (k.isHit()) {
                kogeliterator.remove();
            }
        }
    }

    public void setupSpawnTimer() {
        spawnTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < enemyCounter; i++) {
                    enemyOnField.add(new Enemy(1, "WutFace", "euh wa moek ier zetten", 100, 10, "src/Media/vijand1.png", 20, 20));

                }
                enemyCounter++;
            }
        });
    }

    public void update() {
        updateKogels(schip.getKogels());
        updateKogels(drone.getKogels());
        approachShip();
        schip.beweegSchip();
        if (schip.isInvulnerability()) {

            System.out.println("invulnerability start");
            invulnerabilityTimer.start();
        }
        if (schip.isSlowerEnemies()) {
            System.out.println("slower enemies");
            slowerEnemiesTimer.start();
        }

        combo.setText("x " + schip.getCombo());
        score.setText("" + schip.getScore());
        currentHealthBar.setSize((int) updateHealthBar(schip, healthBarWidth, currentHealthBar), currentHealthBar.getHeight());
        if (coop) {
            updateKogels(schipp2.getKogels());
            schipp2.beweegSchip();
            combop2.setText("x " + schipp2.getCombo());
            scorep2.setText("" + schipp2.getScore());
            currentHealthBarp2.setSize((int) updateHealthBar(schipp2, healthBarWidthp2, currentHealthBarp2), currentHealthBarp2.getHeight());
        }

    }

    private double updateHealthBar(Schip schip, double healthBarWidth, JProgressBar currentHealthBar) {
        if (schip.getHp() != 0) {
            ratio = 425 / schip.getMaxhp();
            healthBarWidth = (int) ratio * schip.getHp();
        } else {
            //TODO
            gameFinished = true;
        }
        return healthBarWidth;
    }

    public void setInvulnerabilityTimer() {
        invulnerabilityTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schip.setInvulnerability(false);
                System.out.println("invulnerability stop");
                invulnerabilityTimer.stop();
            }
        });

    }

    public void setSlowerEnemiesTimer() {
        slowerEnemiesTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schip.setSlowerEnemies(false);
                System.out.println("slower enemies stopped");
                slowerEnemiesTimer.stop();

            }
        });
    }

    private void setUpShootingDroneTimer(Drone drone) {
        shootingDroneTimer = new Timer(drone.getFireSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enemyOnField.isEmpty()) {
                    drone.getKogels().add(new Kogel(drone.getCurrentLocation().getX(), drone.getCurrentLocation().getY(), closestEnemy(drone, enemyOnField), "src/Media/kogel1.png"));
                }
            }
        });
    }

}
