package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

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
    protected Rectangle2D rectangle;

    public Sprite(double x, double y, Point target){
        this.x = x;
        this.y = y;
        this.target = target;
        visible = true;
    }

    public Sprite(String image){
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();

    }

    protected void loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        width = ii.getIconWidth();
        height = ii.getIconHeight();
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

}
