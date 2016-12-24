package GComponents;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Matthias Carlier
 */

public class GPane extends JLabel{
    public GPane(int x, int y, int width, int height){
        this.setBounds(x,y,width, height);
        this.setOpaque(true);
        this.setBackground(new Color(255,255,255,95));
    }
}
