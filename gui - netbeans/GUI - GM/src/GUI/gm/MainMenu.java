/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.gm;

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
                JButton StartGame = new GButton("Start Game", 36f, 399, 262, 472, 135);
                JButton Upgrades = new GButton("Upgrades", 36f , 507,474,380,97 );
                JButton Profile = new GButton("Profile",36f , 561,642,380,97);
                JButton Settings = new GButton("Settings",36f,574,818,380,97);
                JButton FriendsBtn = new GButton("Friends",36f,1616,40,270,67);
                JButton LogOut = new GButton("Logout",36f,1326,984,270,67);
                JButton Quit = new GButton("Quit",36f,1616,984,270,67);
                JButton Discord = new JButton(new ImageIcon(((new ImageIcon("src\\Media\\Discord.jpg")).getImage()).getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH)));
                JLabel Title = new GTitle(54,40);
                JLabel JoinDiscord = new GLabel("Join Server!", 24f, 102,1000,180,35, false, Color.WHITE);
                JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920,1080, java.awt.Image.SCALE_SMOOTH))));
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
        Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    frame.setVisible(false);
                    frame.dispose();
                    new Profile();
                    
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FontFormatException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        //==================================================
        
        
        //Set Properties
        //==================================================
                   
                Friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                Friends.setVisible(false);
                
        
        //==================================================
  
        //Set Bounds
        //==================================================
                 
        
       
        Friends.setBounds(1616,129,270,254);
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
                frame.add(Background);
        
        //==================================================
    
        
    

    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    }

    
    
     public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
         new MainMenu();
     }
}
