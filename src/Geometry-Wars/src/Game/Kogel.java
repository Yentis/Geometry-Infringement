package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Kogel extends Sprite {
    private final int SCREEN_WIDTH = 1024;
    private double kogelSnelheid = 5;
    private Point startingPoint;
    private Point location;
    private boolean isHit;


    public Kogel(double x, double y, Point direction, String image) {

        super(x, y, direction, image);


        startingPoint = new Point();
        startingPoint.setLocation(x, y);
        location = new Point();
        location.setLocation(x,y);

        isHit = false;
        //location.setLocation(x,y);
    }

    public Point getLocation() {
        return location;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public double getKogelSnelheid() {
        return kogelSnelheid;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public void move(double velocityX, double velocityY) {

        x += velocityX;
        y += velocityY;

        location.setLocation(x,y);

        if (x > SCREEN_WIDTH) {
            visible = false;
        }
    }
}
