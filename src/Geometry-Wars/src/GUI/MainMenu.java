/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import GComponents.*;


/**
 * @author Renzie
 */
public class MainMenu extends GPanel {

    private String url = "https://discordapp.com/";

    private MainMenu panel = this;
    private Window window = (Window) SwingUtilities.getRoot(this);



    public MainMenu() throws MalformedURLException, IOException, FontFormatException {



        //Make components
        //==================================================

        JButton StartGame = new GButton("Start Game", 36f, 399, 262, 472, 135);
        JButton Upgrades = new GButton("Upgrades", 36f , 507,474,380,97 );
        JButton Profile = new GButton("Profile",36f , 561,642,380,97);
        JButton Settings = new GButton("Settings",36f,574,818,380,97);
        JButton FriendsBtn = new GButton("Friends",36f,1616,40,270,67);
        JButton LogOut = new GButton("Logout",36f,1326,984,270,67);
        JButton Quit = new GButton("Quit",36f,1616,984,270,67);
        JButton Discord = new JButton(new ImageIcon(((new ImageIcon("src\\Media\\Discord.jpg")).getImage()).getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH)));
        JLabel Title = new JLabel("Geometry Wars", SwingConstants.CENTER);
        JLabel JoinDiscord = new JLabel("Join Server!");
        //TODO put img in files (intelliJ only)
        //JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920,1080, java.awt.Image.SCALE_SMOOTH))));
        JList Friends = new JList();


        //==================================================



        //Add Action Listener
        //==================================================

        // TODO give same sort of buttons the same kind of function
        StartGame.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartGame().setVisible(true);


            }

        });

        FriendsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!Friends.isShowing()){
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
                }
                catch (java.io.IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        //TODO (IntelliJ)
        Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              //  try {
                    panel.setVisible(false);
                    //SwingUtilities.getWindowAncestor(JFrame);
                    //new Profile();
/*
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FontFormatException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }*/

           // }
        }});
        //==================================================


        //Set Properties
        //==================================================
        Title.setFont(new GFont(80));
        Title.setOpaque(true);
        Title.setBackground(new Color(255,255,255,95));
        JoinDiscord.setFont(new GFont(24));
        JoinDiscord.setForeground(Color.white);

        Friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Friends.setVisible(false);


        //==================================================

        //Set Bounds
        //==================================================


        Title.setBounds(54,40,900,131);
        JoinDiscord.setBounds(102,1000,180,35);
        Friends.setBounds(1616,129,270,254);

        Discord.setBounds(22,983,65,65);

        //TODO (IntelliJ)
        //Background.setBounds(0,0,1920,1080);

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
        //panel.add(Background);

        //==================================================
    }
}
