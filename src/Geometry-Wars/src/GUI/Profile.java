/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GComponents.*;
import Game.Speler;

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

    public Profile() throws MalformedURLException, IOException, FontFormatException {
        //do not init
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        Speler speler = window.getSpel().getSpeler();

        //Make components
        //==================================================

        JLabel Title = new GTitle(25, 25);
        JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("resources\\Media\\Background.png")).getImage().getScaledInstance(1920, 1080, java.awt.Image.SCALE_SMOOTH))));
        JLabel Profile = new GPane(220, 160, 234, 300);
        JLabel ProfileInfo = new GPane(480, 160, 500, 540);
        JLabel Username = new GLabel("", 24f, 245, 175, 180, 64, true, Color.BLACK);
        JLabel ProfilePicture = new GIcon("ProfilePictures\\" + speler.getGebruikersnaam() + ".png", 245, 268, 180, 170, true);
        JLabel Rank = new GLabel(speler.getRank(), 24f, 660, 210, 280, 62, false, Color.BLACK);
        JLabel RankPicture = new GIcon("Badges\\" + speler.getRank() + ".png", 520, 175, 130, 130, false);
        JButton Back = new GButton("Back", 24f, 320, 655, 140, 45);
        JButton Achievements = new GButton("Achievements", 24f, 60, 982, 340, 67);

        Username.setText("  " + speler.getGebruikersnaam());
        ProfileInfo.setFont(new GFont(22));
        ProfileInfo.setForeground(Color.BLACK);
        ProfileInfo.setBorder(new EmptyBorder(0, 10, 0, 0));
        ProfileInfo.setText("<html>Level: " + speler.getLevel() + "<br>Experience: " + speler.getExperience() + "<br>Rank: " + speler.getRank() + "<br>Nuggets: " + speler.getNuggets() + "<br>Golden Nuggets: " + speler.getGnuggets() + "<html>");


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

