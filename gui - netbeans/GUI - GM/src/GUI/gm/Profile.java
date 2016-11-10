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
public class Profile {


    /**
     * @param args the command line arguments
     */
    public Profile() throws MalformedURLException, IOException, FontFormatException {
        
        
        //Make components
        //==================================================
                JFrame frame = new JFrame("Geometry Wars");
                JLabel Title = new GTitle(54,40);
                JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920,1080, java.awt.Image.SCALE_SMOOTH))));
                JLabel Profile = new GPane(536,193,418,481);
                JLabel ProfileInfo = new GPane(976,40,902,1009);
                JLabel Username = new GLabel("Username", 36f,576,215,337,91,true, Color.BLACK);
                JLabel ProfilePicture = new GIcon("profilePicture.png",577,329,336,324, true);
                JLabel Rank = new GLabel("Unranked",36f,1243,130,280,62,false, Color.BLACK);
                JLabel RankPicture = new GIcon("Badges\\NoRank.png",1026,77,168,168,false);
                JButton MyClan = new GButton("My Clan",36f,536,696,418,96);
                JButton EditProfile = new GButton("Edit Profile",36f,606,820,278,67);
                JButton Back = new GButton("Back",36f,536,982,270,67);
                JButton Achievements = new GButton("Achievements", 36f, 60,982,340,67);
        
        
        //==================================================
      
        //Add Action Listener
        //==================================================
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    frame.setVisible(false);
                    frame.dispose();
                    new MainMenu();
                    
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
         
        //==================================================
    
        //Set Bounds
        //==================================================
             
        //==================================================
        
        //Add Components
        //==================================================
                
                frame.add(Title);
                frame.add(ProfilePicture);
                frame.add(MyClan);
                frame.add(EditProfile);
                frame.add(Back);
                frame.add(Achievements);
                frame.add(Username);
                frame.add(RankPicture);
                frame.add(Rank);
                frame.add(Profile);
                frame.add(ProfileInfo);
                frame.add(Background);
        //==================================================
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    }

     public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
         new Profile();
     }
}
