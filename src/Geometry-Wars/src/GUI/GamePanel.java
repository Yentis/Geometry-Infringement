package GUI;

import GComponents.GLabel;
import GComponents.GPane;
import GComponents.GPanel;
import Game.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Objects;

import javax.sound.sampled.UnsupportedAudioFileException;
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
    private GLabel schipbarp2;
    private GLabel dronebarp2;
    private JProgressBar currentSchipXpBar;
    private JProgressBar currentDroneXpBar;
    private JProgressBar currentHealthBar;
    private JProgressBar currentHealthBarp2;
    private JProgressBar currentSchipXpBarp2;
    private JProgressBar currentDroneXpBarp2;
    private double healthBarWidth;
    private double healthBarWidthp2;
    private double xpBarWidthSchip;
    private double xpBarWidthDrone;
    private double ratioHP;
    private double ratioSchipXP;
    private double ratioDroneXp;
    private boolean coop;
    private boolean gameFinished;
    private Timer shootingDroneTimer;
    private boolean soundExecuted;


    public GamePanel(List<Enemy> enemies) throws IOException, FontFormatException {
        this.enemies = enemies;
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);
        spawnEnemies();
        initGamePanel();
        initComponents();
    }

    public int getScore() {
        return Integer.parseInt(score.getText());
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void resetGame() {
        schip = null;
        schipp2 = null;
    }

    private void initGamePanel() {
        try {
            initComponents();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void initTimers() {
        if(drone != null && schip != null){
            setUpShootingDroneTimer(drone);
        }
        setSlowerEnemiesTimer(schip);
        setInvulnerabilityTimer(schip);

    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        //make Components
        combo = new GLabel("", 24f, 30, 620, 200, 60, false, Color.white);
        score = new GLabel("", 30f, 140, 65, 300, 60, false, Color.white);

        GLabel schipLvlp1 = new GLabel("Ship:", 20, 25, 680, 222, 62, false, Color.green);

        GLabel droneLvlp1 = new GLabel("Drone:", 20, 250, 680, 222, 62, false, new Color(155, 255, 204));
        JLabel schipbarp1Pane = new GPane(100, 695, 125, 35);
        JLabel dronebarp1Pane = new GPane(350, 695, 125, 35);
        schipbarp1 = new GLabel("", 20, 100, 660, 100, 47, false, Color.white);
        dronebarp1 = new GLabel("", 20, 350, 660, 100, 47, false, Color.white);

        schipbarp2 = new GLabel("", 20, 670, 660, 125, 35, false, Color.white);
        dronebarp2 = new GLabel("", 20, 880, 660, 125, 35, false, Color.white);

        //Schip 1
        currentSchipXpBar = new JProgressBar();
        currentSchipXpBar.setBounds(100, 695, 0, 35);
        currentSchipXpBar.setBackground(new Color(50, 50, 255));
        currentSchipXpBar.setOpaque(true);
        currentDroneXpBar = new JProgressBar();
        currentDroneXpBar.setBounds(415, 695, 0, 35);
        currentDroneXpBar.setBackground(new Color(50, 50, 255));
        currentDroneXpBar.setOpaque(true);
        currentHealthBar = new JProgressBar();
        currentHealthBar.setBounds(20, 27, 0, 40);
        currentHealthBar.setBackground(new Color(0, 200, 0));
        currentHealthBar.setOpaque(true);

        //Schip 2
        currentHealthBarp2 = new JProgressBar();
        currentHealthBarp2.setBounds(575, 27, 0, 40);
        currentHealthBarp2.setBackground(new Color(0, 200, 0));
        currentHealthBarp2.setOpaque(true);
        currentDroneXpBarp2 = new JProgressBar();
        currentDroneXpBarp2.setBounds(880, 695, 0, 35);
        currentDroneXpBarp2.setBackground(new Color(50, 50, 255));
        currentDroneXpBarp2.setOpaque(true);
        currentSchipXpBarp2 = new JProgressBar();
        currentSchipXpBarp2.setBounds(660, 695, 0, 35);
        currentSchipXpBarp2.setBackground(new Color(50, 50, 255));
        currentSchipXpBarp2.setOpaque(true);

        //score rechts uitlijnen
        score.setHorizontalAlignment(SwingConstants.RIGHT);

        //Add components to panel

        combop2 = new GLabel("", 24f, 920, 620, 100, 60, false, Color.white);
        scorep2 = new GLabel("", 30f, 700, 65, 300, 60, false, Color.white);

        scorep2.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(combo);
        panel.add(score);

        panel.add(schipbarp1);
        panel.add(dronebarp1);
        panel.add(currentSchipXpBar);
        panel.add(currentDroneXpBar);
        panel.add(currentHealthBar);

        panel.add(combop2);
        panel.add(scorep2);
        panel.add(currentHealthBarp2);
        panel.add(currentDroneXpBarp2);
        panel.add(currentSchipXpBarp2);
        panel.add(schipbarp2);
        panel.add(dronebarp2);

        panel.add(schipbarp1Pane);
        panel.add(dronebarp1Pane);
        panel.add(schipLvlp1);
        panel.add(droneLvlp1);
        setAllComponentsVisible();
    }

    private void showCoopUI() {
        currentHealthBarp2.setVisible(true);
        combop2.setVisible(true);
        scorep2.setVisible(true);
    }

    private void hideCoopUI() {
        currentHealthBarp2.setVisible(false);
        combop2.setVisible(false);
        scorep2.setVisible(false);
    }


    private void clearUI() {
        score.setText("");
        combo.setText("x");
        currentHealthBar.setSize(0, currentHealthBar.getHeight());
        currentSchipXpBar.setSize(0, currentSchipXpBar.getHeight());
        currentDroneXpBar.setSize(0, currentDroneXpBar.getHeight());
        if (coop) {
            scorep2.setText("");
            combop2.setText("x");
            currentHealthBarp2.setSize(0, currentHealthBarp2.getHeight());
        }
    }

    private Drone makeDrone(Schip schip){
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        Drone dummydr = null;

        if(schip.getUpgrades().contains(4)){
            dummydr = window.getSpel().getDrones().get(0);
        } else if (schip.getUpgrades().contains(5)){
            dummydr = window.getSpel().getDrones().get(1);
        } else if (schip.getUpgrades().contains(6)){
            dummydr = window.getSpel().getDrones().get(2);
        }

        return dummydr;
    }

    public void startGame() throws SQLException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Integer> upgrades = new ArrayList<>();
        upgrades = window.getSpel().checkUpgrades();
        enemyOnField.clear(); //lol
        enemyCounter = 1;
        gameFinished = false;
        spawnTimer.start();

        Schip dummy = window.getSpel().getSchepen().get(0);
        Drone dummydr = null;

        //TODO: player can chose which drone he want

        requestFocus();
        setSlowerEnemiesTimer(schip);
        setInvulnerabilityTimer(schip);

        schip = new Schip(dummy.getNr(), dummy.getHp(), dummy.getKracht(), dummy.getImageString(), dummy.getKeyLeft(), dummy.getKeyRight(), dummy.getKeyUp(), dummy.getKeyDown(), dummy.getSpeed(), upgrades);

        if(!Objects.equals(window.getSpel().getCurrentControls(), "Keyboard + Mouse")){
            Controllers controller = new Controllers(schip, 0);
        }

        dummydr = makeDrone(schip);

        if (dummydr != null){
            drone = new Drone(dummydr.getNr(), dummydr.getNaam(), dummydr.getBeschrijving(), dummydr.getKracht(), dummydr.getImageString(), dummydr.getType());
            schip.setDrone(drone);
        }

        if (schip.getUpgrades().contains(2)){
            schip.setSpeed(5);
        }
        if (coop) {
            //set layouts
            showCoopUI();
            schipp2 = new Schip(dummy.getNr(), dummy.getHp(), dummy.getKracht(), dummy.getImageString(), dummy.getKeyLeft(), dummy.getKeyRight(), dummy.getKeyUp(), dummy.getKeyDown(), dummy.getSpeed(), upgrades);
            try {
                Controllers controller2 = new Controllers(schipp2, 1);

            } catch (IndexOutOfBoundsException e) {
                try {
                    Controllers controller2 = new Controllers(schipp2, 0);
                } catch (IndexOutOfBoundsException ef) {

                }
            }

            dummydr = makeDrone(schipp2);

            if (dummydr != null){
                dronep2 = new Drone(dummydr.getNr(), dummydr.getNaam(), dummydr.getBeschrijving(), dummydr.getKracht(), dummydr.getImageString(), dummydr.getType());
                schipp2.setDrone(dronep2);
            }

            setSlowerEnemiesTimer(schipp2);
            setInvulnerabilityTimer(schipp2);

        } else {
            hideCoopUI();
        }
        initTimers();
        clearUI();

    }

    public void pauseGame() {
        spawnTimer.stop();
    }

    public void resumeGame() {
        spawnTimer.start();
    }


    //paints the "draw" region
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameFinished){
            drawEnemy(g);
        }
        if (schip != null) {
            drawBullets(g, schip.getKogels(), schip);
            drawShip(g, schip);
            drawBuffs(g, schip, schipbarp1);
        }
        if (drone != null && schip != null){
            drawBullets(g, drone.getKogels(), schip);
            drawDrone(g, drone, schip);
        }
        if (coop && schipp2 != null) {
            drawBullets(g, schipp2.getKogels(), schipp2);
            drawShip(g, schipp2);
            drawBuffs(g,schipp2,schipbarp2);
        }
        if (coop && dronep2 != null && schipp2 != null){

            drawBullets(g, dronep2.getKogels(), schipp2);
            drawDrone(g, dronep2, schipp2);
        }
    }


    //region draw
    private void drawBuffs(Graphics g, Schip schip, GLabel xpbar) {
        Graphics2D g2d = (Graphics2D) g;
        if (schip.getActiveBuffs().size() > 0) {
            for (int i = 0; i < schip.getActiveBuffs().size(); i++) {
                g2d.drawImage(schip.getActiveBuffs().get(i).getImage(), xpbar.getX() + xpbar.getWidth() - 35 * (i + 1), 630, 30, 30, null);
            }
        }
    }

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
                        if (schip.getHp() < 100 && schip.getLifesteal().isActive()) {
                            schip.getLifesteal().doFunction();
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


    //endregion

    //region hiteffect and stuff
    private void schipHit(Schip schip) {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (schip.collisionDetect(enemy.getHitBox())) {
                schip.setHit(true);
                if (!schip.getInvulnerability().isActive()) {
                    schip.loseHP(enemy.getKracht());
                    schip.resetCombo();
                }
                enemyIterator.remove();
            }
        }
    }
    //endregion

    //region closestTarget
    private Schip closestShip(Enemy enemy) {
        //berekent distance tussen 2 points
        if (schip != null && schipp2 != null) {
            int distancep1 = (int) (enemy.getCurrentLocation().distanceSq(schip.getCurrentLocation()));
            int distancep2 = (int) (enemy.getCurrentLocation().distanceSq(schipp2.getCurrentLocation()));
            if (distancep1 < distancep2) {
                return schip;
            } else {
                return schipp2;
            }
        } else if (schip == null) {
            return schipp2;
        } else {
            return schip;
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
                    enemy.loseHP(schip.getKracht());
                    enemy.setHit(false);
                    if (enemy.getHP() <= 0) {

                        enemyIterator.remove();
                        if (schip.getCombo() < 999){
                            schip.addCombo();
                        }


                        if (drone != null && kogels == schip.getDrone().getKogels()) {
                            if (Objects.equals(drone.getNaam(), "Experience Drone")){
                                schip.addCurrentXp(enemy.getExperience());
                            } else {
                                schip.getDrone().addCurrentXp(enemy.getExperience());
                            }
                           
                            drone.checkLevel();
                        } else if (kogels == schip.getKogels()) {
                            schip.addCurrentXp(enemy.getExperience());
                            schip.checkLevel();
                            schip.checkForUpgrade(schip.getCombo());
                            if (schip.getActiveDrone().isActive()) {
                                shootingDroneTimer.start();
                            }
                        }
                    }
                }
            }
        }
        clearHitBullets(kogels);
    }

    private void clearHitBullets(ArrayList<Kogel> kogels) {
        for (Iterator<Kogel> kogelIterator = kogels.iterator(); kogelIterator.hasNext(); ) {
            Kogel k = kogelIterator.next();
            if (k.isHit()) {
                kogelIterator.remove();

            }
        }
    }

    private void collisionDrone(Drone drone){
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ){
            Enemy enemy = enemyIterator.next();
            if (drone.collisionDetect(enemy.getHitBox())){
                System.out.println("hi");
                drone.addCurrentXp(enemy.getExperience());
                enemyIterator.remove();
            }
        }
    }


    private void approachShip() {
        for (Enemy enemy : enemyOnField) {
            if (coop) {
                if (schip != null || schipp2 != null) {
                    if (schip != null) {
                        schip.getSlowEnemies().doFunction(enemy);
                    }
                    if (schipp2 != null) {
                        schipp2.getSlowEnemies().doFunction(enemy);
                    }
                    enemy.updateLocation(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
                } else {
                    if (schip != null) {
                        enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
                    }
                    if (schipp2 != null) {
                        enemy.updateLocation(schipp2.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
                    }

                }

            } else {
                schip.getSlowEnemies().doFunction(enemy);
                enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
            }
        }
    }


    //endregion


    //updates the "updates" region
    public void update() throws IOException, UnsupportedAudioFileException {
        checkDeadShip();
        if (schip != null || schipp2 != null) {
            checkGameFinished();
            approachShip();
            if (schip != null) {
                schip.updateBuffs();
                if (drone != null && (Objects.equals(drone.getNaam(), "Attack Drone") || Objects.equals(drone.getNaam(), "Experience Drone"))){
                    updateKogels(drone.getKogels(), schip);
                    currentDroneXpBar.setSize((int) updateDroneXpBar(xpBarWidthDrone, drone), currentDroneXpBar.getHeight());
                } else if (drone != null && Objects.equals(drone.getNaam(), "Defense Drone")){
                    collisionDrone(drone);
                    currentDroneXpBar.setSize((int) updateDroneXpBar(xpBarWidthDrone, drone), currentDroneXpBar.getHeight());
                }
                schip.beweegSchip();
                updateKogels(schip.getKogels(), schip);
                if (schip.getInvulnerability().isActive()) {

                    invulnerabilityTimer.start();
                }
                if (schip.getSlowEnemies().isActive()) {
                    //System.out.println("slower enemies");

                    slowerEnemiesTimer.start();
                }

                combo.setText("x " + schip.getCombo());
                score.setText("" + schip.getScore());
                currentHealthBar.setSize((int) updateHealthBar(schip), currentHealthBar.getHeight());
                currentSchipXpBar.setSize((int) updateSchipXpBar(xpBarWidthSchip, schip), currentSchipXpBar.getHeight());
                schipbarp1.setText(" lvl: " + schip.getLevel());
            }
        }
        if (coop && schipp2 != null) {
            checkDeadShip();
            if (schipp2 != null) {
                schipp2.updateBuffs();
                updateKogels(schipp2.getKogels(), schipp2);
                if(dronep2 != null){
                    updateKogels(dronep2.getKogels(), schipp2);

                    currentDroneXpBarp2.setSize((int) updateDroneXpBar(xpBarWidthDrone, dronep2), currentDroneXpBarp2.getHeight());
                    dronebarp2.setText("lvl: " +  schipp2.getDrone().getLevel());
                    currentDroneXpBarp2.setSize((int) updateDroneXpBar(xpBarWidthDrone, dronep2), currentDroneXpBarp2.getHeight());
                }
                schipp2.beweegSchip();
                if (schipp2.getInvulnerability().isActive()) {
                    invulnerabilityTimer.start();
                }
                if (schipp2.getSlowEnemies().isActive()) {
                    slowerEnemiesTimer.start();
                }
                combop2.setText("x " + schipp2.getCombo());
                scorep2.setText("" + schipp2.getScore());
                currentHealthBarp2.setSize((int) updateHealthBar(schipp2), currentHealthBarp2.getHeight());
                currentSchipXpBarp2.setSize((int) updateSchipXpBar(xpBarWidthSchip, schipp2), currentSchipXpBarp2.getHeight());
                schipbarp2.setText("lvl: " +  schipp2.getLevel());
            }
        }
    }

    private void checkDeadShip() {
        if (coop) {
            if (schip != null && schip.getHp() <= 0) {
                schip = null;
            }
            if (schipp2 != null && schipp2.getHp() <= 0) {
                schipp2 = null;
            }
            if (schip == null && schipp2 == null) {
                gameFinished = true;
            }
        }
    }

    private double updateHealthBar(Schip schip) {
        if (schip.getHp() >= 0) {
            ratioHP = 432 / schip.getMaxhp();
            healthBarWidth = ratioHP * schip.getHp();
        }
        return healthBarWidth;
    }

    private void checkGameFinished() throws IOException, UnsupportedAudioFileException {
        if (coop) {
            if(schip == null && !soundExecuted){
                Sound sound = new Sound("resources/Sound/playerdeath.wav");
                soundExecuted = true;
            }

            if (schipp2 == null && !soundExecuted){
                Sound sound = new Sound("resources/Sound/playerdeath.wav");
                soundExecuted = true;
            }

            if (schip == null && schipp2 == null) {
                Sound sound = new Sound("resources/Sound/playerdeath.wav");
                gameFinished = true;
            }
        } else {
            if (schip.getHp() <= 0) {
                Sound sound = new Sound("resources/Sound/playerdeath.wav");
                gameFinished = true;
            }
        }
    }


    private double updateSchipXpBar(double xpBarWidthSchip, Schip schip) {
        ratioSchipXP = 100 / schip.getMaxXp();
        xpBarWidthSchip = ratioSchipXP * schip.getCurrentXp();


        return xpBarWidthSchip;

    }

    private double updateDroneXpBar(double xpBarWidthDrone, Drone drone) {
        ratioDroneXp = 100 / drone.getMaxXp();
        xpBarWidthDrone = ratioDroneXp * drone.getCurrentXp();
        dronebarp1.setText(" lvl: " + drone.getLevel());
        return xpBarWidthDrone;
    }


    //region Timers

    public void setInvulnerabilityTimer(Schip schip) {
        invulnerabilityTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (schip != null) {
                    schip.getInvulnerability().setActive(false);
                    invulnerabilityTimer.stop();
                }
            }
        });
    }

    public void setSlowerEnemiesTimer(Schip schip) {
        slowerEnemiesTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schip.getSlowEnemies().setActive(false);
                slowerEnemiesTimer.stop();

            }
        });
    }

    private void setUpShootingDroneTimer(Drone drone) {
        shootingDroneTimer = new Timer(drone.getFireSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enemyOnField.isEmpty()) {
                    drone.getKogels().add(new Kogel(drone.getCurrentLocation().getX(), drone.getCurrentLocation().getY(), closestEnemy(drone, enemyOnField), "resources/Media/kogel1.png"));
                }
            }
        });
    }

    public void spawnEnemies() {
        Enemy testenemy = enemies.get(0);
        ImageIcon ii = new ImageIcon(testenemy.getImage());

        spawnTimer = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enemyOnField.isEmpty()) {

                    if (enemyCounter % 20 == 0) {
                        Enemy boss1 = enemies.get(6);
                        enemyOnField.add(new Enemy(boss1.getNr(), boss1.getNaam(), boss1.getBeschrijving(), boss1.getHP(), boss1.getKracht(), boss1.getImageString(), boss1.getExperience(), boss1.getScore(), boss1.getSpeed()));
                        enemyCounter++;
                    } else {
                        for (int i = 0; i < enemyCounter; i++) {
                            enemyOnField.add(new Enemy(testenemy.getNr(), testenemy.getNaam(), testenemy.getBeschrijving(), testenemy.getHP(), testenemy.getKracht(), testenemy.getImageString(), testenemy.getExperience(), testenemy.getScore(), testenemy.getSpeed()));
                        }
                        enemyCounter++;
                        if (enemyCounter % 5 == 0) {
                            Enemy enemy2 = enemies.get(1);
                            enemyOnField.add(new Enemy(enemy2.getNr(), enemy2.getNaam(), enemy2.getBeschrijving(), enemy2.getHP(), enemy2.getKracht(), enemy2.getImageString(), enemy2.getExperience(), enemy2.getScore(), enemy2.getSpeed()));
                        }
                        if (enemyCounter % 2 == 0) {
                            Enemy enemy2 = enemies.get(2);
                            enemyOnField.add(new Enemy(enemy2.getNr(), enemy2.getNaam(), enemy2.getBeschrijving(), enemy2.getHP(), enemy2.getKracht(), enemy2.getImageString(), enemy2.getExperience(), enemy2.getScore(), enemy2.getSpeed()));
                        }
                        if (enemyCounter % 7 == 0) {
                            Enemy enemy2 = enemies.get(3);
                            enemyOnField.add(new Enemy(enemy2.getNr(), enemy2.getNaam(), enemy2.getBeschrijving(), enemy2.getHP(), enemy2.getKracht(), enemy2.getImageString(), enemy2.getExperience(), enemy2.getScore(), enemy2.getSpeed()));
                        }
                        if (enemyCounter % 9 == 0) {
                            Enemy enemy2 = enemies.get(4);
                            enemyOnField.add(new Enemy(enemy2.getNr(), enemy2.getNaam(), enemy2.getBeschrijving(), enemy2.getHP(), enemy2.getKracht(), enemy2.getImageString(), enemy2.getExperience(), enemy2.getScore(), enemy2.getSpeed()));
                        }
                        if (enemyCounter % 11 == 0) {
                            Enemy enemy2 = enemies.get(5);
                            enemyOnField.add(new Enemy(enemy2.getNr(), enemy2.getNaam(), enemy2.getBeschrijving(), enemy2.getHP(), enemy2.getKracht(), enemy2.getImageString(), enemy2.getExperience(), enemy2.getScore(), enemy2.getSpeed()));
                        }
                        if (enemyCounter % 13 == 0) {
                            Enemy enemy2 = enemies.get(6);
                            enemyOnField.add(new Enemy(enemy2.getNr(), enemy2.getNaam(), enemy2.getBeschrijving(), enemy2.getHP(), enemy2.getKracht(), enemy2.getImageString(), enemy2.getExperience(), enemy2.getScore(), enemy2.getSpeed()));
                        }
                        enemyCounter++;
                    }
                }

              if(schip != null && schip.getUpgrades().contains(2)){
                  healthRegen(schip);
              }
               if(schipp2 != null && coop && schipp2.getUpgrades().contains(2)){
                   healthRegen(schipp2);
               }
            }
        });
    }

    private void healthRegen(Schip schip){
        if (schip.getHp() < schip.getMaxhp()){
            schip.addHp(2);
        }
    }
    //endregion

    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    //region mouse and key events

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (schip != null) {
                schip.keyPressed(e);

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (schip != null) {
                schip.keyReleased(e);
            }
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
