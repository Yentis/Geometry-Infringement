package Game;


import GUI.InGame;
import Game.InGameUpgrade.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import static java.lang.Math.abs;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip extends Sprite {

    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int maxhp = 100;
    private int kracht = 25;
    private double speed;
    private double dx;
    private double dy;
    private int score = 0;
    private int newscore = 0;
    private int combo = 0;
    private int level = 0;
    private int currentXp = 0;
    private double maxXp = 1000;

    private int keyLeft;
    private int keyRight;
    private int keyUp;
    private int keyDown;
    private ArrayList<Kogel> kogels = new ArrayList<Kogel>();
    private double locationX;
    private double locationY;
    private double currentAngle;
    private Movement move;
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 768;
    private Drone drone;

    //IngameUpgrades
    private LifeSteal lifesteal = new LifeSteal(1, "LifeSteal", "resources/Media/IngameUpgradeIcons/LifeSteal.png", this);
    private Invulnerability invulnerability = new Invulnerability(2, "Invulnerability", "resources/Media/IngameUpgradeIcons/Invulnerability.png", this);
    private RandomBullets randomBullets = new RandomBullets(3, "RandomBullets", "resources/Media/IngameUpgradeIcons/RandomBullets.png", this);
    private SlowEnemies slowEnemies = new SlowEnemies(4, "RandomBullets", "resources/Media/IngameUpgradeIcons/SlowEnemies.png", this);
    private ActiveDrone activeDrone = new ActiveDrone(5, "ActiveDrone", "resources/Media/IngameUpgradeIcons/ActiveDrone.png", this);



    //check voor activebuffs, en total aantal buffs in de game
    private ArrayList<InGameUpgrade> buffs = new ArrayList<InGameUpgrade>();
    private ArrayList<InGameUpgrade> activeBuffs = new ArrayList<InGameUpgrade>();


    //private HashMap<String, Boolean> buffs = new HashMap<String, Boolean>();

    private String imageString;
    private Timer mousePressedTimer;


    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image, int keyLeft, int keyRight, int keyUp, int keyDown, double speed) {
        super(image);
        imageString = image;
        currentLocation = new Point();
        currentLocation.setLocation(700, 300);
        locationX = currentLocation.getX();
        locationY = currentLocation.getY();
        currentAngle = 0;
        this.nr = nr;
        this.hp = hp;
        this.kracht = kracht;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.speed = speed;
        move = new Movement(this, keyLeft, keyRight, keyUp, keyDown);
        addBuffs();
        //updateBuffs();
    }
    //endregion

    private void addBuffs() {
        buffs.add(lifesteal);
        buffs.add(invulnerability);
        buffs.add(randomBullets);
        buffs.add(slowEnemies);
        buffs.add(activeDrone);
    }

    public ArrayList<InGameUpgrade> getActiveBuffs() {
        return activeBuffs;
    }

    public void updateBuffs() {
        for (InGameUpgrade buff : buffs) {
            if (buff.isActive() && !activeBuffs.contains(buff)) {
                activeBuffs.add(buff); //indien de buff active is en nog niet in de ActiveBuffs zit, stop je hem erin
                System.out.println("amount of active buffs: " + activeBuffs.size());
            } else if (!buff.isActive() && activeBuffs.contains(buff)) {
                activeBuffs.remove(buff); //indien de buff niet meer active is en in de ActiveBuffs zit, gooi je hem eruit
            }
        }
    }

    //region Getters


    public Drone getDrone() {
        return drone;
    }

    public String getImageString() {
        return imageString;
    }

    public int getKeyUp() {
        return keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public int getKeyRight() {
        return keyRight;
    }

    public int getKeyLeft() {
        return keyLeft;
    }

    public int getKracht() {
        return kracht;
    }

    public int getNr() {
        return nr;
    }

    public Movement getMove() {
        return move;
    }

    public int getCombo() {
        return combo;
    }

    public int getScore() {
        return score;
    }

    public int getHp() {
        return hp;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentXp() {
        return currentXp;
    }


    //endregion

    //region Setters


    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setControls(int keyLeft, int keyRight, int keyUp, int keyDown) {
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
    }

    public ArrayList<Kogel> getKogels() {
        return kogels;
    }


    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }
    //endregion

    //region ComboProperties
    public Invulnerability getInvulnerability() {
        return invulnerability;
    }

    public SlowEnemies getSlowEnemies() {
        return slowEnemies;
    }

    public ActiveDrone getActiveDrone() {
        return activeDrone;
    }

    //endregion

    //region levelProperties


    public void addLevel() {
        this.level += 1;
    }

    public void addCurrentXp(int xp) {
        this.currentXp += xp;
    }

    public void resetCurrentXp() {
        this.currentXp = 0;
    }

    public double getMaxXp() {
        return maxXp;
    }

    public void setMaxXp(int level) {
        this.maxXp = Math.pow(2, level) * 1000;
    }

    //endregion


    public void checkLevel() {
        if (getCurrentXp() >= getMaxXp()) {
            addLevel();
            setMaxXp(getLevel());
            checkLevel();
            resetCurrentXp();
        }
        setKracht(kracht + (25 * getLevel()));
    }
    //lifesteal 100, invul 50, slow 75, drone 150, randombullets 250
    //wanneer je combo verliest, zet alle upgrades af
    //debuff : als je < 50% hp hebt, vertraag je

    public void checkForUpgrade(int combo) {
        //TODO terugveranderen :p - Renzie dit is voor de upgrade arraylist check
        if (combo % 20 == 0) {
            //Every 50 combo
            invulnerability.setActive(true);
        } else if (combo % 50 == 0) {
            //Every 75 combo
            slowEnemies.setActive(true);
        }
        switch (combo) {
            case 1:
                //when combo resets
               for (InGameUpgrade buff : buffs){
                   buff.setActive(false);
               }
                break;
            case 20:
                 lifesteal.setActive(true);
                break;
            case 75:
                //stays active when reached
                activeDrone.setActive(true);
                break;
            case 100:
             randomBullets.setActive(true);
                break;


        }
        //updateBuffs();
    }

    public LifeSteal getLifesteal() {
        return lifesteal;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    //region Behaviour
    public void resetCombo() {
        setCombo(0);
    }

    public void addCombo() {
        combo += 1;
        addScore(100, combo);
    }


    public void addScore(int enemyscore, int combo) {
        score = enemyscore * combo;
        score = adjustScore(score);
    }

    public int adjustScore(int score) {

        newscore += score;
        System.out.println("score: " + newscore);
        System.out.println("combo: " + combo);
        return newscore;
    }

    public void setKracht(int kracht) {
        this.kracht = kracht;
    }

    public void addKracht(int amount) {
        this.kracht += amount;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void loseHP(int amount) {
        this.hp -= amount;
    }

    public void beweegSchip() {


        locationX = limitToBorders(locationX, 75, 875);
        locationY = limitToBorders(locationY, 125, 525);

        currentLocation.setLocation(locationX += dx, locationY += dy);
    }

    private double limitToBorders(double currLocation, double minBorder, double maxBorder) {
        if (currLocation > maxBorder) {
            return maxBorder;
        } else if (currLocation < minBorder) {
            return minBorder;
        }
        return currLocation;
    }

    public void controllerPressed(int key) {
        move.controllerPressed(key);
    }

    public void controllerReleased(int key) {
        move.controllerReleased(key);
    }

    public void keyPressed(KeyEvent e) {
        move.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        move.keyReleased(e);
    }

    public void moveUp(double speed) {
        dy = -speed;
    }

    public void moveDown(double speed) {
        dy = speed;
    }

    public void moveLeft(double speed) {
        dx = -speed;
    }

    public void moveRight(double speed) {
        dx = speed;
    }


    public void controllerAim(double x, double y) {
        Point point = new Point((int) x, (int) y);
        fire(point);
        if (randomBullets.isActive()) {
            randomFire();
        }
    }

    public void mousePressed(MouseEvent e) {
        fire(e.getPoint());
        if (randomBullets.isActive()) {
            randomFire();
        }
    }

    public void mouseReleased(MouseEvent e) {
        /*if (mousePressedTimer != null) {
            mousePressedTimer.stop();
        }*/
    }

    private void addKogels(Kogel k) {
        kogels.add(k);
    }

    public void fire(Point mousePointer) {
        /*System.out.println(mousePointer);
        int delay = 50;
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {*/
        double kogelX = locationX;
        double kogelY = locationY;

        addKogels(new Kogel(kogelX, kogelY, mousePointer, "resources/Media/kogel1.png"));
           /* }
        };

        if (mousePressedTimer == null) { //PROBLEEM: hij stelt de eerst geschoten angle in en savet het
            mousePressedTimer = new Timer(delay, taskPerformer);
            mousePressedTimer.start();
        }else {
            mousePressedTimer.start();
        }*/
    }


    public int randomX() {
        Random randomGenerator = new Random();

        int randGetal = randomGenerator.nextInt(SCREEN_WIDTH);


        return randGetal;

    }

    public int randomY() {

        Random randomGenerator = new Random();

        int randGetal = randomGenerator.nextInt(SCREEN_HEIGHT);

        return randGetal;

    }

    public void randomFire() {

        double kogelX = locationX;
        double kogelY = locationY;
        int kogelX2 = randomX();
        int kogelY2 = randomY();
        Point mousePointer2 = new Point(kogelX2, kogelY2);

        addKogels(new Kogel(kogelX, kogelY, mousePointer2, "resources/Media/kogel1.png"));
        System.out.println(kogelX2 + " " + kogelY2);
    }


    public void setImage(String image) {

    }


    // Dit zorgt ervoor dat de angle binnen 360 blijft.
    public double normalizeAngle(double angle) {
        if (angle < 0 || 360 < angle) {
            angle = (angle + 360) % 360;
            return angle;
        } else {
            return angle;
        }
    }
    //endregion
}
