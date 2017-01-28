package Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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
    private double speed;
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 768;
    private Rectangle2D hpBar;
    private Rectangle2D maxHpBar;
    private int maxHp;
    private String imageString;
    private double[] hard;
    private double[] normal;
    private double[] easy;

    //endregion

    //region Constructors

    public Enemy(int nr, String naam, String beschrijving, int hp, int kracht, String image, int experience, int score, double speed) {
        super(image);
        imageString = image;
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        this.speed = speed;
        this.experience = experience;
        this.score = score;
        maxHp = hp;
        easy = new double[]{this.hp / 2, this.kracht / 2, this.speed / 2, this.score / 2};
        normal = new double[]{this.hp, this.kracht, this.speed, this.score};
        hard = new double[]{this.hp * 1.5, this.kracht * 1.5, this.speed * 1.25, this.score * 2};

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

    //endregion

    //region Getters & Setters

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

    public double getSpeed() {
        return speed;
    }

    public Image getImage() {
        return image;
    }

    public int getExperience() {
        return experience;
    }

    public int getScore() {
        return score;
    }

    public String getImageString() {
        return imageString;
    }

    public void setDifficulty(String difficulty){
        double[] selecteddiff = new double[3];

        switch (difficulty){
            case "Easy":
                selecteddiff = easy;
                break;
            case "Normal":
                selecteddiff = normal;
                break;
            case "Hard":
                selecteddiff = hard;
                break;
        }

        this.hp = (int)(selecteddiff[0]);
        this.kracht = (int)(selecteddiff[1]);
        this.speed = selecteddiff[2];
        this.score = (int)(selecteddiff[3]);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setKracht(int kracht) {
        this.kracht = kracht;
    }

    //endregion

    //region Behaviour

    public void loseHP(int amount) {
        this.hp -= amount;
    }

    private double randomX() {
        Random randomGenerator = new Random();

        return randomGenerator.nextInt(SCREEN_WIDTH);
    }

    private double randomY() {
        Random randomGenerator = new Random();

        return randomGenerator.nextInt(SCREEN_HEIGHT);
    }

    public void move(double velocityX, double velocityY) {
        locationX += velocityX;
        locationY += velocityY;

        currentLocation.setLocation(locationX, locationY);
    }

    public void drawHPBar(Graphics2D g2d) {
        if (hpBar == null || maxHpBar == null) {
            hpBar = (new Rectangle2D.Double(this.getCurrentLocation().getX(), this.getCurrentLocation().getY() - 50,  hpBarWidthRatio() * 0.75, 8));
            maxHpBar = (new Rectangle2D.Double(getCurrentLocation().getX(), getCurrentLocation().getY() - 50, getWidth()* 0.75, 8));
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

    private double hpBarWidthRatio() {
        double ratio = width / maxHp;

        return ratio * hp;
    }

    //endregion
}
