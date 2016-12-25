package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Sprite {
    //region Instance Variables

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;
    private Point target;
    Point currentLocation;
    private Rectangle2D hitBox;
    boolean isHit;
    private Schip hitter;

    //endregion

    //region Constructors

    Sprite(double x, double y, Point target, String image) {
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

    Sprite(String image) {
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        isHit = false;
    }

    //endregion

    //region Getters & Setters

    public Point getCurrentLocation() {
        return currentLocation;
    }

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

    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public double getDirection(Point target, Point start) {
        return Math.toDegrees(Math.atan2(target.getY() - start.getY(), target.x - start.getX()));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setCurrentLocation(double x, double y){ this.currentLocation.setLocation(x,y); }

    public void setImage(Image image) {
        ImageIcon ii = new ImageIcon(image);
        this.image = ii.getImage();
    }

    //endregion

    //region Behaviour

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

        if(length == 0) {
            length = 1;
        }

        velocityX = ((verschilX) / length) * speed;
        velocityY = ((verschilY) / length) * speed;

        //verplaats met de velocities
        move(velocityX, velocityY);
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
        drawHitBox();

        //return to old Transform
        g2d.setTransform(save);
    }

    private void updateNewTransform(AffineTransform newTransform, Double angle) {
        newTransform.translate(currentLocation.getX(), currentLocation.getY());
        newTransform.rotate(Math.toRadians(angle), width / 2, height / 2);
        newTransform.translate(-currentLocation.getX(), -currentLocation.getY());
    }

    public void drawHitBox() {
        if (hitBox == null) {
            hitBox = new Rectangle2D.Double(this.getCurrentLocation().getX(), this.getCurrentLocation().getY(), this.getWidth(), this.getHeight());
        } else {
            hitBox.setRect(this.getCurrentLocation().getX(), this.getCurrentLocation().getY(), this.getWidth(), this.getHeight());
        }
    }

    //endregion
}
