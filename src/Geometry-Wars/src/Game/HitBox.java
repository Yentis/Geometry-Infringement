package Game;

import java.awt.*;

/**
 * Created by Renzie on 1/12/2016.
 */
public class HitBox {
    private Point center;
    private double width;
    private double height;

    public HitBox( Point center, double width, double height) {
        this.width = width;
        this.center = center;
        this.height = height;
    }

    //TODO hitboxposition definen
    //public void

    public Point getCenter() {
        return center;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void checkIfHit(HitBox approachingHitBox){
        //if (approachingHitBox.getCenter().)
    }
}
