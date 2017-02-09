package GComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GButton extends JButton{
    public GButton( String text) throws IOException, FontFormatException {
        setBorder(new EmptyBorder(15, 20, 15, 20));
        setText(text);
        setFont(new GFont(24));
        setBackground(new Color(255, 255, 255 ,200));
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor( new Color(255, 255, 255 ,200) );
        g.fillRect(0, 0, getWidth(), getHeight());
        setOpaque(false);
        super.paintComponent(g);
    }
}
