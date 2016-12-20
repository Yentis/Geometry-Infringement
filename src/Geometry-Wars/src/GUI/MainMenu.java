/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import GComponents.*;


/**
 * @author Renzie
 */
public class MainMenu extends GPanel {

    private String url = "https://discordapp.com/";
    private MainMenu panel = this;


    public MainMenu() throws IOException, FontFormatException {

        initComponents();

        // resize components according to frame size
        resizeComponents(getWidth(), getHeight());

    }


    @Override
    public void initComponents() throws IOException, FontFormatException {
        //Make components
        //==================================================

        JButton StartGame = new GButton("Start Game", 24f, 120,200,260,70);
        JButton Upgrades = new GButton("Upgrades", 24f, 190,320,260,70);
        JButton Profile = new GButton("Profile", 24f, 237,440,260,70);
        JButton Settings = new GButton("Settings", 24f, 235,560,260,70);
        JButton FriendsBtn = new GButton("Friends", 24f, 820, 25, 170,63);
        JButton LogOut = new GButton("Logout", 24f, 635,650, 170, 63);
        JButton Quit = new GButton("Quit", 24f, 820, 650, 170, 63);
        JButton Discord = new JButton(new ImageIcon(((new ImageIcon("src\\Media\\Discord.jpg")).getImage()).getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH)));
        JLabel Title = new JLabel("Geometry Wars", SwingConstants.CENTER);
        JLabel JoinDiscord = new JLabel("Join Server!");
        JList Friends = new JList();
        GLabel confirmationlabel = new GLabel("Are you sure you want to quit?", 30, 320,285,550,60, false, Color.cyan);
        JButton Yes = new GButton("Yes", 24f, 455, 380, 100, 47);
        JButton No = new GButton("No", 24f, 595, 380, 100, 47);
        JLabel pauzepane = new JLabel();


        //==================================================


        //Add Action Listener
        //==================================================

        // TODO give same sort of buttons the same kind of function
        StartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartGame().setVisible(true);
            }

        });

        FriendsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!Friends.isShowing()) {
                    Friends.setVisible(true);
                } else {
                    Friends.setVisible(false);
                }

            }
        });
        Discord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                } catch (java.io.IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });


        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getSpel().logOut();
                window.getLogout().setVisible(true);
            }
        });

        Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getProfile().setVisible(true);


                // }
            }
        });
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(true);
                Yes.setVisible(true);
                No.setVisible(true);
                confirmationlabel.setVisible(true);
                StartGame.setVisible(false);
                Profile.setVisible(false);
                Upgrades.setVisible(false);
                Settings.setVisible(false);

                Friends.setVisible(false);
                Quit.setVisible(false);
                LogOut.setVisible(false);
                Discord.setVisible(false);
                FriendsBtn.setVisible(false);
                JoinDiscord.setVisible(false);


            }



        });

        No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(false);
                Yes.setVisible(false);
                No.setVisible(false);
                confirmationlabel.setVisible(false);
                StartGame.setVisible(true);
                Profile.setVisible(true);
                Upgrades.setVisible(true);
                Settings.setVisible(true);

                Friends.setVisible(true);
                Quit.setVisible(true);
                LogOut.setVisible(true);
                Discord.setVisible(true);
                FriendsBtn.setVisible(true);
                JoinDiscord.setVisible(true);

            }



        });

        Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getScoreboard().setVisible(true);
                System.exit(0);


            }



        });


        //==================================================


        //Set Properties
        //==================================================
        Title.setFont(new GFont(65));
        Title.setOpaque(true);
        Title.setBackground(new Color(255, 255, 255, 95));
        JoinDiscord.setFont(new GFont(24));
        JoinDiscord.setForeground(Color.white);

        Friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Friends.setVisible(false);
        Friends.setBackground(new Color(255, 255, 255, 95));

        pauzepane.setOpaque(true);
        pauzepane.setBackground(new Color(255,255,255,50));
        pauzepane.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.cyan));


        //==================================================

        //Set Bounds
        //==================================================


        Title.setBounds(25,25,650,100);
        JoinDiscord.setBounds(102, 1000, 180, 35);
        Friends.setBounds(820, 129, 170, 300);
        Discord.setBounds(35, 650, 65, 65);
        pauzepane.setBounds(280,265,630,245);


        pauzepane.setVisible(false);
        Yes.setVisible(false);
        No.setVisible(false);
        confirmationlabel.setVisible(false);

        //==================================================

        //Add Components
        //==================================================
        panel.add(StartGame);
        panel.add(Profile);
        panel.add(Upgrades);
        panel.add(Settings);
        panel.add(Title);
        panel.add(Friends);
        panel.add(Quit);
        panel.add(LogOut);
        panel.add(Discord);
        panel.add(FriendsBtn);
        panel.add(JoinDiscord);

        panel.add(pauzepane);
        panel.add(Quit);
        panel.add(confirmationlabel);
        panel.add(Yes);
        panel.add(No);



    }
}
