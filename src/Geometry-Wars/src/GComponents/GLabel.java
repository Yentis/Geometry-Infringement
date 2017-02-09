package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GLabel extends JLabel {
    public GLabel(String text, int x, int y, int width, int height, boolean background, Color foreground) throws IOException, FontFormatException {
        setText(text);
        setFont(new GFont(24f));
        setForeground(foreground);
        setBounds(x,y,width,height);
        if (background){
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(new Color(255,255,255,95));
        }
    }

    public GLabel(String text, boolean background, Color foreground) throws IOException, FontFormatException {
        setText(text);
        setFont(new GFont(24f));
        setForeground(foreground);
        if (background){
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(new Color(255,255,255,95));
        }
    }
}
