package Game;

import java.awt.*;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Kogel extends Sprite {
    //region Instance Variables

    private double kogelSnelheid = 10;
    private Point startingPoint;

    //endregion

    //region Constructors

    public Kogel(Spel spel, double x, double y, Point target, String image) {
        super(spel, x, y, target, image);
        startingPoint = new Point();
        startingPoint.setLocation(x, y);
        isHit = false;
    }

    //endregion

    //region Getters & Setters

    public Point getStartingPoint() {
        return startingPoint;
    }

    public double getKogelSnelheid() {
        return kogelSnelheid;
    }

    //endregion

    //region Behaviour

    public boolean isOutOfBorder(double currlocation, double minBorder, double maxborder ){
        if (currlocation < minBorder ){
            return true;
        } else if (currlocation > maxborder){
            return true;
        } return false;
    }

    //endregion
}
