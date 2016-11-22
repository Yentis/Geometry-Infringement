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



        JButton Campaign = new GButton("Campaign", 36f, 150,230,300,80);
        JButton War = new GButton("War", 36f, 550,230,300,80);
        JButton SoloGame = new GButton("Solo Game", 36f, 200,350,300,80);
        JButton Coop = new GButton("Co-op", 36f, 250,470,300,80);
        JButton Back = new GButton("Back", 36f, 670,620,300,80);

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);

        //props
        label.setOpaque(true);
        label.setFont(new GFont(80));
        label.setBackground(new Color(255,255,255,95));
        War.setBackground(new Color(255,255,255,200));
        Campaign.setBackground(new Color(255,255,255,200));
        SoloGame.setBackground(new Color(255,255,255,200));
        Coop.setBackground(new Color(255,255,255,200));
        Back.setBackground(new Color(255,255,255,200));




        //Bounds

        label.setBounds(25,25,800,125);


        this.add(Campaign);
        this.add(War);
        this.add(SoloGame);
        this.add(Coop);
        this.add(label);
        this.add(Back);

        Campaign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartGameCampaign().setVisible(true);
            }

        });

    }



    /*public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new StartGame();
    }*/



    }

