package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GLabel extends JLabel {
    public GLabel(String text, boolean background, Color foreground) throws IOException, FontFormatException {
        setText(text);
        setFont(new GFont(24));
        setForeground(foreground);
        if (background){
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(new Color(255,255,255,95));
        }
    }
}
