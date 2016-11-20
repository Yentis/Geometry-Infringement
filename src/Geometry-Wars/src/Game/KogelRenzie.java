package Game;

import java.awt.*;

/**
 * Created by Renzie on 19/11/2016.
 */
public class KogelRenzie extends  SpriteRenzie{
    private final int SCREEN_WIDTH = 1920;
    private double kogelSnelheid = 5;



    public KogelRenzie (double x, double y, Point direction){
        super(x, y, direction);

        initKogel();
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
