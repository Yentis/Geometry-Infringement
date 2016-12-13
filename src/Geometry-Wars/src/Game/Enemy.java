package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Enemy extends Sprite{
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
    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 768;

    //endregion

    //region Constructors

    public Enemy(int nr, String naam, String beschrijving, int hp, int kracht, String image, int experience, int score){
        super(image);
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        locationX = randRange(0, SCREEN_WIDTH);
        locationY = randRange(0, SCREEN_HEIGHT);
        currentLocation = new Point();
        currentLocation.setLocation(locationX, locationY);
        isHit = false;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double randRange(int minRange, int maxRange){
        Random generator = new Random();
        double randgetal = minRange + (maxRange - minRange) * generator.nextDouble();

        return randgetal;
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

    public Image getImage(){
        return image;
    }


    public void move(double velocityX, double velocityY){
        locationX += velocityX;
        locationY += velocityY;

        currentLocation.setLocation(locationX, locationY);

    }

    //endregion
}
