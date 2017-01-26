package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GButtonPause extends JButton{
    public GButtonPause( String text,float fontsize,int x, int y,  int width, int height) throws IOException, FontFormatException {
        this.setText(text);
        this.setFont(new GFont(fontsize));
        this.setBackground(new Color(255, 255, 255 ,200));
        this.setBounds(x ,y ,width ,height );
    }
}
