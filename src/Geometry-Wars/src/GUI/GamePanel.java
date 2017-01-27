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

class GamePanel extends GPanel {
    //region Instance Variables

    private int enemyCounter;
    private int roundCounter;
    private double enemyPower = 1;
    private boolean coop;
    private boolean gameFinished;
    private boolean soundExecuted;
    private Point mouseLocation;
    private Schip schip;
    private Schip schipp2;
    private Drone drone;
    private Drone dronep2;
    private Timer spawnTimer;
    private Timer invulnerabilityTimer;
    private Timer slowerEnemiesTimer;
    private Timer shootingDroneTimer;
    private Timer mousePressedTimer;
    private JLabel combo;
    private JLabel combop2;
    private JLabel score;
    private JLabel scorep2;
    private JLabel schipbarp1;
    private JLabel dronebarp1;
    private JLabel schipbarp2;
    private JLabel dronebarp2;
    private JPanel panel = this;
    private JProgressBar currentSchipXpBar;
    private JProgressBar currentDroneXpBar;
    private JProgressBar currentHealthBar;
    private JProgressBar currentHealthBarp2;
    private JProgressBar currentSchipXpBarp2;
    private JProgressBar currentDroneXpBarp2;
    private List<Enemy> enemies = new ArrayList<>();
    private ArrayList<Enemy> enemyOnField = new ArrayList<>();
    private ArrayList<Effect> effects = new ArrayList<>();

    //endregion

    //region Constructors

    GamePanel(List<Enemy> enemies) throws IOException, FontFormatException {
        this.enemies = enemies;
        mouseLocation = new Point(0,0);
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);
        spawnEnemies();
        initGamePanel();
        initComponents();
    }

    //endregion

    //region Getters & Setters

    int getScore() {
        return Integer.parseInt(score.getText());
    }

    boolean getGameFinished() {
        return gameFinished;
    }

    void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    void setCoop(boolean coop) {
        this.coop = coop;
    }

    //endregion

    //region Behaviour

    private void initGamePanel() {
        try {
            initComponents();
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void initTimers() {
        if (schip.getDrone() != null && schip != null) {
            setUpShootingDroneTimer(schip.getDrone());
        }
        setSlowerEnemiesTimer(schip);
        setInvulnerabilityTimer(schip);
        if (coop && schipp2.getDrone() != null && schipp2 != null){
            setUpShootingDroneTimer(schipp2.getDrone());
            setSlowerEnemiesTimer(schipp2);
            setInvulnerabilityTimer(schipp2);
        }
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();
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
        currentDroneXpBar.setBounds(350, 695, 0, 35);
        currentDroneXpBar.setBackground(new Color(50, 50, 255));
        currentDroneXpBar.setOpaque(true);
        currentHealthBar = new JProgressBar();
        currentHealthBar.setBounds(20, 27, 0, 40);
        currentHealthBar.setBackground(Color.green);
        currentHealthBar.setOpaque(true);

        //Schip 2
        currentHealthBarp2 = new JProgressBar();
        currentHealthBarp2.setBounds(575, 27, 0, 40);
        currentHealthBarp2.setBackground(Color.green);
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
            currentSchipXpBarp2.setSize(0, currentSchipXpBarp2.getHeight());
            currentDroneXpBarp2.setSize(0, currentDroneXpBarp2.getHeight());
        }
    }

    private Drone makeDrone() {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        Drone dummydr;

        if (Objects.equals(window.getSpel().getSpeler().getActiveDrone(), "Defense Drone")) {
            dummydr = window.getSpel().getDrones().get(0);
        } else if (Objects.equals(window.getSpel().getSpeler().getActiveDrone(), "Experience Drone")) {
            dummydr = window.getSpel().getDrones().get(1);
        } else if (Objects.equals(window.getSpel().getSpeler().getActiveDrone(), "Attack Drone")) {
            dummydr = window.getSpel().getDrones().get(2);
        } else {
            dummydr = null;
        }
        return dummydr;
    }

    void startGame() throws SQLException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Integer> upgrades;
        upgrades = window.getSpel().checkUpgrades();
        enemyOnField.clear();
        effects.clear();
        enemyCounter = 3;
        roundCounter = 1;
        enemyPower = 1;
        gameFinished = false;
        spawnTimer.start();

        requestFocus();

        Schip dummy = window.getSpel().getSchepen().get(0);
        Drone dummydr;

        schip = new Schip(dummy.getNr(), dummy.getHp(), dummy.getKracht(), dummy.getImageString(), dummy.getKeyLeft(), dummy.getKeyRight(), dummy.getKeyUp(), dummy.getKeyDown(), dummy.getSpeed(), upgrades);

        setSlowerEnemiesTimer(schip);
        setInvulnerabilityTimer(schip);

        if (Objects.equals(window.getSpel().getCurrentControls(), "Keyboard + Mouse")) {
            addKeyListener(new TAdapter());
            MouseAdapter adapter = new MAdapter();
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
            setUpMousePressedTimer(150);
        } else {
            new Controllers(schip, 0);
        }

        dummydr = makeDrone();

        if (dummydr != null) {
            drone = new Drone(dummydr.getNr(), dummydr.getNaam(), dummydr.getBeschrijving(), dummydr.getKracht(), dummydr.getImageString(), dummydr.getType());
            schip.setDrone(drone);
        }

        if (schip.getUpgrades().contains(2)) {
            schip.setSpeed(5);
        }

        if (coop) {
            //set layouts
            showCoopUI();
            schipp2 = new Schip(dummy.getNr(), dummy.getHp(), dummy.getKracht(), dummy.getImageString(), dummy.getKeyLeft(), dummy.getKeyRight(), dummy.getKeyUp(), dummy.getKeyDown(), dummy.getSpeed(), upgrades);

            setSlowerEnemiesTimer(schipp2);
            setInvulnerabilityTimer(schipp2);

            new Controllers(schipp2, 1);

            if (dummydr != null) {
                dronep2 = new Drone(dummydr.getNr(), dummydr.getNaam(), dummydr.getBeschrijving(), dummydr.getKracht(), dummydr.getImageString(), dummydr.getType());
                schipp2.setDrone(dronep2);
            }

            if (schipp2.getUpgrades().contains(2)) {
                schipp2.setSpeed(5);
            }
        } else {
            hideCoopUI();
        }
        initTimers();
        clearUI();
    }

    void pauseGame() {
        spawnTimer.stop();
    }

    void resumeGame() {
        spawnTimer.start();
    }

    @Override
    //paints the "draw" region
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameFinished) {
            drawEnemy(g);
            drawCollisionEffects(g);
        }
        if (schip != null) {
            drawBullets(g, schip.getKogels(), schip);
            drawShip(g, schip);
            drawBuffs(g, schip, 10);
        }
        if (schip != null && schip.getDrone() != null) {
            drawBullets(g, schip.getDrone().getKogels(), schip);
            drawDrone(g, schip.getDrone(), schip);
        }
        if (coop && schipp2 != null) {
            drawBullets(g, schipp2.getKogels(), schipp2);
            drawShip(g, schipp2);
            drawBuffs(g, schipp2, 955);
        }
        if (coop && schipp2 != null && schipp2.getDrone() != null) {
            drawBullets(g, schipp2.getDrone().getKogels(), schipp2);
            drawDrone(g, schipp2.getDrone(), schipp2);
        }
    }

    //region Draw

    private void drawBuffs(Graphics g, Schip schip, int xCoordinaat) {
        Graphics2D g2d = (Graphics2D) g;
        if (schip.getActiveBuffs().size() > 0) {
            for (int i = 0; i < schip.getActiveBuffs().size(); i++) {
                g2d.drawImage(schip.getActiveBuffs().get(i).getImage(), xCoordinaat, 120 + 35 * i, 40, 40, null);
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
        drone.drawHitBox();
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
        for (Enemy enemy : enemyOnField) {
            if (enemy.getHP() >= 0) {
                enemy.drawHPBar(g2d);
                if (coop) {
                    enemy.draw(g2d, enemy.getDirection(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation()));
                } else {
                    enemy.draw(g2d, enemy.getDirection(schip.getCurrentLocation(), enemy.getCurrentLocation()));
                }
            }
        }
    }

    //@Renzie, gets laggy
    private void drawCollisionEffects(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (Effect effect : effects){
            g2d.setFont(effect.getFont());
            g2d.setColor(effect.getColor());
            g2d.drawString(effect.getText(), effect.getCurrentLocation().x, effect.getCurrentLocation().y);
        }
    }

    //endregion

    private void schipHit(Schip schip) {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (schip.collisionDetect(enemy.getHitBox())) {
                schip.setHit(true);
                if (!schip.getInvulnerability().isActive()) {
                    new Sound("playerhit");
                    new Thread(new HitFlash(schip)).start();
                    schip.loseHP(enemy.getKracht());
                    try {
                        effects.add(Effect.takeDamage(enemy));
                    } catch (IOException | FontFormatException e) {
                        e.printStackTrace();
                    }
                    schip.resetCombo();
                }
                enemyIterator.remove();
            }
        }
    }

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

        for (Enemy enemy : enemies) {
            if (drone.getCurrentLocation().distanceSq(enemy.getCurrentLocation()) < drone.getCurrentLocation().distanceSq(closestEnemy)) {
                closestEnemy.setLocation(enemy.getCurrentLocation());
            }
        }
        return closestEnemy;
    }

    //region Updates

    private void updateKogels(ArrayList<Kogel> kogels, Schip schip) {
        for (Kogel k : kogels) {
            k.updateLocation(k.getTarget(), k.getStartingPoint(), k.getKogelSnelheid());
            for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
                Enemy enemy = enemyIterator.next();
                if (k.isHit() && enemy.isHit()) {
                    enemy.loseHP(schip.getKracht());
                    enemy.setHit(false);
                    if (enemy.getHP() <= 0) {
                        new Sound("enemydeath");
                        try {
                            effects.add(Effect.XPGain(enemy));
                        } catch (IOException | FontFormatException e) {
                            e.printStackTrace();
                        }
                        enemyIterator.remove();
                        schip.addCombo();
                        schip.addScore(enemy.getScore(), schip.getCombo());


                        if (schip.getDrone() != null && kogels == schip.getDrone().getKogels()) {
                            if (Objects.equals(schip.getDrone().getNaam(), "Experience Drone")) {
                                schip.addCurrentXp(enemy.getExperience());
                            } else {
                                schip.getDrone().addCurrentXp(enemy.getExperience());
                            }

                            schip.getDrone().checkLevel();
                        } else if (kogels == schip.getKogels()) {
                            schip.addCurrentXp(enemy.getExperience());
                            schip.checkLevel();
                            if (schip.isLeveled()){
                                effects.add(Effect.levelUp(schip));
                            }
                            schip.checkForUpgrade();
                            if (schip.getDrone() != null && schip.getActiveDrone().isActive()) {
                                shootingDroneTimer.start();
                            }
                        }
                    }
                }
            }
        }
        clearHitBullets(kogels);
    }

    private void updateAnimations(){
        for (Iterator<Effect> killAnimationIterator = effects.iterator(); killAnimationIterator.hasNext() ; ){
            Effect effect = killAnimationIterator.next();
            if (System.currentTimeMillis() > effect.getDisappearTime()){
                killAnimationIterator.remove();
            } else {
                effect.setCurrentLocation(effect.getCurrentLocation().getX(), effect.getCurrentLocation().getY() - effect.getSpeed());
            }
        }
    }

    private void clearHitBullets(ArrayList<Kogel> kogels) {
        for (Iterator<Kogel> kogelIterator = kogels.iterator(); kogelIterator.hasNext(); ) {
            Kogel k = kogelIterator.next();
            if (k.isHit()) {
                kogelIterator.remove();
            }
        }
    }

    private void collisionDrone(Drone drone) {
        for (Iterator<Enemy> enemyIterator = enemyOnField.iterator(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            if (drone.collisionDetect(enemy.getHitBox())) {
                drone.addCurrentXp(enemy.getExperience());
                enemyIterator.remove();
            }
        }
    }

    private void approachShip() {
        for (Enemy enemy : enemyOnField) {
            if (coop) {
                if(schip != null && schipp2 != null) {
                    schip.getSlowEnemies().doFunction(enemy);
                    schipp2.getSlowEnemies().doFunction(enemy);
                    enemy.updateLocation(closestShip(enemy).getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
                } else if (schip == null){
                    enemy.updateLocation(schipp2.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
                } else {
                    enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
                }
            } else {
                schip.getSlowEnemies().doFunction(enemy);
                enemy.updateLocation(schip.getCurrentLocation(), enemy.getCurrentLocation(), enemy.getSpeed());
            }
        }
    }

    //endregion

    //updates the "updates" region
    void update() throws IOException, UnsupportedAudioFileException {
        checkDeadShip();
        if (schip != null || schipp2 != null) {
            updateAnimations();
            checkGameFinished();
            approachShip();
            if (schip != null) {
                schip.updateBuffs();
                if(schip.getDrone() != null && (Objects.equals(schip.getDrone().getNaam(), "Attack Drone") || Objects.equals(schip.getDrone().getNaam(), "Experience Drone"))){
                    updateKogels(schip.getDrone().getKogels(), schip);
                }
                if (schip.getDrone() != null && (Objects.equals(schip.getDrone().getNaam(), "Attack Drone") || Objects.equals(schip.getDrone().getNaam(), "Defense Drone"))){
                    currentDroneXpBar.setSize((int) updateDroneXpBar(schip.getDrone()), currentDroneXpBar.getHeight());
                }
                if (schip.getDrone() != null && Objects.equals(schip.getDrone().getNaam(), "Defense Drone")) {
                    collisionDrone(schip.getDrone());
                }
                schip.beweegSchip();
                updateKogels(schip.getKogels(), schip);
                if (schip.getInvulnerability().isActive()) {
                    currentHealthBar.setBackground(Color.cyan);
                    invulnerabilityTimer.start();
                }
                if (schip.getSlowEnemies().isActive()) {
                    slowerEnemiesTimer.start();
                }

                combo.setText("x " + schip.getCombo());
                score.setText("" + schip.getScore());
                currentHealthBar.setSize((int) updateHealthBar(schip), currentHealthBar.getHeight());
                currentSchipXpBar.setSize((int) updateSchipXpBar(schip), currentSchipXpBar.getHeight());
                schipbarp1.setText(" lvl: " + schip.getLevel());
            }

            if (coop && schipp2 != null) {
                schipp2.updateBuffs();
                if(schipp2.getDrone() != null && (Objects.equals(schipp2.getDrone().getNaam(), "Attack Drone") || Objects.equals(schipp2.getDrone().getNaam(), "Experience Drone"))){
                    updateKogels(schipp2.getDrone().getKogels(), schipp2);
                }
                if (schipp2.getDrone() != null && (Objects.equals(schipp2.getDrone().getNaam(), "Attack Drone") || Objects.equals(schipp2.getDrone().getNaam(), "Defense Drone"))){
                    currentDroneXpBarp2.setSize((int) updateDroneXpBar(schipp2.getDrone()), currentDroneXpBarp2.getHeight());
                }
                if (schipp2.getDrone() != null && Objects.equals(schipp2.getDrone().getNaam(), "Defense Drone")) {
                    collisionDrone(schipp2.getDrone());
                }
                schipp2.beweegSchip();
                updateKogels(schipp2.getKogels(), schipp2);
                if (schipp2.getInvulnerability().isActive()) {
                    currentHealthBarp2.setBackground(Color.cyan);
                    invulnerabilityTimer.start();
                }
                if (schipp2.getSlowEnemies().isActive()) {
                    slowerEnemiesTimer.start();
                }

                combop2.setText("x " + schipp2.getCombo());
                scorep2.setText("" + schipp2.getScore());
                currentHealthBarp2.setSize((int) updateHealthBar(schipp2), currentHealthBarp2.getHeight());
                currentSchipXpBarp2.setSize((int) updateSchipXpBar(schipp2), currentSchipXpBarp2.getHeight());
                schipbarp2.setText("lvl: " + schipp2.getLevel());
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
        double healthBarWidth = 0;

        if (schip.getHp() >= 0) {
            double ratioHP = 432 / schip.getMaxhp();
            healthBarWidth = ratioHP * schip.getHp();
        }
        return healthBarWidth;
    }

    private void checkGameFinished() throws IOException, UnsupportedAudioFileException {
        if (coop) {
            if ((schip == null || schipp2 == null) && !soundExecuted) {
                new Sound("playerdeath");
                soundExecuted = true;
            }

            if (schip == null && schipp2 == null) {
                new Sound("playerdeath");
                gameFinished = true;
            }
        } else {
            if (schip.getHp() <= 0) {
                new Sound("playerdeath");
                gameFinished = true;
            }
        }
    }

    private double updateSchipXpBar(Schip schip) {
        double xpBarWidthSchip;

        double ratioSchipXP = 100 / schip.getMaxXp();
        xpBarWidthSchip = ratioSchipXP * schip.getCurrentXp();


        return xpBarWidthSchip;
    }

    private double updateDroneXpBar(Drone drone) {
        double xpBarWidthDrone;

        double ratioDroneXp = 100 / drone.getMaxXp();
        xpBarWidthDrone = ratioDroneXp * drone.getCurrentXp();
        if(drone == schip.getDrone()){
            dronebarp1.setText(" lvl: " + drone.getLevel());
        } else if (drone == schipp2.getDrone()){
            dronebarp2.setText(" lvl: " + drone.getLevel());
        }
        return xpBarWidthDrone;
    }

    //region Timers

    private void setInvulnerabilityTimer(Schip schip) {
        invulnerabilityTimer = new Timer(5000, e -> {
            if (schip != null) {
                schip.getInvulnerability().setActive(false);
                new Sound("shieldinactive");
                if(coop && schip == schipp2){
                    currentHealthBarp2.setBackground(Color.green);
                } else {
                    currentHealthBar.setBackground(Color.green);
                }
                invulnerabilityTimer.stop();
            }
        });
    }

    private void setSlowerEnemiesTimer(Schip schip) {
        slowerEnemiesTimer = new Timer(5000, e -> {
            schip.getSlowEnemies().setActive(false);
            slowerEnemiesTimer.stop();

        });
    }

    private void setUpShootingDroneTimer(Drone drone) {
        shootingDroneTimer = new Timer(drone.getFireSpeed(), e -> {
            if (!enemyOnField.isEmpty()) {
                drone.getKogels().add(new Kogel(drone.getCurrentLocation().getX() + drone.getWidth() / 2, drone.getCurrentLocation().getY() + drone.getHeight() / 2, closestEnemy(drone, enemyOnField), "resources/Media/kogel2.png"));
            }
        });
    }

    private void setUpMousePressedTimer(int delay){
        if (schip != null){
            ActionListener taskPerformer = e1 -> schip.mousePressed(mouseLocation);
            if (mousePressedTimer == null) {
                mousePressedTimer = new Timer(delay, taskPerformer);
                mousePressedTimer.start();
            }
        }
    }

    private void makeEnemy(Enemy enemy) {
        enemyOnField.add(new Enemy(enemy.getNr(), enemy.getNaam(), enemy.getBeschrijving(), (int)(enemy.getHP() * (1 + Math.pow((enemyPower - 1), 2) * 0.0025)), (int)(enemy.getKracht() * (1 + Math.pow((enemyPower - 1), 1.65) * 0.015)), enemy.getImageString(), (int)(enemy.getExperience() * (1 + Math.pow((enemyPower - 1), 2) * 0.015)), enemy.getExperience() * 10, enemy.getSpeed()));
    }

    private void spawnEnemies() {
        spawnTimer = new Timer(5000, e -> {
            if (enemyOnField.isEmpty()) {
                Enemy enemy = null;

                enemy = enemies.get(0);
                for (int i = 0; i < enemyCounter; i++) {
                    makeEnemy(enemy);
                }
                if (enemyCounter % 3 == 0) {
                    enemy = enemies.get(1);
                    makeEnemy(enemy);
                }
                if (enemyCounter % 2 == 0) {
                    enemy = enemies.get(2);
                    makeEnemy(enemy);
                }
                if (enemyCounter % 4 == 0) {
                    enemy = enemies.get(3);
                    makeEnemy(enemy);
                }
                if (enemyCounter % 5 == 0) {
                    enemy = enemies.get(4);
                    makeEnemy(enemy);
                }
                if (enemyCounter % 6 == 0) {
                    enemy = enemies.get(5);
                    makeEnemy(enemy);
                }
                if (roundCounter % 10 == 0) {
                    enemy = enemies.get(6);
                    makeEnemy(enemy);
                    enemyCounter = 3;
                }
                enemyCounter ++;
                enemyPower ++;
                try {
                    effects.add(Effect.roundIndicator(roundCounter));
                } catch (IOException | FontFormatException e1) {
                    e1.printStackTrace();
                }
                roundCounter++;
            }

            if (schip != null && schip.getUpgrades().contains(2)) {
                healthRegen(schip);
            }
            if (coop && schipp2 != null && schipp2.getUpgrades().contains(2)) {
                healthRegen(schipp2);
            }
        });
    }

    private void healthRegen(Schip schip) {
        if (schip.getHp() < schip.getMaxhp()) {
            schip.addHp(2);
        }
    }

    //endregion

    //region Mouse and Key events

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
        public void mouseDragged(MouseEvent e) {
            mouseLocation = e.getPoint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseLocation = e.getPoint();
                mousePressedTimer.start();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (mousePressedTimer != null) {
                mousePressedTimer.stop();
            }
        }
    }

    //endregion

    //endregion
}