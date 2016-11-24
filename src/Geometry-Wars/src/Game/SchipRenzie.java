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
    private int currentAngle;


    //endregion

    //region Constructors

    public SchipRenzie(int nr, int hp, int kracht, String image) {
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        locationX = 700;
        locationY = 300;
        location.setLocation(locationX, locationY);
        System.out.println(location);
        currentAngle = 0;
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

    public Point getLocation() {
        return location;
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

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                dx = -3;
                normalizeAngle(currentAngle);
                rotate(10, 270);
                break;
            case KeyEvent.VK_RIGHT:

                normalizeAngle(currentAngle);
                rotate(10, 90);
                dx = 3;
                break;
            case KeyEvent.VK_UP:
                rotate(10, 0);
                dy = -3;
                break;
            case KeyEvent.VK_DOWN:
                rotate(10, 180);
                dy = 3;
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
        fire(e.getPoint());
    }

    public void fire(Point mousePointer) {
        kogels.add(new KogelRenzie(location.getX(), location.getY(), mousePointer));
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
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

    public double rotate(int degrees, int targetAngle) {
        int verschil = (currentAngle - targetAngle) % 360;
        currentAngle = normalizeAngle(currentAngle);


        if (currentAngle - targetAngle == 0) return currentAngle;




        /*if (Math.abs(currentAngle - targetAngle) < 180) {
            System.out.println("directly");
            // Rotate current directly towards target.
            if (currentAngle < targetAngle) currentAngle += degrees ;
            else currentAngle -= degrees;
        } else {
            System.out.println("not directly");
            // Rotate the other direction towards target
            if (currentAngle < targetAngle) currentAngle-= degrees ;
            else currentAngle+= degrees ;
        }
        currentAngle = ((currentAngle % 360) + 360) % 360;

*/
        // als angle + target groter is dan doe je het omgekeerde
        //    270              90 =  350             270                90      = 170

        // Probleem : bij 270 CA zal CA naar 260 gaan maar bij 260 gaat hij naar 270
        // Probleem : bij 180 -> 270  180 + 270 % 360 = 90    180 - 270 % 360 = 270
        /*if ((currentAngle + targetAngle) % 180 > (currentAngle - targetAngle) % 180 ){

            currentAngle += normalizeAngle(degrees);
            currentAngle = normalizeAngle(currentAngle);
            System.out.println(" in 1ste" + (currentAngle + targetAngle) % 360);
        } else {

            currentAngle -= normalizeAngle(degrees);
            currentAngle = normalizeAngle(currentAngle);
            System.out.println(" int 2e" + (currentAngle + targetAngle) % 360);
        }
        System.out.printf("CurrentAngle = %d, targetAngle = %d\n",
                currentAngle, targetAngle);
*/
        if (currentAngle < targetAngle && (targetAngle - currentAngle) % 360 <= 180) {
            rotateClockwise(degrees);
        }
        if (targetAngle < currentAngle && currentAngle - targetAngle <= 180) {
            rotateCounterClockwise(degrees);
        }
        if (currentAngle < targetAngle && targetAngle - currentAngle >= 180) {
            rotateCounterClockwise(degrees);
        }
        if (targetAngle < currentAngle && currentAngle - targetAngle >= 180) {
            rotateClockwise(degrees);
        }

      /*  if ((currentAngle - targetAngle) % 360 == 180 && targetAngle < currentAngle){
            rotateCounterClockwise(degrees);
        }
        if ((currentAngle - targetAngle) % 360 == 180 && targetAngle > currentAngle){
            rotateClockwise(degrees);
        }*/
        System.out.printf("CurrentAngle = %d, targetAngle = %d\n",
                currentAngle, targetAngle);


        /*if (verschil < 180 && targetAngle > currentAngle) { currentAngle += degrees; }
        if (verschil < 180 && targetAngle < currentAngle) { currentAngle -= degrees; }
        if (verschil > 180 && targetAngle > currentAngle) { currentAngle -= degrees; }
        if (verschil > 180 && targetAngle < currentAngle) { currentAngle += degrees; }
        if (verschil == 180 || verschil == 0) { currentAngle += degrees; }*/
       /* if (currentAngle + degrees <= targetAngle){
            currentAngle += targetAngle - currentAngle;
        } else if (currentAngle + degrees >= targetAngle){

        }
        currentAngle += Math.toRadians(degrees); // 5 degrees per 100 ms = 50 degrees/second
        while (currentAngle > 2 * Math.PI) {
            currentAngle -= 2 * Math.PI;  // keep angle in reasonable range.
        }
*/
        return currentAngle;
    }

    public void rotateClockwise(int degrees) {
        currentAngle += degrees;
    }

    public void rotateCounterClockwise(int degrees) {
        currentAngle -= degrees;
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

    public double getDirection(Point target) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - location.getY(), target.x - location.getX()));

        //if(angle < 0){
        //    angle += 360;
        // }
        return angle;
    }

    //endregion
}
