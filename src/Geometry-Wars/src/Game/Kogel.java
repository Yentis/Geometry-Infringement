package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Kogel extends Sprite {
    private final int SCREEN_WIDTH = 1024;
    private double kogelSnelheid = 10;
    private Point startingPoint;


    public Kogel(double x, double y, Point target, String image) {
        super(x, y, target, image);
        startingPoint = new Point();
        startingPoint.setLocation(x, y);
        isHit = false;
    }

    public boolean isOutOfBorder(double currlocation, double minBorder, double maxborder ){
        if (currlocation < minBorder ){
            return true;
        } else if (currlocation > maxborder){
            return true;
        } return false;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public double getKogelSnelheid() {
        return kogelSnelheid;
    }

}
