package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public class GPasswordField extends JPasswordField {
    public GPasswordField(JButton button, int columns) throws IOException, FontFormatException {
        setColumns(columns);
        setFont(getFont().deriveFont(24f));
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
