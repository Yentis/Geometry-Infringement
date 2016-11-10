package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
    private int x;
    private int y;
    private int r;
    private int dx;
    private int dy;
    private int dr;
    private Image image;
    private int width;
    private int height;
    private String direction;

    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image){
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        x = 700;
        y = 300;
        r = 0;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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

        if (x > 1640){
            x = 1640;
        } else if (y > 800){
            y = 800;
        }

        x += dx;
        y += dy;

        if (dy < 0){
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
        } else if (dr > 0 && r <= 90){
            System.out.println("right");
            r += dr;
        } else if (dr < 0 && r >= -90 && r <= 270){
            System.out.println("left");
            if (r+90 < r - 90){
                r -= dr;
            } else {
                r += dr;
            }
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        switch(key){
            case KeyEvent.VK_LEFT:
                dx = -1;
                dr = -3;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 1;
                dr = 3;
                break;
            case KeyEvent.VK_UP:
                dy = -1;
                dr = -3;
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
                dr = 3;
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        switch(key){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                dx = 0;
                dr = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                dy = 0;
                dr = 0;
                break;
        }
    }

    //endregion
}
