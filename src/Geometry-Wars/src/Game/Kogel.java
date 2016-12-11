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
    private boolean isHit;


    public Kogel(double x, double y, Point target, String image) {

        super(x, y, target, image);


        startingPoint = new Point();
        startingPoint.setLocation(x, y);
        isHit = false;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public double getKogelSnelheid() {
        return kogelSnelheid;
    }

}
