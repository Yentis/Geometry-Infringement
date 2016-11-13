package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GInputField extends JTextField{

    public GInputField(int x, int y, int width, int height) throws IOException, FontFormatException {
        this.setOpaque(true);
        this.setFont(new GFont(24));
        this.setBackground(new Color(255,255,255,200));
        this.setBounds(x,y,width,height);
    }

}
