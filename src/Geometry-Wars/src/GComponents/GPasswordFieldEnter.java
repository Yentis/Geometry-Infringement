package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Yentl on 26-Jan-17.
 */
public class GPasswordFieldEnter extends JPasswordField{
        public GPasswordFieldEnter(int x, int y, int width, int height, JButton button) throws IOException, FontFormatException {
            this.setOpaque(true);
            this.setFont(new GFont(24));
            this.setBackground(new Color(255, 255,255,200));
            this.setBounds(x,y,width,height);

            this.addActionListener(evt -> button.doClick());
        }
}
