package Game;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip extends Sprite{

    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int kracht = 10;
    private double dx;
    private double dy;
    private int score;
    private int newscore = 0;
    private int combo;
    private ArrayList<Kogel> kogels = new ArrayList<Kogel>();
    private double locationX;
    private double locationY;
    private double currentAngle;
    private Movement move;
    private boolean lifesteal;


    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image, int score, int combo) {
        super(image);
        currentLocation = new Point();
        currentLocation.setLocation(700, 300);
        locationX = currentLocation.getX();
        locationY = currentLocation.getY();
        currentAngle = 0;
        this.score = 0;
        this.combo = 0;
        this.nr = nr;
        this.hp = hp;
        this.kracht = kracht;
        move = new Movement(this);
    }
    //endregion

    public int getCombo() {
        return combo;
    }

    public int getScore() {
        return score;
    }

    public int getHp() {
        return hp;
    }

    //endregion

    //region Properties

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Kogel> getKogels() {
        return kogels;
    }

    //endregion

    public boolean isLifesteal() {
        return lifesteal;
    }

    public void setLifesteal(boolean lifesteal) {
        this.lifesteal = lifesteal;
    }

    public void checkForUpgrade(int combo){

        if (combo % 100 == 0){
            System.out.println("setHp(100);");
            setHp(100);
            //setInvulnerability()
            System.out.println("setInvulnerability");
        }
        switch (combo){
            case 0:
                setLifesteal(false);
                break;
            case 20 :
                System.out.println("setExtraPowerActive");
                //setExtraPowerActive()
                break;
            case 50 :
                System.out.println("setLifeStealActive");
                setLifesteal(true);
                break;
            case 125 :
                //setDroneActive()
                break;
            case 150:
                //setDroneAuto()
                break;
            case 250:
                //setDroneMorePower()
                break;
            case 350:
                //setRandomBullets()
                break;
        }
    }
    public void setCombo(int combo) {
        this.combo = combo;
    }

    //region Behaviour
    public void resetCombo(){
        setCombo(0);
    }
    public void addCombo(){
        combo += 1;
        addScore(100, combo);
    }

    public void addScore(int enemyscore, int combo){
        score = enemyscore * combo;
        score = adjustScore(score);
    }

    public int adjustScore(int score){

        newscore += score;
        System.out.println("score: " + newscore);
        System.out.println("combo: " + combo);
        return newscore;
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


        locationX = limitToBorders(locationX , 0 , 1024);
        locationY = limitToBorders(locationY , 0 , 768);

        currentLocation.setLocation(locationX += dx, locationY += dy);
    }

    private double limitToBorders(double currLocation, double minBorder, double maxBorder){
        if (currLocation > maxBorder) {
            return maxBorder;
        } else if (currLocation < minBorder) {
           return minBorder;
        }
        return currLocation;
    }

    public void keyPressed(KeyEvent e) {
        move.keyPressed(e);
        }

    public void keyReleased(KeyEvent e) {
        move.keyReleased(e);
    }

    public void moveUp(int speed){
        dy = -speed;
    }

    public void moveDown(int speed){
        dy = speed;
    }

    public void moveLeft(int speed){
        dx = -speed;
    }

    public void moveRight(int speed){
        dx = speed;
    }

    public void mousePressed(MouseEvent e) {
        fire(e.getPoint());
    }

    public void mouseReleased(MouseEvent e) {
        /*if (mousePressedTimer != null){
            mousePressedTimer.stop();
            System.out.println("mouse released");
        }*/
    }

    private void addKogels(Kogel k){
        if (kogels.size() < 30){
            kogels.add(k);
        } else {
            kogels.clear();
            System.out.println("kogels cleared");
        }
    }

    public void fire(Point mousePointer) {
        /*if (mousePressedTimer == null){
            mousePressedTimer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {*/
        System.out.println("shit fired");
                    double kogelX = locationX;
                    double kogelY = locationY;
                    addKogels(new Kogel(kogelX, kogelY, mousePointer,"src/Media/kogel1.png"));
              /*  }
            });
       }
        else{
            mousePressedTimer.start();
        }
            */
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
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
