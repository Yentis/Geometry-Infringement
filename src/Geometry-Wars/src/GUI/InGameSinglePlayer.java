package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import GComponents.*;


/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class InGameSinglePlayer extends GPanel{
    private InGameSinglePlayer panel = this;

    public InGameSinglePlayer() throws MalformedURLException, IOException, FontFormatException {

        initComponents();

    }


    @Override
    public void initComponents() throws IOException, FontFormatException {

        
        ImageIcon PauseImage = new ImageIcon("src\\Media\\pause-128.png");
        JButton pauze = new JButton(PauseImage);
        JLabel pane = new JLabel();
        JLabel pauzepane = new JLabel();

        GLabel health = new GLabel("Health:", 24, 40,18,169,62, false, Color.cyan);
        GLabel score = new GLabel("Score:", 24, 606,18,169,62, false, Color.cyan);
        GLabel schipLvl = new GLabel("Ship lvl:", 24, 200,667,222,62, false, Color.green);
        GLabel droneLvl = new GLabel("Drone lvl:", 24, 600,667,222,62, false, new Color(155,255,204));
        GLabel healthbar = new GLabel("", 24, 15,23,434,47, true, Color.black);
        GLabel dronebar = new GLabel("", 24, 753,672,216,47, true, Color.black);
        GLabel schipbar = new GLabel("", 24, 350,672,216,47, true, Color.black);
        GLabel confirmationlabel = new GLabel("Are you sure?", 30, 470,285,500,60, false, Color.cyan);
        JButton Quit = new GButton("Quit", 24f, 20, 675, 100, 47);
        JButton Yes = new GButton("Yes", 24f, 455, 380, 100, 47);
        JButton No = new GButton("No", 24f, 595, 380, 100, 47);

        pane.setOpaque(true);
        pane.setBackground(new Color(255,255,255,2));
        pane.setBorder(BorderFactory.createMatteBorder(
                5, 5, 5, 5, Color.green));

        pauzepane.setOpaque(true);
        pauzepane.setBackground(new Color(255,255,255,50));
        pauzepane.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.cyan));


        healthbar.setBackground(new Color(255,255,255,95));
        dronebar.setBackground(new Color(255,255,255,95));
        schipbar.setBackground(new Color(255,255,255,95));

        pauze.setOpaque(true);
        pauze.setBackground(new Color(155,255,204,200));
        pauze.setBorder(null);

        //BOUNDS
        pauze.setBounds(510,23,60,58);
        pane.setBounds(235,175,700,430);
        pauzepane.setBounds(395,265,370,245);

        pauzepane.setVisible(false);
        Yes.setVisible(false);
        No.setVisible(false);
        confirmationlabel.setVisible(false);



        //==================================================

        //Add Components
        //==================================================
        this.add(pane);
        this.add(healthbar);
        this.add(schipbar);
        this.add(dronebar);
        this.add(pauze);
        this.add(score);
        this.add(health);
        this.add(schipLvl);
        this.add(droneLvl);
        this.add(pauzepane);
        this.add(Quit);
        this.add(confirmationlabel);
        this.add(Yes);
        this.add(No);



        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(true);
                Yes.setVisible(true);
                No.setVisible(true);
                confirmationlabel.setVisible(true);
            }



        });

        No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(false);
                Yes.setVisible(false);
                No.setVisible(false);
                confirmationlabel.setVisible(false);
            }



        });

        Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauzepane.setVisible(false);
                Yes.setVisible(false);
                No.setVisible(false);
                confirmationlabel.setVisible(false);
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getScoreboard().setVisible(true);

            }



        });



    }


}
