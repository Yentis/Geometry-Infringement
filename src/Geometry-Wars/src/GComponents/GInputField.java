package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GInputField extends JTextField{
    public GInputField(JButton button, int columns) throws IOException, FontFormatException {
        setColumns(columns);
        setFont(new GFont(24));
        setBackground(new Color(255,255,255,200));

        addActionListener(evt -> button.doClick());
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor( new Color(255, 255, 255 ,200) );
        g.fillRect(0, 0, getWidth(), getHeight());
        setOpaque(false);
        super.paintComponent(g);
    }
}
