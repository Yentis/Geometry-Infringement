package Game;

import java.awt.*;
import java.awt.geom.Area;

/**
 * Created by Renzie on 1/12/2016.
 */
public class HitBox{
    private Point center;
    private double width;
    private double height;

    public HitBox( Point center, double width, double height) {
        this.width = width;
        this.center = center;
        this.height = height;
    }

    //TODO hitboxposition definen
    /* center - width / 2
     * center + width / 2
     * center + height / 2
     * center - height / 2
     */

    public boolean testIntersection(Shape shapeA, Shape shapeB){
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }

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

    }
}
