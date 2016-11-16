/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 *
 * @author Matthias Carlier
 */
public class GIcon extends JLabel{
    public GIcon(String file, int x, int y, int width, int height, boolean background) throws IOException, FontFormatException {
        String Source = "src\\Media\\";
        this.setIcon(new ImageIcon(((new ImageIcon(Source + file)).getImage().getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH))));
        this.setBounds(x,y,width,height);
        if (background){
            this.setOpaque(true);
            this.setBackground(new Color(255,255,255,200));

        }
    }
}
