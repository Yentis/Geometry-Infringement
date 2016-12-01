package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Sprite {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected Image image;
    protected boolean visible;
    protected Point target;

    public Sprite(double x, double y, Point target){
        this.x = x;
        this.y = y;
        this.target = target;
        visible = true;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point gettarget() { return target; }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
