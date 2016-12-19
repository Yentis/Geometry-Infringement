package Game;


import GUI.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip extends Sprite{

    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int maxhp = 100;
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
    private boolean invulnerability;
    private boolean randomBullets;
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 768;



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
    public int getMaxhp(){
        return maxhp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Kogel> getKogels() {
        return kogels;
    }

    //endregion

    public boolean isInvulnerability() {
        return invulnerability;
    }

    public void setInvulnerability(boolean invulnerability) {
        this.invulnerability = invulnerability;
    }

    public boolean isLifesteal() {
        return lifesteal;
    }

    public void setLifesteal(boolean lifesteal) {
        this.lifesteal = lifesteal;
    }

    public boolean isRandomBullets() {
        return randomBullets;
    }

    public void setRandomBullets(boolean randomBullets) {
        this.randomBullets = randomBullets;
    }

    public void checkForUpgrade(int combo){

        if (combo % 100 == 0){
            //System.out.println("setHp(100);");
            setHp(100);
            setInvulnerability(true);
            //System.out.println("setInvulnerability");
        }
        switch (combo){
            case 1:
                setLifesteal(false);
                setInvulnerability(false);
                setRandomBullets(false);
                break;
            case 20 :
                //System.out.println("setExtraPowerActive");
                //setExtraPowerActive()

                break;
            case 50 :
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
                setRandomBullets(true);
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
        if (isRandomBullets()){
            randomFire();
        }

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
        //System.out.println("shit fired");
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

    public int randomX(){
        Random randomGenerator = new Random();

        int randGetal = randomGenerator.nextInt(SCREEN_WIDTH);


        return  randGetal;

    }

    public int randomY(){

        Random randomGenerator = new Random();

        int randGetal = randomGenerator.nextInt(SCREEN_HEIGHT);

        return  randGetal;

    }

    public void randomFire(){

        double kogelX = locationX;
        double kogelY = locationY;
        int kogelX2 = randomX();
        int kogelY2 = randomY();
        Point mousePointer2 = new Point(kogelX2,kogelY2);

        addKogels(new Kogel(kogelX,kogelY, mousePointer2,"src/Media/kogel1.png"));
        System.out.println(kogelX2 + " " + kogelY2);
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
