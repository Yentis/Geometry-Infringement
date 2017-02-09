package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GButtonPause extends JButton{
    public GButtonPause( String text) throws IOException, FontFormatException {
        this.setText(text);
        this.setFont(new GFont(24f));
        this.setBackground(new Color(255, 255, 255 ,200));
    }
}
