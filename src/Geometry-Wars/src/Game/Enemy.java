package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Enemy extends Sprite {
    //region Instance Variables

    private int nr;
    private String naam;
    private String beschrijving;
    private int hp = 100;
    private int kracht = 10;
    private double locationX;
    private double locationY;
    private int experience;
    private int score;
    private int speed;
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 768;
    private Rectangle2D hpBar;
    private Rectangle2D maxHpBar;
    private int maxHp = 100;
    private String imageString;

    //endregion

    //region Constructors


    public Enemy(int nr, String naam, String beschrijving, int hp, int kracht, String image, int experience, int score, int speed) {
        super(image);
        imageString = image;
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        this.speed = speed;

        locationX = randomX();
        locationY = randomY();

        while ((locationX > 100 && locationY >100) && (locationX < SCREEN_WIDTH -100 && locationY < SCREEN_HEIGHT -100)){
            locationX = randomX();
            locationY = randomY();
        }

        currentLocation = new Point();
        currentLocation.setLocation(locationX, locationY);
        isHit = false;
    }

    public void loseHP(int amount) {

        this.hp -= amount;
    }

    public void setHpBar(Rectangle2D hpBar) {
        this.hpBar = hpBar;
    }

    public void setMaxHpBar(Rectangle2D maxHpBar) {
        this.maxHpBar = maxHpBar;
    }

    public String getNaam() {
        return naam;
    }

    public int getNr() {
        return nr;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getKracht() {
        return kracht;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setKracht(int kracht) {
        this.kracht = kracht;
    }
    public double randomX() {
        Random randomGenerator = new Random();

        int randGetal = randomGenerator.nextInt(SCREEN_WIDTH);


       /* while (randGetal > 100 && randGetal < (SCREEN_WIDTH - 100)) {
            randGetal = randomGenerator.nextInt(SCREEN_WIDTH);
        }*/

        return randGetal;

    }

    public double randomY() {

        Random randomGenerator = new Random();

        int randGetal = randomGenerator.nextInt(SCREEN_HEIGHT);
        /*while (randGetal > 100 && randGetal < (SCREEN_HEIGHT - 100)) {
            randGetal = randomGenerator.nextInt(SCREEN_HEIGHT);
        }*/

        return randGetal;

    }

    /*public Enemy() {
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";

    }*/

    /*public void drawAndCheckCollisionWithBullet(Graphics2D g2d, Point targetPoint, Point currentLocation, ArrayList<Kogel> collidingSprites){
        draw(g2d, targetPoint, currentLocation);
        for (Kogel kogel : collidingSprites) {
            //wanneer isHit true is verdwijnt de collidingsprite
            if (kogel.collisionDetect(this.getHitBox())) {
                kogel.setHit(true);
                System.out.println("in true");
            }
        }
    }*/

    public Image getImage() {
        return image;
    }

    public int getExperience() {
        return experience;
    }

    public int getScore() {
        return score;
    }

    public void move(double velocityX, double velocityY) {
        locationX += velocityX;
        locationY += velocityY;

        currentLocation.setLocation(locationX, locationY);

    }

    public String getImageString() {
        return imageString;
    }

    public void drawHPBar(Graphics2D g2d) {

        if (this.hpBar == null || this.maxHpBar == null) {
            this.setHpBar(new Rectangle2D.Double(this.getCurrentLocation().getX(), this.getCurrentLocation().getY() - 50,  hpBarWidthRatio() * 0.75, 8));
            this.setMaxHpBar(new Rectangle2D.Double(getCurrentLocation().getX(), getCurrentLocation().getY() - 50, getWidth()* 0.75, 8));
        } else {
            if (hp != 0) {
                hpBar.setRect(getCurrentLocation().getX(), getCurrentLocation().getY() - 50,  hpBarWidthRatio()* 0.75, 8);
                maxHpBar.setRect(getCurrentLocation().getX(), getCurrentLocation().getY() - 50, getWidth()* 0.75, 8);
            }
        }

        g2d.setPaint(Color.RED);
        g2d.fill(maxHpBar);
        g2d.draw(maxHpBar);
        g2d.setPaint(Color.GREEN);
        g2d.fill(hpBar);
        g2d.draw(hpBar);
    }

    public double hpBarWidthRatio() {

        double ratio = width / maxHp;
        double healthBarWidth = ratio * hp;

        return healthBarWidth;
    }

    //endregion
}
