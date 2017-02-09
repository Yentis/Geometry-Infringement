package Game;

import GComponents.GFont;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 25/12/2016.
 */
public class Effect extends Sprite{
    private String text;
    private long appearTime;
    private long disappearTime;
    private double speed;
    private Color color;
    private GFont font;

    private Effect(Spel spel, String text, Point currentLocation, long disappearTime, double speed, Color color, GFont font){
        super(spel, currentLocation);
        this.text = text;
        this.color = color;
        this.speed = speed;
        appearTime = System.currentTimeMillis();
        this.disappearTime = appearTime + disappearTime;
        this.font = font;
    }

    public static Effect XPGain(Spel spel, Enemy enemy) throws IOException, FontFormatException {
        return new Effect(spel, "+" + enemy.getExperience() + "XP",enemy.getCurrentLocation(), 2000, 1, Color.cyan, new GFont(20f));
    }

    public static Effect takeDamage(Spel spel, Enemy enemy) throws IOException, FontFormatException {
        return new Effect(spel, "-" + enemy.getKracht() + "HP", enemy.getCurrentLocation(), 2000, 1, Color.red, new GFont(20f));
    }

    public static Effect levelUp(Spel spel, Schip schip){
        try {
            return new Effect(spel, "LEVEL UP! + \nDMG +25", schip.getCurrentLocation(),2000, 0, Color.green, new GFont(20f));
        } catch (FontFormatException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Effect roundIndicator(Graphics g, Spel spel, int roundCounter) throws IOException, FontFormatException {
        String message;
        Font font = new GFont(40f);

        if(roundCounter % 10 == 0){
            message = "Boss Round";
        } else {
            message = "Round: " + roundCounter;
        }
        int width = g.getFontMetrics(font).stringWidth(message);

        return new Effect (spel, message, new Point((spel.getScreenSize().width / 2) - (width / 2), spel.getScreenSize().height / 4), 2000, 0, Color.yellow, new GFont(40f));
    }

    public GFont getFont() {
        return font;
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

    public class XPGain extends Effect {

        public XPGain(Spel spel, Enemy enemy) throws IOException, FontFormatException {
            super(spel, "+" + enemy.getExperience() + "XP",enemy.getCurrentLocation(), 2000, 1, Color.cyan, new GFont(20f));
        }
    }
}
