/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.gm;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Renzie
 */
public class GUIGM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        // TODO code application logic here
        JFrame frame = new JFrame("Test");

    frame.setContentPane(new JPanel() {
        BufferedImage image = ImageIO.read(new File("C:\\Users\\Matthias Carlier\\Desktop\\GitHub\\Geometry-Infringement\\Geometry-Infringement\\src\\Geometry-Wars\\src\\Media\\Background.png"));
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 300, 300, this);
        }
    });

    frame.add(new JButton("Test Button"));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setVisible(true);
    }
    
}
