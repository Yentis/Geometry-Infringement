package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GInputField extends JTextField{
    public GInputField(int x, int y, int width, int height, JButton button) throws IOException, FontFormatException {
        this.setFont(new GFont(24));
        this.setBackground(new Color(255,255,255,200));
        this.setBounds(x,y,width,height);

        this.addActionListener(evt -> button.doClick());
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor( new Color(255, 255, 255 ,200) );
        g.fillRect(0, 0, getWidth(), getHeight());
        this.setOpaque(false);
        super.paintComponent(g);
    }
}
