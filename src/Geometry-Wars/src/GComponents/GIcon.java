package GComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 *
 * @author Matthias Carlier
 */
public class GIcon extends JLabel{
    public GIcon(String file, int width, int height, boolean background) throws IOException, FontFormatException {
        setIcon(new ImageIcon(((new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Media/" + file))).getImage().getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH)))));
        if (background){
            setOpaque(true);
            setBackground(new Color(255,255,255,200));
        }
    }
}
