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
                JLabel TotalPlayed = new GLabel("Total Hours played:", 24f, 1026,295,293,35, false, Color.BLACK);
                JLabel TotalPlayedResult = new GLabel("657h 49m", 24f, 1669,295,159,35, false, Color.BLACK);
                JLabel TotalNormal = new GLabel("Total Hours Normal:", 24f, 1026,372,298,35,false, Color.BLACK);
                JLabel TotalNormalResult = new GLabel("123h 12m", 24f, 1688,372,140,35, false, Color.BLACK);
                JLabel TotalRanked = new GLabel("Total Hours Ranked:", 24f, 1026,451,307,35, false, Color.BLACK);
                JLabel TotalRankedResult = new GLabel("534h 37m", 24f, 1671,451,157,35, false, Color.BLACK );
                JLabel OverallHighscore = new GLabel("Overall Highscore", 24f, 1026,585,275,35,false, Color.BLACK);
                JLabel OverallHighscoreResult = new GLabel("12 458 365 759", 24f,1593,585,234,35, false, Color.BLACK);
                JLabel SeasonHighscore = new GLabel("Current Season Highscore:", 24f, 1026,654,408,35, false, Color.BLACK);
                JLabel SeasonHighscoreResult = new GLabel("11 105 356 128", 24f, 1616,654,211,35,false, Color.BLACK);
                JLabel Upgrades = new GLabel("Upgrades bought:", 24f, 1026,771,271,35, false, Color.BLACK );
                JLabel UpgradesResult = new GLabel("2", 24f, 1807,771,20,35,false, Color.BLACK);
                JLabel Skins = new GLabel("Skins bought:", 24f, 1026,836,207,35, false, Color.BLACK);
                JLabel SkinsResult = new GLabel("12", 24f, 1797,836,30,35,false, Color.BLACK);
                
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
                frame.add(TotalPlayed);
                frame.add(TotalPlayedResult);
                frame.add(TotalNormal);
                frame.add(TotalNormalResult);
                frame.add(TotalRanked);
                frame.add(TotalRankedResult);
                frame.add(OverallHighscore);
                frame.add(OverallHighscoreResult);
                frame.add(SeasonHighscore);
                frame.add(SeasonHighscoreResult);
                frame.add(Upgrades);
                frame.add(UpgradesResult);
                frame.add(Skins);
                frame.add(SkinsResult);
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
