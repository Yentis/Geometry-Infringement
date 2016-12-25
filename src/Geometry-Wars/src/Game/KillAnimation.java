package Game;

import java.awt.*;

/**
 * Created by Renzie on 25/12/2016.
 */
public class KillAnimation extends Sprite{
    private String text;
    private long appearTime;
    private long disappearTime;

    public KillAnimation(String text, Point currentLocation){
        super(currentLocation);
        this.text = text;
        appearTime = System.currentTimeMillis();
        disappearTime = appearTime + 2000;
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
}
