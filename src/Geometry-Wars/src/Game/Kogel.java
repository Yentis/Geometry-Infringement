package Game;

import java.awt.*;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Kogel extends Sprite {
    private final int SCREEN_WIDTH = 1024;
    private double kogelSnelheid = 5;
    private Point startingPoint;


    public Kogel(double x, double y, Point direction){
        super(x, y, direction);
        startingPoint = new Point();
        startingPoint.setLocation(x, y);

        initKogel();
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
