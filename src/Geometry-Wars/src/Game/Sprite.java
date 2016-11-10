package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected boolean visible;
    protected int mousex;
    protected int mousey;

    public Sprite(int x, int y, int mousex, int mousey){
        this.x = x;
        this.y = y;
        this.mousex = mousex;
        this.mousey = mousey;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMousex() {
        return mousex;
    }

    public int getMousey() {
        return mousey;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
