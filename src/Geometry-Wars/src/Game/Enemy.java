package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
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
    private double width = 100;
    private double height = 1000;
    private Point location = new Point();
    private double locationX;
    private double locationY;
    private int experience;
    private int score;

    private int SCREEN_WIDTH = 1024;
    private int SCREEN_HEIGHT = 768;
    private boolean visible;

    private Rectangle2D rectangle;

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
        locationX = randRange(0, SCREEN_WIDTH);
        locationY = randRange(0, SCREEN_HEIGHT);
        location.setLocation(locationX, locationY);




    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle2D rectangle) {
        this.rectangle = rectangle;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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

    public double getDirection(Point target, Point start) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - location.getY(), target.x - location.getX()));
        return angle;
    }


    public void move(double velocityX, double velocityY){
        locationX += velocityX;
        locationY += velocityY;

        location.setLocation(locationX, locationY);

    }



    // moet spawnen


    // moet van spawnpunt naar schip vliegen op een bepaalde manier

    //endregion
}
