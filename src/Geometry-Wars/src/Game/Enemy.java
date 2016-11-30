package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Enemy {
    //region Instance Variables

    private int nr;
    private String naam;
    private String beschrijving;
    private int hp = 100;
    private int kracht = 10;
    private Image image;
    private int width = 100;
    private int height = 1000;
    private Point location = new Point();
    private double locationX;
    private double locationY;
    private int experience;
    private int score;

    private int SCREEN_WIDTH = 1024;
    private boolean visible;

    //endregion

    //region Constructors

    public Enemy(int nr, String naam, String beschrijving, int hp, int kracht, String image, int experience, int score){
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        locationX = randRange(0, 1024);
        locationY = randRange(0, 768);
        location.setLocation(locationX, locationY);


    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double randRange(int minRange, int maxRange){
        Random generator = new Random();
        double randgetal = minRange + (maxRange - minRange) * generator.nextDouble();

        return randgetal;
    }

    public Point getLocation() {
        return location;
    }

    public Enemy() {
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";

    }

    public Image getImage(){
        return image;
    }

    public double getDirection(Point target) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - location.getY(), target.x - location.getX()));
        return angle;
    }


    public void move(double velocityX, double velocityY){
        locationX += velocityX;
        locationY += velocityY;
        System.out.println("in move, locationX is: " + locationX);

        location.setLocation(locationX, locationY);

        if(locationX > SCREEN_WIDTH){
            visible = false;
        }
    }



    // moet spawnen


    // moet van spawnpunt naar schip vliegen op een bepaalde manier

    //endregion
}
