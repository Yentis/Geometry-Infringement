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

        JLabel Title = new GTitle(54, 40);
        JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920, 1080, java.awt.Image.SCALE_SMOOTH))));
        JLabel Profile = new GPane(536, 193, 418, 481);
        JLabel ProfileInfo = new GPane(976, 40, 902, 1009);
        JLabel Username = new GLabel("Username", 36f, 576, 215, 337, 91, true, Color.BLACK);
        JLabel ProfilePicture = new GIcon("profilePicture.png", 577, 329, 336, 324, true);
        JLabel Rank = new GLabel("Unranked", 36f, 1243, 130, 280, 62, false, Color.BLACK);
        JLabel RankPicture = new GIcon("Badges\\NoRank.png", 1026, 77, 168, 168, false);
        JButton MyClan = new GButton("My Clan", 36f, 536, 696, 418, 96);
        JButton EditProfile = new GButton("Edit Profile", 36f, 606, 820, 278, 67);
        JButton Back = new GButton("Back", 36f, 536, 982, 270, 67);
        JButton Achievements = new GButton("Achievements", 36f, 60, 982, 340, 67);


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

