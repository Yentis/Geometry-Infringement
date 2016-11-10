package Game;

/**
 * Created by Yentl-PC on 10/11/2016.
 */
public class Kogel extends Sprite {

    private final int SCREEN_WIDTH = 1640;
    private int kogelSnelheid = 5;

    public Kogel (double x, double y){
        super(x, y);

        initKogel();
    }

    private void initKogel(){
        loadImage("src/Media/kogel1.png");
    }

    public void move(double velocityX, double velocityY){
        moving = true;
        x += velocityX;
        y += velocityY;

        if(x > SCREEN_WIDTH){
            visible = false;
        }
    }
}
