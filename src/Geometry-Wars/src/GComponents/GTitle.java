package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GTitle extends JLabel {

    public GTitle(int x , int y) throws IOException, FontFormatException {
        this.setText("Geometry Wars");
        this.setFont(new GFont(80));
        this.setOpaque(true);
        this.setBackground(new Color(255,255,255,95));
        this.setBounds(x,y,900,131);
    }

}
