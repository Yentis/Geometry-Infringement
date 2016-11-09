/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.gm;

import Font.GFont;
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
 *
 * @author Renzie
 */
public class MainMenu {

    private String url = "https://discordapp.com/";
    /**
     * @param args the command line arguments
     */
    public MainMenu() throws MalformedURLException, IOException, FontFormatException {
        
        
        //Make components
        //==================================================
                JFrame frame = new JFrame("Geometry Wars");
                JButton StartGame = new JButton("start Game");
                JButton Upgrades = new JButton("Upgrades");
                JButton Profile = new JButton("Profile");
                JButton Settings = new JButton("Settings");
                JButton FriendsBtn = new JButton("Friends");
                JButton LogOut = new JButton("Logout");
                JButton Quit = new JButton("Quit");
                JButton Discord = new JButton(new ImageIcon(((new ImageIcon("src\\Media\\Discord.jpg")).getImage()).getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH)));
                JLabel Title = new JLabel("Geometry Wars", SwingConstants.CENTER);
                JLabel JoinDiscord = new JLabel("Join Server!");
                JList Friends = new JList();
        
        
        //==================================================
      
        
        
        //Add Action Listener
        //==================================================
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
                 frame.dispose();
            }
        });
        //==================================================
        
        
        //Set Properties
        //==================================================
                Title.setFont(new GFont(80));
                Title.setOpaque(true);
                Title.setBackground(new Color(255,255,255,95));
                StartGame.setFont(new GFont(36));
                StartGame.setOpaque(true);
                StartGame.setBackground(new Color(255,255,255,200));
                Upgrades.setFont(new GFont(36));
                Upgrades.setOpaque(true);
                Upgrades.setBackground(new Color(255,255,255,200));
                Profile.setFont(new GFont(36));
                Profile.setOpaque(true);
                Profile.setBackground(new Color(255,255,255,200));
                Settings.setFont(new GFont(36));
                Settings.setOpaque(true);
                Settings.setBackground(new Color(255,255,255,200));
                FriendsBtn.setOpaque(true);
                FriendsBtn.setFont(new GFont(24));
                FriendsBtn.setBackground(new Color(255,255,255,200));
                LogOut.setOpaque(true);
                LogOut.setFont(new GFont(24));
                LogOut.setBackground(new Color(255,255,255,200));
                Quit.setOpaque(true);
                Quit.setFont(new GFont(24));
                Quit.setBackground(new Color(255,255,255,200));
                JoinDiscord.setFont(new GFont(24));
                JoinDiscord.setForeground(Color.white);
                
                
                
                
                

                
                Friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                Friends.setVisible(false);
                
        
        //==================================================
        
    frame.setContentPane(new JPanel() {
        BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 1920, 1080, this);
            
        }
    });
    frame.setLayout(null);
    
    
    
    
        //Set Bounds
        //==================================================
                 
        StartGame.setBounds(399,262,472,135);
        Upgrades.setBounds(507,474,380,97);
        Profile.setBounds(561,642,380,97);
        Settings.setBounds(574,818,380,97);
        Quit.setBounds(1616,984,270,67);
        LogOut.setBounds(1326,984,270,67);
        Title.setBounds(54,40,900,131);
        JoinDiscord.setBounds(102,1000,180,35);
        Friends.setBounds(1616,129,270,254);
        FriendsBtn.setBounds(1616,40,270,67);
        Discord.setBounds(22,983,65,65);
        
        //==================================================
        
        //Add Components
        //==================================================
                frame.add(StartGame);
                frame.add(Profile);
                frame.add(Upgrades);
                frame.add(Settings);
                frame.add(Title);
                frame.add(Friends);
                frame.add(Quit);
                frame.add(LogOut);
                frame.add(Discord);
                frame.add(FriendsBtn);
                frame.add(JoinDiscord);
        
        //==================================================
    
        
    

    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    }

    
    
     public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
         new MainMenu();
     }
}
