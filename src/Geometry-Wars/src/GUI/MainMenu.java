/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Renzie
 */
public class MainMenu{



    public MainMenu() throws MalformedURLException, IOException, FontFormatException {





        //Make components
        //==================================================
       // JFrame frame = new JFrame("Test");

        JButton StartGame = new JButton("StartGame");
        JButton Upgrades = new JButton("Upgrades");
        JButton Profile = new JButton("Profile");
        JButton Settings = new JButton("Settings");
        JLabel label = new JLabel("title", SwingConstants.CENTER);





        //==================================================

        //Set Properties
        //==================================================
        //label.setFont(new GFont(80));

        label.setOpaque(true);
        label.setBackground(new Color(255,255,255,95));
        StartGame.setFont(new GFont(36));
        StartGame.setOpaque(true);
        StartGame.setBackground(new Color(255,255,255,200));
        Upgrades.setFont(new GFont(36));
        Upgrades.setOpaque(true);
        Upgrades.setBackground(new Color(255,255,255,200));
        Profile.setFont(new GFont(36));
        Profile.setOpaque(true);
        Profile.setBackground(new Color(255,255,255,200));
        Settings.setFont(new GFont(36));
        Settings.setOpaque(true);
        Settings.setBackground(new Color(255,255,255,200));

        //==================================================

        /*frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });*/
       // frame.setLayout(null);




        //Set Bounds
        //==================================================

        StartGame.setBounds(350,250,300,80);
        Upgrades.setBounds(475,425,300,80);
        Profile.setBounds(535,600,300,80);
        Settings.setBounds(550,775,300,80);
        label.setBounds(25,25,800,125);

        //==================================================

        //Add Components
        //==================================================
        Window.get.add(StartGame));
        frame.add(Profile);
        frame.add(Upgrades);
        frame.add(Settings);
        frame.add(label);

        //==================================================




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }





    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new MainMenu();
    }
}
