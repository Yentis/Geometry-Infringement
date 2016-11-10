package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static com.sun.javafx.webkit.UIClientImpl.toBufferedImage;

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

    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht, String image){
        ImageIcon ii = new ImageIcon(image);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        this.image = ii.getImage();
        x = 40;
        y = 60;
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
        x += dx;
        y += dy;
        if(dr > 0 && r <= 90){
            r += dr;
        } else if (dr < 0 && r >= -90){
            r += dr;
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
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
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
                break;
        }
    }

    //endregion
}
