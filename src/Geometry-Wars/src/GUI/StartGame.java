package GUI;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.*;
import javax.swing.*;

import GComponents.*;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class StartGame extends GPanel {
    private StartGame panel = this;

    public StartGame() throws IOException, FontFormatException {
        initComponents();
        }



    @Override
    public void initComponents() throws IOException, FontFormatException {



        JButton Campaign = new GButton("Campaign", 24f, 150,230,300,80);
        JButton War = new GButton("War", 24f, 550,230,300,80);
        JButton SoloGame = new GButton("Solo Game", 24f, 200,350,300,80);
        JButton Coop = new GButton("Co-op", 24f, 250,470,300,80);
        JButton Back = new GButton("Back", 24f, 820, 650, 170, 63);
        JButton Highscores = new GButton("High Scores", 24f, 600, 350, 300, 80);

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);

        //props
        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));
        War.setBackground(new Color(255,255,255,200));
        Campaign.setBackground(new Color(255,255,255,200));
        SoloGame.setBackground(new Color(255,255,255,200));
        Coop.setBackground(new Color(255,255,255,200));
        Back.setBackground(new Color(255,255,255,200));
        Highscores.setBackground(new Color(255,255,255,200));




        //Bounds

        label.setBounds(25,25,650,100);


        this.add(Campaign);
        this.add(War);
        this.add(SoloGame);
        this.add(Coop);
        this.add(label);
        this.add(Back);
        this.add(Highscores);

        //ActionListeners

        Campaign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartGameCampaign().setVisible(true);

            }
        });

        Coop.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartCoop().setVisible(true);
            }
        });

        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getMainMenu().setVisible(true);
            }
        });

        Highscores.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getHighScores().setVisible(true);
            }
        });
    }}
