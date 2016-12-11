package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip extends Sprite{

    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int kracht = 10;
    private int r;
    private double dx;
    private double dy;
    private ArrayList<Kogel> kogels = new ArrayList<Kogel>();
    private Point location = new Point();
    private double locationX = location.getX();
    private double locationY = location.getY();
    private int currentAngle;
    private Movement move;


    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image) {
        super(image);
        locationX = 700;
        locationY = 300;
        location.setLocation(locationX, locationY);
        currentAngle = 0;
        this.nr = nr;
        this.hp = hp;
        this.kracht = kracht;
        move = new Movement(this);
    }


    //endregion

    //region Properties

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Kogel> getKogels() {
        return kogels;
    }

    public Point getLocation() {
        return location;
    }

    public int getAngle() {
        return currentAngle;
    }

    //endregion


    //region Behaviour


    public void addKracht(int amount) {
        this.kracht += amount;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void beweegSchip() {

        locationX = limitToBorders(locationX , 0 , 1024);
        locationY = limitToBorders(locationY , 0 , 768);

        location.setLocation(locationX += dx, locationY += dy);
    }

    private double limitToBorders(double currLocation, double minBorder, double maxBorder){
        if (currLocation > maxBorder) {
            return maxBorder;
        } else if (currLocation < minBorder) {
           return minBorder;
        }
        return currLocation;
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
        /*if (mousePressedTimer != null){
            mousePressedTimer.stop();
            System.out.println("mouse released");
        }*/
    }

    private void addKogels(Kogel k){
        if (kogels.size() < 30){
            kogels.add(k);
        } else {
            kogels.clear();
            System.out.println("kogels cleared");
        }
    }

    public void fire(Point mousePointer) {
        /*if (mousePressedTimer == null){
            mousePressedTimer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {*/
                    double kogelX = locationX;
                    double kogelY = locationY;
                    addKogels(new Kogel(kogelX, kogelY, mousePointer,"src/Media/kogel1.png"));
              /*  }
            });
       }
        else{
            mousePressedTimer.start();
        }
            */
    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(int currentAngle) {
        this.currentAngle = currentAngle;
    }

    // Dit zorgt ervoor dat de angle binnen 360 blijft.
    public int normalizeAngle(int angle) {
        if (angle < 0 || 360 < angle) {
            angle = (angle + 360) % 360;
            return angle;
        } else {
            return angle;
        }
    }

    //endregion
}
