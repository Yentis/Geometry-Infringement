package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip {

    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int kracht = 10;
    private int r;
    private double dx;
    private double dy;
    private Image image;
    private int width;
    private int height;
    private ArrayList kogels = new ArrayList();
    private Point location = new Point();
    private double locationX = location.getX();
    private double locationY = location.getY();
    private int currentAngle;
    private Movement move;
    private HitBox hitBox;
    private Timer mousePressedTimer;


    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image) {
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        locationX = 700;
        locationY = 300;
        location.setLocation(locationX, locationY);
        currentAngle = 0;
        this.nr = nr;
        this.hp = hp;
        this.kracht = kracht;
        move = new Movement(this);
        hitBox = new HitBox(location, width, height);
    }


//endregion

    //region Properties
    public HitBox getHitBox() {
        return hitBox;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList getKogels() {
        return kogels;
    }

    public Point getLocation() {
        return location;
    }

    public double getDy() {
        return dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }
    //endregion


    //region Behaviour

    private void initTimer(){

    }

    public void addKracht(int amount) {
        this.kracht += amount;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void beweegSchip() {

        //dr = 15;
        if (r > 360) {
            r -= 360;
        } else if (r < -360) {
            r += 360;
        }

        if (locationX > 1024) {
            locationX = 1024;
        } else if (locationX < 0) {
            locationX = 0;
        } else if (locationY > 768) {
            locationY = 768;
        } else if (location.getY() < 0) {
            locationY = 0;
        }

        location.setLocation(locationX += dx, locationY += dy);


        //r += dr;
    }

    public void keyPressed(KeyEvent e) {
        move.keyPressed(e);

        }




    public void keyReleased(KeyEvent e) {
        move.keyReleased(e);
    }

    public void moveUp(int speed){
        dy = -speed;
    }

    public void moveDown(int speed){
        dy = speed;
    }

    public void moveLeft(int speed){
        dx = -speed;
    }

    public void moveRight(int speed){
        dx = speed;
    }



    public void mousePressed(MouseEvent e) {
        fire(e.getPoint());
    }

    public void mouseReleased(MouseEvent e) {
        if (mousePressedTimer != null){
            mousePressedTimer.stop();
        }
    }

    private void addKogels(Kogel k){
        if (kogels.size() < 10){
            kogels.add(k);
        } else {
            kogels.clear();
        }
    }

    public void fire(Point mousePointer) {
        if (mousePressedTimer == null){
            mousePressedTimer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double kogelX = locationX;
                    double kogelY = locationY;
                    kogels.add(new Kogel(kogelX, kogelY, mousePointer));
                }
            });
        }
        else{
            mousePressedTimer.start();
        }

    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(int currentAngle) {
        this.currentAngle = currentAngle;
    }

    // nodig voor wanneer currentangle negatief wordt
    public int normalizeAngle(int angle) {
        //angle = angle % 360;
        if (angle < 0 || 360 < angle) {
            angle = (angle + 360) % 360;
            return angle;
        } else {
            return angle;
        }
    }


    public int getAngle() {
        return currentAngle;
    }

    public double getDirection(Point target , Point start) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - start.getY(), target.x - start.getX()));
        return angle;
    }

    //endregion
}
