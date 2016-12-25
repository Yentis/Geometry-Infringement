package Game;

import Game.InGameUpgrade.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip extends Sprite {
    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int maxhp = 100;
    private int kracht = 25;
    private int score = 0;
    private int newscore = 0;
    private int combo = 0;
    private int level = 0;
    private int currentXp = 0;
    private int keyLeft;
    private int keyRight;
    private int keyUp;
    private int keyDown;
    private int upgradecombo = 0;
    private double speed;
    private double dx;
    private double dy;
    private double maxXp = 1000;
    private double locationX;
    private double locationY;
    private double currentAngle;
    private String imageString;
    private ArrayList<Kogel> kogels = new ArrayList<>();
    private List<Integer> menuUpgrades = new ArrayList<>();
    private Movement move;
    private Drone drone;

    //IngameUpgrades
    private LifeSteal lifesteal = new LifeSteal(1, "LifeSteal", "resources/Media/IngameUpgradeIcons/LifeSteal.png", this);
    private Invulnerability invulnerability = new Invulnerability(2, "Invulnerability", "resources/Media/IngameUpgradeIcons/Invulnerability.png", this);
    private RandomBullets randomBullets = new RandomBullets(3, "RandomBullets", "resources/Media/IngameUpgradeIcons/RandomBullets.png", this);
    private SlowEnemies slowEnemies = new SlowEnemies(4, "RandomBullets", "resources/Media/IngameUpgradeIcons/SlowEnemies.png", this);
    private ActiveDrone activeDrone = new ActiveDrone(5, "ActiveDrone", "resources/Media/IngameUpgradeIcons/ActiveDrone.png", this);
    private ArrayList<InGameUpgrade> buffs = new ArrayList<>();
    private ArrayList<InGameUpgrade> activeBuffs = new ArrayList<>();

    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image, int keyLeft, int keyRight, int keyUp, int keyDown, double speed, List<Integer> upgrades) {
        super(image);
        imageString = image;
        currentLocation = new Point();
        currentLocation.setLocation(700, 300);
        locationX = currentLocation.getX();
        locationY = currentLocation.getY();
        currentAngle = 0;
        menuUpgrades = upgrades;
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
    }

    //endregion

    //region Getters & Setters

    public List<Integer> getUpgrades() {
        return menuUpgrades;
    }

    public LifeSteal getLifesteal() {
        return lifesteal;
    }

    public double getMaxXp() {
        return maxXp;
    }

    public Invulnerability getInvulnerability() {
        return invulnerability;
    }

    public SlowEnemies getSlowEnemies() {
        return slowEnemies;
    }

    public ActiveDrone getActiveDrone() {
        return activeDrone;
    }

    public ArrayList<InGameUpgrade> getActiveBuffs() {
        return activeBuffs;
    }

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

    private void setMaxXp(int level) {
        this.maxXp = Math.pow(2, level) * 1000;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public ArrayList<Kogel> getKogels() {
        return kogels;
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }

    //endregion

    //region Behaviour

    private void addBuffs() {
        buffs.add(lifesteal);
        buffs.add(invulnerability);
        buffs.add(randomBullets);
        buffs.add(slowEnemies);
        buffs.add(activeDrone);
    }

    public void updateBuffs() {
        for (InGameUpgrade buff : buffs) {
            if (buff.isActive() && !activeBuffs.contains(buff)) {
                activeBuffs.add(buff); //indien de buff active is en nog niet in de ActiveBuffs zit, stop je hem erin
            } else if (!buff.isActive() && activeBuffs.contains(buff)) {
                activeBuffs.remove(buff); //indien de buff niet meer active is en in de ActiveBuffs zit, gooi je hem eruit
            }
        }
    }

    private void addLevel() {
        this.level += 1;
    }

    public void addCurrentXp(int xp) {
        this.currentXp += xp;
    }

    private void resetCurrentXp() {
        this.currentXp = 0;
    }

    public void checkLevel() {
        if (getCurrentXp() >= getMaxXp()) {
            addLevel();
            setMaxXp(getLevel());
            checkLevel();
            resetCurrentXp();
            addKracht(25);
        }
    }

    public void checkForUpgrade() {
        //TODO terugveranderen :p - Renzie dit is voor de upgrade arraylist check
        if (upgradecombo % 20 == 0) {
            //Every 20 combo
            if(!invulnerability.isActive()){
                try {
                    new Sound("shieldactive");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            invulnerability.setActive(true);
        } else if (upgradecombo % 50 == 0) {
            //Every 50 combo
            slowEnemies.setActive(true);
        }
        switch (upgradecombo) {
            case 1:
                //when combo resets
                for (InGameUpgrade buff : buffs){
                    buff.setActive(false);
                }
                break;
            case 20:
                lifesteal.setActive(true);
                break;
            case 30:
                activeDrone.setActive(true);
                break;
            case 50:
                randomBullets.setActive(true);
                break;
        }
    }

    public void resetCombo() {
        combo = 0;
        upgradecombo = 0;
    }

    public void addCombo() {
        if (combo < 999) {
            combo ++;
        }
        upgradecombo ++;
    }

    public void addScore(int enemyscore, int combo) {
        score = enemyscore * combo;
        score = adjustScore(score);
    }

    private int adjustScore(int score) {
        newscore += score;
        return newscore;
    }

    private void addKracht(int amount) {
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

    void controllerPressed(int key) {
        move.controllerPressed(key);
    }

    void controllerReleased(int key) {
        move.controllerReleased(key);
    }

    public void keyPressed(KeyEvent e) {
        move.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        move.keyReleased(e);
    }

    void moveUp(double speed) {
        dy = -speed;
    }

    void moveDown(double speed) {
        dy = speed;
    }

    void moveLeft(double speed) {
        dx = -speed;
    }

    void moveRight(double speed) {
        dx = speed;
    }

    void controllerAim(double x, double y) {
        Point point = new Point((int) x, (int) y);
        fire(point);
        if (randomBullets.isActive()) {
            randomFire();
        }
    }

    public void mousePressed(Point point) {
        fire(point);
        if (randomBullets.isActive()) {
            randomFire();
        }
    }

    private void addKogels(Kogel k) {
        kogels.add(k);
    }

    private void fire(Point point) {
        try {
            new Sound("shoot");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        double kogelX = locationX;
        double kogelY = locationY;

        addKogels(new Kogel(kogelX, kogelY, point, "resources/Media/kogel1.png"));

        if (menuUpgrades.contains(1)) {
            Point mousePointer2 = new Point((int)point.getX(), (int)point.getY() + 50);
            Point mousePointer3 = new Point((int)point.getX(), (int)point.getY() - 50);

            addKogels(new Kogel(kogelX, kogelY, mousePointer2, "resources/Media/kogel1.png"));
            addKogels(new Kogel(kogelX, kogelY, mousePointer3, "resources/Media/kogel1.png"));
        }
    }

    private int randomX() {
        Random randomGenerator = new Random();
        int SCREEN_WIDTH = 1024;

        return randomGenerator.nextInt(SCREEN_WIDTH);
    }

    private int randomY() {
        Random randomGenerator = new Random();
        int SCREEN_HEIGHT = 768;

        return randomGenerator.nextInt(SCREEN_HEIGHT);
    }

    private void randomFire() {
        double kogelX = locationX;
        double kogelY = locationY;
        int kogelX2 = randomX();
        int kogelY2 = randomY();
        Point mousePointer2 = new Point(kogelX2, kogelY2);

        addKogels(new Kogel(kogelX, kogelY, mousePointer2, "resources/Media/kogel1.png"));
    }

    // Dit zorgt ervoor dat de angle binnen 360 blijft.
    double normalizeAngle(double angle) {
        if (angle < 0 || 360 < angle) {
            angle = (angle + 360) % 360;
            return angle;
        } else {
            return angle;
        }
    }

    //endregion
}
