/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GComponents.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * @author Renzie
 */
public class Profile extends GPanel {

    private Profile panel = this;

    /**
     * @param args the command line arguments
     */

    public Profile() throws MalformedURLException, IOException, FontFormatException {
        initComponents();
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {


        //Make components
        //==================================================

        JLabel Title = new GTitle(25, 25);
        JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920, 1080, java.awt.Image.SCALE_SMOOTH))));
        JLabel Profile = new GPane(220, 160, 234, 300);
        JLabel ProfileInfo = new GPane(480, 160, 500, 540);
        JLabel Username = new GLabel("Username", 24f, 245, 175, 180, 64, true, Color.BLACK);
        JLabel ProfilePicture = new GIcon("profilePicture.png", 245, 268, 180, 170, true);
        JLabel Rank = new GLabel("Unranked", 24f, 550, 175, 280, 62, false, Color.BLACK);
        JLabel RankPicture = new GIcon("Badges\\NoRank.png", 520, 175, 130, 130, false);
        JButton MyClan = new GButton("My Clan", 24f, 220, 475, 234, 60);
        JButton EditProfile = new GButton("Edit Profile", 18f, 260, 545, 165, 40);
        JButton Back = new GButton("Back", 24f, 320, 655, 140, 45);
        JButton Achievements = new GButton("Achievements", 24f, 60, 982, 340, 67);


        //==================================================

        //Add Action Listener
        //==================================================
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getMainMenu().setVisible(true);
            }
        });


        //==================================================

        //Add Components
        //==================================================

        this.add(Title);
        this.add(ProfilePicture);
        this.add(MyClan);
        this.add(EditProfile);
        this.add(Back);
        this.add(Achievements);
        this.add(Username);
        this.add(RankPicture);
        this.add(Rank);
        this.add(Profile);
        this.add(ProfileInfo);
        this.add(Background);
        //==================================================

    }
}

