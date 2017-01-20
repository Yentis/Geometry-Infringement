package Game;

import java.awt.*;

/**
 * Created by Renzie on 25/12/2016.
 */
public class CollisionEffect extends Sprite{
    private String text;
    private long appearTime;
    private long disappearTime;
    private double speed;
    private Color color;


    public CollisionEffect(String text, Point currentLocation, long disappearTime, double speed, Color color){
        super(currentLocation);
        this.text = text;
        this.color = color;
        this.speed = speed;
        appearTime = System.currentTimeMillis();
        this.disappearTime = appearTime + disappearTime;
    }

    public static CollisionEffect XPGain(Enemy enemy){
        return new CollisionEffect("+" + enemy.getExperience() + "XP",enemy.getCurrentLocation(), 2000, 1, Color.cyan);
    }

    public static CollisionEffect takeDamage(Enemy enemy){
        return new CollisionEffect("-" + enemy.getKracht() + "HP", enemy.getCurrentLocation(), 2000, 1, Color.red);
    }

    public double getSpeed() {
        return speed;
    }

    public Color getColor() {
        return color;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public long getAppearTime() {
        return appearTime;
    }

    public long getDisappearTime() {
        return disappearTime;
    }

    public void setAppearTime(long appearTime) {
        this.appearTime = appearTime;
    }

    public void setDisappearTime(long disappearTime) {
        this.disappearTime = disappearTime;
    }

    public class XPGain extends CollisionEffect{

        public XPGain(Enemy enemy) {
            super("+" + enemy.getExperience() + "XP",enemy.getCurrentLocation(), 2000, 1, Color.cyan);
        }
    }
}
