package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Renzie on 19/11/2016.
 */
public class SchipRenzie {

    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int kracht = 10;
   // private int x;
   // private int y;
    private int r;
    private double dx;
    private double dy;
    private int dr;
    private Image image;
    private int width;
    private int height;
    private String direction;
    private ArrayList kogels = new ArrayList();
    private Point location = new Point();
    private double locationX = location.getX();
    private double locationY = location.getY();
    private double angle;


    //endregion

    //region Constructors

    public SchipRenzie(int nr, int hp, int kracht, String image){
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        locationX = 700;
        locationY = 300;
        location.setLocation(locationX, locationY);
        System.out.println(location);
        //y = 300;
        //r = 0;
        this.nr = nr;
        this.hp = hp;
        this.kracht = kracht;
    }

    //endregion

    //region Properties

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Image getImage() {
        return image;
    }

   /* public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }*/

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList getKogels() {
        return kogels;
    }

    public Point getLocation() { return location; }

    //endregion

    //region Behaviour

    public void addKracht(int amount) {
        this.kracht += amount;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void beweegSchip(){

        if (r > 360){
            r -= 360;
        } else if (r < -360){
            r += 360;
        }

        if (locationX > 1024) {
            locationX = 1024;
        } else if (locationX < 0) {
            locationX = 0;
        } else if (locationY > 768){
           locationY = 768;
        } else if (location.getY() < 0){
            locationY = 0;
        }

        location.setLocation(locationX += dx, locationY += dy);

        r += dr;



        //region Horror
        /*if (dy < 0){
            //if at correct position clear direction
            if(r <= 3 && r >= -3){
                direction = "";
                r = 0;
            } else {
                //calculate shortest distance to 0 or 360
                if (Objects.equals(direction, "left") || (abs(0 - r) < abs(360 - r))){
                    System.out.println("direction: " + direction);
                    direction = "left";
                    if(r < 0){
                        r -= dr;
                    } else {
                        r += dr;
                    }
                } else if (Objects.equals(direction, "right")) {
                    direction = "right";
                    if(r < 0){
                        r += dr;
                    } else {
                        r -= dr;
                    }
                }
            }
        } else if (dy > 0){
            System.out.println("down");
            System.out.println(r);

            //if at correct position clear direction
            if((abs(r) <= 183 && abs(r) >= 177)){
                direction = "";
                r = 180;
            } else {
                //calculate shortest distance to 180 or -180
                if (Objects.equals(direction, "left") || (abs(180 - r) < abs(-180 - r))){
                    System.out.println("direction: " + direction);
                    direction = "left";
                    if(r < 0){
                        r -= dr;
                    } else {
                        r += dr;
                    }
                } else if (Objects.equals(direction, "right")) {
                    direction = "right";
                    if(r < 0){
                        r += dr;
                    } else {
                        r -= dr;
                    }
                }
            }
        } else if (dr > 0 && r <= 90 && r >= -270){
            System.out.println("right");
            r += dr;
        } else if (dr < 0 && r >= -90 && r <= 270){
            System.out.println("left");
            r+= dr;
        }*/
        //endregion
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                dx = -3;
                rotate(5);
                break;
            case KeyEvent.VK_RIGHT:
                rotate(-5);
                dx = 3;
                break;
            case KeyEvent.VK_UP:

                dy = -3;
                break;
            case KeyEvent.VK_DOWN:
                dy = 3;
                break;
        }
    }

    public void mousePressed(MouseEvent e){
        fire(e.getPoint());
    }

    public void fire(Point mousePointer){
        kogels.add(new KogelRenzie(location.getX(), location.getY(), mousePointer));
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        switch(key){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
        }
    }

    public void rotate(double degrees){
        angle += Math.toRadians(degrees); // 5 degrees per 100 ms = 50 degrees/second
        while (angle > 2 * Math.PI)
            angle -= 2 * Math.PI;  // keep angle in reasonable range.
    }

    public double getAngle(Point target){
        double angle = Math.toDegrees(Math.atan2(target.getY() - location.getY(), target.x - location.getX()));

        //if(angle < 0){
        //    angle += 360;
       // }
        return angle;
    }

    //endregion
}
