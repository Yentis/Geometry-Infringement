package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

import GComponents.GButton;
import GComponents.GFont;
import GComponents.GLabel;
import GComponents.GPanel;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class StartGameCampaign extends GPanel {
    private StartGameCampaign panel = this;

    public StartGameCampaign() throws MalformedURLException, IOException, FontFormatException {

       
        //do not init


    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Integer> upgradeList = new ArrayList<>();
        JButton Continue = new GButton("Continue", 24f, 150,230,300,80);
        JButton NewCampaign = new GButton("New Campaign", 24f, 200,350,350,80);
        JButton ClearCampaign = new GButton("Clear Campaign", 24f, 250,470,350,80);
        JButton Back = new GButton("Back", 24f, 820, 650, 170, 63);

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        JLabel labelDrone = new GLabel("Chose drone: ", 18, 500,250,200,50,false, Color.white);
        String[] drones = new String[3];

        try {
            upgradeList = window.getSpel().checkUpgrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        drones[0] = "";

        int i = 1;
        for (int j:upgradeList) {
            switch (j){
                case 4:
                case 5:
                case 6:
                    drones[i] = window.getSpel().getUpgrades().get(j).getNaam();
                    i++;
                    break;
            }
        }

        JComboBox<String> chooseDrone = new JComboBox<String>(drones);

        //props
        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));
        Continue.setBackground(new Color(255,255,255,200));
        NewCampaign.setBackground(new Color(255,255,255,200));
        ClearCampaign.setBackground(new Color(255,255,255,200));

        Back.setBackground(new Color(255,255,255,200));
        chooseDrone.setFont(new GFont(18));
        //Bounds
        label.setBounds(25,25,650,100);
        chooseDrone.setBounds(650,250,200,50);

        this.add(labelDrone);
        this.add(chooseDrone);
        this.add(Continue);
        this.add(NewCampaign);
        this.add(ClearCampaign);
        this.add(label);
        this.add(Back);

        //Action listeners
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartGame().setVisible(true);
            }
        });
        Continue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getInGame().setVisible(true);
                window.getInGame().getStartGame().setVisible(true);
                window.getInGame().getGameEnd().setVisible(false);
                window.getInGame().getPause().setVisible(false);
                window.getInGame().setCoop(false);
            }
        });
        chooseDrone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                if(chooseDrone.getSelectedItem() == null){
                    window.getSpel().getSpeler().setActiveDrone("");
                } else {
                    window.getSpel().getSpeler().setActiveDrone(chooseDrone.getSelectedItem().toString());
                }
                System.out.println("Drone: " + window.getSpel().getSpeler().getActiveDrone());
            }
        });
    }
}
