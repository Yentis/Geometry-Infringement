package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

import static com.sun.javafx.webkit.UIClientImpl.toBufferedImage;
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

   // private boolean up, down, left, right;
    private boolean keyPressed;
    private static boolean keys[] = new boolean[65535];
    private Timer left, right, up, down;



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

    /*public void keyPressed(KeyEvent e) {
        System.out.println("keypressed");
        keyPressed = true;
        int key = e.getKeyCode();
        // Rotation: graden worden in radialen omgezet in Board
        // TODO stuttering weghalen
        switch (key) {
            case KeyEvent.VK_LEFT:
                left = new Timer(30, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        normalizeAngle(currentAngle);
                        rotate(10, 270);
                    }
                });left.start();
                //left = true;
                dx = -3;
                break;
            case KeyEvent.VK_RIGHT:
                //right = true;
                normalizeAngle(currentAngle);
                rotate(10, 90);
                dx = 3;
                break;
            case KeyEvent.VK_UP:
               // up = true;
                rotate(10, 0);
                dy = -3;
                break;
            case KeyEvent.VK_DOWN:
                //down = true;
                rotate(10, 180);
                dy = 3;
                break;
        }
    }



    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("keyreleased");

        switch (key) {
            case KeyEvent.VK_LEFT:
               // left = false;
                dx = 0;
                left.stop();
                break;
            case KeyEvent.VK_RIGHT:
               // right = false;
                dx = 0;
                break;
            case KeyEvent.VK_UP:
               // up = false;
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
               // down = false;
                dy = 0;
                break;
        }
    }
*/
    public void mousePressed(MouseEvent e) {
        fire(e.getPoint());
    }

    public void fire(Point mousePointer) {
        kogels.add(new Kogel(location.getX(), location.getY(), mousePointer));
    }


    public double rotate(int degrees, int targetAngle) {
        System.out.println("in rotate");
        if (keyPressed){
            System.out.println(keyPressed);
            if (currentAngle - targetAngle == 0) return currentAngle;

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
            currentAngle = normalizeAngle(currentAngle);
        }



        return currentAngle;
    }

    public int rotateClockwise(int degrees) {
        return currentAngle += degrees;

    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(int currentAngle) {
        this.currentAngle = currentAngle;
    }

    public int rotateCounterClockwise(int degrees) {
        return currentAngle -= degrees;
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
        return angle;
    }

    //endregion
}
