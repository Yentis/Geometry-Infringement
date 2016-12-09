package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Sprite {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;
    protected boolean visible;
    protected Point target;
    protected Point currentLocation;
    protected Rectangle2D rectangle;



    public Sprite(double x, double y, Point target, String image){
        this.x = x;
        this.y = y;
        this.target = target;
        visible = true;
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
    }

    public Sprite(String image){
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();

    }

    public void updateLocation(Point targetLocation, Point currentLocation, int speed){
        double length;
        double velocityX;
        double velocityY;
        double verschilX;
        double verschilY;

        verschilX = targetLocation.getX() - currentLocation.getX();
        verschilY = targetLocation.getY() - currentLocation.getY();

        /* verschil x / vierkantswortel van ( verschilx^2 + verschilY^2) om de lengte naar 1 stuk te brengen
        *  dit bepaalt de snelheid van de bullet en kan versneld worden door gewoon de kogelsnelheid te veranderen.*/
        length = Math.sqrt(Math.pow(verschilX, 2) + Math.pow(verschilY, 2));
        velocityX = ((verschilX) / length) * speed;
        velocityY = ((verschilY) / length) * speed;

        //verplaats met de velocities
        move(velocityX, velocityY);

    }


    public Image getImage() {
        return image;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point gettarget() { return target; }

    public void setRectangle(Rectangle2D rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getDirection(Point target , Point start) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - start.getY(), target.x - start.getX()));
        return angle;
    }

    public boolean collisionDetect(Rectangle2D approachingTarget) {
        return approachingTarget != null && rectangle.getBounds2D().intersects(approachingTarget.getBounds2D());
    }

    public void move(double velocityX, double velocityY){
        x += velocityX;
        y += velocityY;

        currentLocation.setLocation(x, y);

    }
}
