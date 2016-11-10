package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GLabel extends JLabel {

    public GLabel(String text, float floatsize, int x, int y, int width, int height, boolean background, Color foreground) throws IOException, FontFormatException {
        this.setText(text);
        this.setFont(new GFont(floatsize));
        this.setForeground(foreground);
        this.setBounds(x,y,width,height);
        if (background){
            this.setOpaque(true);
            this.setBackground(new Color(255,255,255,200));

        }
    }
   

}


