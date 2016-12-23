package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Sprite {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;
    protected Point target;
    protected Point currentLocation;
    protected Rectangle2D hitBox;
    protected boolean isHit;
    private Schip hitter;
    private String imageString;


    public Sprite(double x, double y, Point target, String image) {
        imageString = image;
        this.x = x;
        this.y = y;
        this.target = target;
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        currentLocation = new Point();
        currentLocation.setLocation(x, y);
        isHit = false;
    }

    public Sprite(String image) {
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        isHit = false;
    }

    public boolean isNull(){
        return this == null;
    }

    public void updateLocation(Point targetLocation, Point currentLocation, double speed) {
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setCurrentLocation(double x, double y){ this.currentLocation.setLocation(x,y); }

    public Point getTarget() {
        return target;
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

    public void setHitBox(Rectangle2D hitBox) {
        this.hitBox = hitBox;
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public void setHitter(Schip hitter) {
        this.hitter = hitter;
    }

    public Schip getHitter() {
        return hitter;
    }

    public double getDirection(Point target, Point start) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - start.getY(), target.x - start.getX()));
        return angle;
    }

    public boolean collisionDetect(Rectangle2D approachingTarget) {
        return approachingTarget != null && hitBox != null && hitBox.getBounds2D().intersects(approachingTarget.getBounds2D());
    }



    public void move(double velocityX, double velocityY) {
        x += velocityX;
        y += velocityY;

        currentLocation.setLocation(x, y);

    }

    public void draw(Graphics2D g2d,Double angle) {
        //uh.. dit wordt gebruikt om de vorige transform te restoren ofzoiets idk - Renzie
        AffineTransform save = g2d.getTransform();

        //Hier wordt alles veranderd op enkel de newTransform - Renzie
        AffineTransform newTransform = new AffineTransform();
        updateNewTransform(newTransform, angle);
        g2d.transform(newTransform);

        //Teken alles op t - Renzie
        g2d.drawImage(this.getImage(), this.getCurrentLocation().x, this.getCurrentLocation().y, null);

        //Dit is een rectangle die voor collision zorgt
        drawHitBox(g2d);

        //return to old Transform
        g2d.setTransform(save);
    }


    public void updateNewTransform(AffineTransform newTransform, Double angle) {
        newTransform.translate(this.getCurrentLocation().getX(), this.getCurrentLocation().getY());
        newTransform.rotate(Math.toRadians(angle), this.getWidth() / 2, this.getHeight() / 2);
        newTransform.translate(-this.getCurrentLocation().getX(), -this.getCurrentLocation().getY());
    }


    public void drawHitBox(Graphics2D g2d) {
        if (this.getHitBox() == null) {
            this.setHitBox(new Rectangle2D.Double(this.getCurrentLocation().getX(), this.getCurrentLocation().getY(), this.getWidth(), this.getHeight()));
        } else {
            this.getHitBox().setRect(this.getCurrentLocation().getX(), this.getCurrentLocation().getY(), this.getWidth(), this.getHeight());
        }
       g2d.draw(this.getHitBox());
    }

    public void setImage(Image image) {
        ImageIcon ii = new ImageIcon(image);
        this.image = ii.getImage();
    }
}
