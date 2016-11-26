package GUI;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.*;

import GComponents.GButton;
import GComponents.GFont;
import GComponents.GLabel;
import GComponents.GPanel;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class Logout extends GPanel {
    private Logout panel = this;

    public Logout() throws MalformedURLException, IOException, FontFormatException {


        initComponents();


    }

    @Override
    public void initComponents() throws IOException, FontFormatException {


        JLabel LoggedOut = new GLabel("You are now logged out.", 36f, 320, 370, 600, 62, false, Color.white);
        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        JButton Exit = new GButton("Exit", 24f, 820, 650, 170, 63);
        JButton Login = new GButton("Login", 24f, 635,650, 170, 63);

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));

        label.setBounds(25,25,650,100);

        this.add(LoggedOut);
        this.add(label);
        this.add(Exit);
        this.add(Login);


        //Action listeners

        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getLogin().setVisible(true);
            }
        });

    }
}
