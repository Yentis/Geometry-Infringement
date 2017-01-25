package Game;

import javax.swing.*;

/**
 * Created by Yentl on 25-Jan-17.
 */
public class HitFlash implements Runnable {
    private Schip schip;

    @Override
    public void run() {
        ImageIcon ii = new ImageIcon("resources/Media/schip1-hit.png");
        schip.setImage(ii.getImage());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ii = new ImageIcon("resources/Media/schip1.png");
        schip.setImage(ii.getImage());
    }

    public HitFlash(Schip schip){
        this.schip = schip;
    }
}
