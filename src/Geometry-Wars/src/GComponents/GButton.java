package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GButton extends JButton{
    public GButton( String text,float fontsize,int x, int y,  int width, int height) throws IOException, FontFormatException {
        this.setOpaque(true);
        this.setText(text);
        this.setFont(new GFont(fontsize));
        this.setBackground(new Color(255, 255, 255 ,200));
        this.setBounds(x ,y ,width ,height );
    }
}
