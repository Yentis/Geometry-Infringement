package GUI;

import GComponents.GLabel;
import GComponents.GPanel;
import Game.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import javax.swing.*;
import javax.swing.Timer;


/**
 * Created by Yentl-PC on 9/11/2016.
 */

public class GamePanel extends GPanel {
    private GamePanel panel = this;
    private Schip schip;
    private Schip schipp2;
    private Drone drone;
    private Drone dronep2;
    private ArrayList<Enemy> enemyOnField = new ArrayList<Enemy>();
    private List<Enemy> enemies = new ArrayList<>();
    private int enemyCounter;
    private Timer spawnTimer;
    private Timer invulnerabilityTimer;
    private Timer slowerEnemiesTimer;
    private GLabel combo;
    private GLabel combop2;
    private GLabel score;
    private GLabel scorep2;
    private GLabel schipbarp1;
    private GLabel dronebarp1;
    private JProgressBar currentSchipXpBar;
    private JProgressBar currentDroneXpBar;
    private JProgressBar currentHealthBar;
    private JProgressBar currentHealthBarp2;
    private double healthBarWidth;
    private double healthBarWidthp2;
    private double xpBarWidthSchip;
    private double xpBarWidthDrone;
    private double ratioHP;
    private double ratioSchipXP;
    private double ratioDroneXp;
    private boolean coop;
    private boolean gameFinished;
    private int baseDamage = 50;
    private int wave = 1; //@laurens wave kan je ook gwn geven als enemycounter for now right
    private Timer shootingDroneTimer;
    //private HashMap<String, Boolean> activeBuffs = new HashMap<String, Boolean>(); //TODO upgrade
    private Rectangle2D slowerEnemies;

    public GamePanel(List<Enemy> enemies) throws IOException, FontFormatException {
        this.enemies = enemies;
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);
        spawnEnemies();
        initGamePanel();
    }


    public boolean getGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void resetGame() {
        schip = null;
        drone = null;
        enemyCounter = 1;
    }

    private void initGamePanel() {
        try {
            initComponents();
            schipbarp1.setText(" 0 | xp: 0");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void initTimers() {
        setSlowerEnemiesTimer(schip);
        setInvulnerabilityTimer(schip);
        setUpShootingDroneTimer(drone);
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        //make Components
        combo = new GLabel("", 36f, 30, 620, 200, 60, false, Color.white);
        score = new GLabel("", 30f, 140, 65, 300, 60, false, Color.white);

        GLabel schipLvlp1 = new GLabel("Ship:", 24, 25, 667, 222, 62, false, new Color(50, 50, 255));
        GLabel droneLvlp1 = new GLabel("Drone:", 24, 315, 667, 222, 62, false, new Color(50, 50, 255));
        schipbarp1 = new GLabel("", 24, 100, 672, 200, 47, true, Color.gray);
        dronebarp1 = new GLabel("", 24, 415, 672, 200, 47, true, Color.gray);
        currentSchipXpBar = new JProgressBar();
        currentSchipXpBar.setBounds(100, 673, 0, 45);
        currentSchipXpBar.setBackground(new Color(50, 50, 255, 100));
        currentSchipXpBar.setOpaque(true);
        currentDroneXpBar = new JProgressBar();
        currentDroneXpBar.setBounds(415, 673, 0, 45);
        currentDroneXpBar.setBackground(new Color(50, 50, 255, 100));
        currentDroneXpBar.setOpaque(true);
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

        if (coop) {
            combop2 = new GLabel("", 36f, 580, 620, 100, 60, false, Color.white);
            scorep2 = new GLabel("", 30f, 950, 65, 300, 60, false, Color.white);
            currentHealthBarp2 = new JProgressBar();
            currentHealthBarp2.setBounds(575, 27, 425, 40);
            currentHealthBarp2.setBackground(new Color(0, 200, 0));
            currentHealthBarp2.setOpaque(true);
            scorep2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel.add(combop2);
            panel.add(scorep2);
            panel.add(currentHealthBarp2);
        }
        panel.add(combo);
        panel.add(score);
        panel.add(currentSchipXpBar);
        panel.add(currentHealthBar);
        panel.add(schipbarp1);
        panel.add(schipLvlp1);
        panel.add(dronebarp1);
        panel.add(droneLvlp1);
        panel.add(currentDroneXpBar);

        setAllComponentsVisible();
    }

    public void startGame() {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());

        gameFinished = false;
        spawnTimer.start();
        Schip dummy = window.getSpel().getSchepen().get(0);
        Drone dummydr = window.getSpel().getDrones().get(0);

        setSlowerEnemiesTimer(schip);
        setInvulnerabilityTimer(schip);

        try {
            initComponents();
            schipbarp1.setText(" lvl: 0 ");
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        schip = new Schip(dummy.getNr(), dummy.getHp(), dummy.getKracht(), dummy.getImageString(), dummy.getKeyLeft(), dummy.getKeyRight(), dummy.getKeyUp(), dummy.getKeyDown(), dummy.getSpeed());
        drone = new Drone(dummydr.getNr(), dummydr.getNaam(), dummydr.getBeschrijving(), dummydr.getHp(), dummydr.getKracht(), dummydr.getImageString());
        schip.setDrone(drone);

        if (coop) {
            schipp2 = new Schip(dummy.getNr(), dummy.getHp(), dummy.getKracht(), dummy.getImageString(), dummy.getKeyLeft(), dummy.getKeyRight(), dummy.getKeyUp(), dummy.getKeyDown(), dummy.getSpeed());
            Controllers controller = new Controllers(schipp2);
            dronep2 = new Drone(dummydr.getNr(), dummydr.getNaam(), dummydr.getBeschrijving(), dummydr.getHp(), dummydr.getKracht(), dummydr.getImageString());
            schipp2.setDrone(dronep2);

            setSlowerEnemiesTimer(schipp2);
            setInvulnerabilityTimer(schipp2);
        }
        initTimers();

        gameFinished = false;

    }

    public void pauseGame() {
        spawnTimer.stop();
    }

    public void resumeGame() {
        spawnTimer.start();
    }

   /* public void updateBuffs() {
        //wanneer de buff active is en nog niet in de activebuffs zit add je het
        for (HashMap.Entry<String, Boolean> buff : schip.getBuffs().entrySet()) {
            //als de buff active is en als de buff nog niet in de hashmap zit
            if (buff.getValue() && !activeBuffs.containsKey(buff.getKey())) {
                activeBuffs.put(buff.getKey(), buff.getValue());
                //anders als de buff false is en als de buff nog steeds in de hashmap zit
            } else if (!buff.getValue() && activeBuffs.containsKey(buff.getKey())) {
                activeBuffs.remove(buff.getKey());
            }
        }
    }*/

   /* public void drawBuffs(Graphics g) {
        //TODO probleem: het drawt maar 1 rectangle per keer
        Graphics2D g2d = (Graphics2D) g;
        if (activeBuffs.size() > 0) {
            //i = 1 want anders wordt het * 0
            for (int i = 1; i < activeBuffs.size() + 1; i++)
                if (slowerEnemies == null) {
                    slowerEnemies = new Rectangle2D.Double(10, 125 + 30 * i, 30, 30);
                } else {
                    slowerEnemies.setRect(10, 125 * i, 30, 30);
                }
            g2d.draw(slowerEnemies);
        }
    }*/

    //paints the "draw" region
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawBullets(g, schip.getKogels(), schip);
        drawBullets(g, drone.getKogels(), schip);
        drawShip(g, schip);
        drawDrone(g, drone, schip);
        drawEnemy(g);
        if (coop) {
            drawBullets(g, schipp2.getKogels(), schipp2);
            drawBullets(g, dronep2.getKogels(), schipp2);
            drawShip(g, schipp2);
            drawDrone(g, dronep2, schipp2);
        }
    }


    //region draw
    private void drawShip(Graphics g, Schip schip) {
        Graphics2D g2d = (Graphics2D) g;
        schip.draw(g2d, schip.getCurrentAngle());
        schipHit(schip);
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
        drone.setCurrentLocation(schip.getCurrentLocation().getX() + 75, schip.getCurrentLocation().getY() + 75);

        g2d.drawImage(drone.getImage(), drone.getCurrentLocation().x, drone.getCurrentLocation().y, this);
        g2d.setTransform(old);
    }

    private void drawBullets(Graphics g, ArrayList<Kogel> kogels, Schip schip) {
        Graphics2D g2d = (Graphics2D) g;
        if (!kogels.isEmpty()) {


            for (Iterator<Kogel> kogelIterator = kogels.iterator(); kogelIterator.hasNext(); ) {
                Kogel k = kogelIterator.next();
                if (k.isOutOfBorder(k.getCurrentLocation().getX(), 60, 925) || k.isOutOfBorder(k.getCurrentLocation().getY(), 125, 600)) {
                    kogelIterator.remove();
                }
                k.draw(g2d, k.getDirection(k.getTarget(), k.getStartingPoint()));

                for (Enemy enemy : enemyOnField) {
                    //wanneer isHit true is verdwijnt de bullet
                    if (k.collisionDetect(enemy.getHitBox())) {
                        k.setHit(true);
                        enemy.setHit(true);
                        //TODO combo bepalen en upgrades uitvoeren
                        if (schip.getHp() < 100 && schip.isLifesteal()) {
                            schip.addHp(2);
                        }
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
            enemy.drawHPBar(g2d);
            if (coop) {
                enemy.draw(g2d, enemy.getDirection(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation()));
            } else {
                enemy.draw(g2d, enemy.getDirection(schip.getCurrentLocation(), enemy.getCurrentLocation()));
            }
        }
    }

    public void checkBullet() {

    }

    //endregion

    //region hiteffect and stuff
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
                //System.out.println(enemy.getHP());
            }
        }
    }
    //endregion

    //region closestTarget
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
    //endregion

    //region updates
    private void updateKogels(ArrayList<Kogel> kogels, Schip schip) {
        for (Iterator<Kogel> kogeliterator = kogels.iterator(); kogeliterator.hasNext(); ) {
            Kogel k = kogeliterator.next();
            k.updateLocation(k.getTarget(), k.getStartingPoint(), k.getKogelSnelheid());
            for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
                Enemy enemy = enemyIterator.next();
                if (k.isHit() && enemy.isHit()) {
                    enemy.loseHP(baseDamage);
                    enemy.setHit(false);
                    if (enemy.getHP() <= 0) {
                        enemyIterator.remove();
                        schip.addCombo();
                        if (kogels == schip.getDrone().getKogels()) {
                            schip.getDrone().addCurrentXp(20);
                            //currentDroneXpBar.setSize((int) updateDroneXpBar(xpBarWidthDrone, enemy.getHitter()), currentDroneXpBar.getHeight());
                        } else if (kogels == schip.getKogels()) {
                            schip.addCurrentXp(20);
                            schip.checkLevel();
                            //currentSchipXpBar.setSize((int) updateSchipXpBar(xpBarWidthSchip, enemy.getHitter()), currentSchipXpBar.getHeight());
                            schip.checkForUpgrade(schip.getCombo());
                            if (schip.isDroneActive()) {
                                shootingDroneTimer.start();
                            }
                        }
                    }

                    kogeliterator.remove();
                }
            }
        }
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
        }
    }


    private double updateHealthBar(Schip schip, double healthBarWidth, JProgressBar currentHealthBar) {
        if (schip.getHp() != 0) {
            ratioHP = 425 / schip.getMaxhp();
            healthBarWidth = (int) ratioHP * schip.getHp();
        } else {
            //TODO
            gameFinished = true;
        }
        return healthBarWidth;
    }
    //endregion


    //updates the "updates" region
    public void update() {
        if (schip != null) {
            //updateBuffs();
            updateKogels(schip.getKogels(), schip);
            updateKogels(drone.getKogels(), schip);
            approachShip();
            schip.beweegSchip();

            if (schip.isInvulnerability()) {


                //System.out.println("invulnerability start");
                invulnerabilityTimer.start();
            }
            if (schip.isSlowerEnemies()) {
                //System.out.println("slower enemies");
                slowerEnemiesTimer.start();
            }

            combo.setText("x " + schip.getCombo());
            score.setText("" + schip.getScore());
            currentHealthBar.setSize((int) updateHealthBar(schip, healthBarWidth), currentHealthBar.getHeight());
            if (coop) {
                updateKogels(schipp2.getKogels(), schipp2);
                updateKogels(dronep2.getKogels(), schipp2);
                schipp2.beweegSchip();

                if (schipp2.isInvulnerability()) {

                    System.out.println("invulnerability start");
                    invulnerabilityTimer.start();
                }
                if (schipp2.isSlowerEnemies()) {
                    System.out.println("slower enemies");
                    slowerEnemiesTimer.start();
                }

                combop2.setText("x " + schipp2.getCombo());
                scorep2.setText("" + schipp2.getScore());
                currentHealthBarp2.setSize((int) updateHealthBar(schipp2, healthBarWidthp2, currentHealthBarp2), currentHealthBarp2.getHeight());
            }
            combo.setText("x " + schip.getCombo());
            score.setText("" + schip.getScore());
            currentHealthBar.setSize((int) updateHealthBar(schip, healthBarWidth, currentHealthBar), currentHealthBar.getHeight());
            currentSchipXpBar.setSize((int) updateSchipXpBar(xpBarWidthSchip, schip), currentSchipXpBar.getHeight());
        }
    }

    private double updateHealthBar(Schip schip, double healthBarWidth) {
        if (schip.getHp() != 0) {
            ratioHP = 425 / schip.getMaxhp();
            healthBarWidth = (int) ratioHP * schip.getHp();
        } else {
            //TODO
            gameFinished = true;

            System.out.println("invulnerability start");
            invulnerabilityTimer.start();
        }
        if (schip.isSlowerEnemies()) {
            //System.out.println("slower enemies");
            slowerEnemiesTimer.start();
        }

        return healthBarWidth;
    }


    private double updateSchipXpBar(double xpBarWidthSchip, Schip schip) {
        ratioSchipXP = 190 / schip.getMaxXp();
        xpBarWidthSchip = ratioSchipXP * schip.getCurrentXp();
        schipbarp1.setText(" lvl: " + schip.getLevel());
        return xpBarWidthSchip;

    }

    private double updateDroneXpBar(double xpBarWidthDrone, Schip schip) {
        ratioDroneXp = 200 / drone.getMaxXp();
        xpBarWidthDrone = ratioDroneXp * drone.getCurrentXp();
        dronebarp1.setText(" lvl: " + drone.getLevel());
        return xpBarWidthDrone;


    }


    //region Timers
    public void setInvulnerabilityTimer(Schip schip) {
        invulnerabilityTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!schip.isNull()) {
                    schip.setInvulnerability(false);
                    invulnerabilityTimer.stop();
                }
            }
        });
    }

    public void setSlowerEnemiesTimer(Schip schip) {
        slowerEnemiesTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schip.setSlowerEnemies(false);
                slowerEnemiesTimer.stop();

            }
        });
    }

    private void setUpShootingDroneTimer(Drone drone) {
        if (!schip.isNull()) {
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

    public void spawnEnemies() {
        Enemy testenemy = enemies.get(0);
        ImageIcon ii = new ImageIcon(testenemy.getImage());

        spawnTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < enemyCounter; i++) {


                    enemyOnField.add(new Enemy(testenemy.getNr(), testenemy.getNaam(), testenemy.getBeschrijving(), testenemy.getHP(), testenemy.getKracht(), testenemy.getImageString(), testenemy.getExperience(), testenemy.getScore(), testenemy.getSpeed()));

                }
                enemyCounter++;
            }
        });
    }
    //endregion

    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    //region mouse and key events

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

    //endregion

}