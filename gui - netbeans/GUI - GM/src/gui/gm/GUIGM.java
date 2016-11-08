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
        
        //Make components
        //==================================================
                JFrame frame = new JFrame("Test");
                JButton button = new JButton("startGame");
                JButton button2 = new JButton("test");
        
        
        //==================================================
        
        //Set text
        //==================================================
                button.setText("Start Game");
        
        
        //==================================================
        
        
    frame.setContentPane(new JPanel() {
        BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 1920, 1080, this);
            
        }
    });
    frame.setLayout(null);
    
    
    
    
        //Set Bounds
        //==================================================
                 
        button.setBounds(200,200,200,50);
        
        
        //==================================================
        
        //Add Components
        //==================================================
                frame.add(button);
                frame.add(button2);
        
        //==================================================
    
    

    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    }
    
}
