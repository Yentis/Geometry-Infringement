package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Kogel extends Sprite {
    private final int SCREEN_WIDTH = 1024;
    private double kogelSnelheid = 5;
    private Point startingPoint;
    private HitBox hitBox;
    private Image image;
    private int width;
    private int height;


    public Kogel(double x, double y, Point direction){

        super(x, y, direction);

        ImageIcon imageIcon = new ImageIcon("src/Media/kogel1.png");
        width = imageIcon.getIconWidth();
        height = imageIcon.getIconHeight();
        image = imageIcon.getImage();
        startingPoint = new Point();
        startingPoint.setLocation(x, y);

        hitBox = new HitBox(startingPoint, width, height);
        //initKogel();
    }

    @Override
    public Image getImage() {
        return image;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    private void initKogel(){
        loadImage("src/Media/kogel1.png");
    }

    public double getKogelSnelheid() {
        return kogelSnelheid;
    }

    public void move(double velocityX, double velocityY){

        x += velocityX;
        y += velocityY;


        if(x > SCREEN_WIDTH){
            visible = false;
        }
    }
}
