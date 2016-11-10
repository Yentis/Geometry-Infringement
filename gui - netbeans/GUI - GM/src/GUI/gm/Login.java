/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.gm;

import GComponents.*;
import Game.Spel;
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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
public class Login {

    private String url = "https://discordapp.com/";
    /**
     * @param args the command line arguments
     */
    public Login() throws MalformedURLException, IOException, FontFormatException {
        
        
        //Make components
        //==================================================
                JFrame frame = new JFrame("Geometry Wars");
                JLabel LoginPane = new JLabel();
                JLabel UsernameLabel = new JLabel("Username:");
                JLabel PasswordLabel = new JLabel("Password:");
                JLabel RegisterLabel = new JLabel("Not Registered yet?");
                JLabel Title = new JLabel("Geometry Wars", SwingConstants.CENTER);
                JButton Login = new GButton("Login", 24f ,1775,531,117,44);
                JButton Register = new GButton("Register",24f,1681,711,209,44);
                JTextField Username = new JTextField();
                JPasswordField Password = new JPasswordField();
                JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920,1080, java.awt.Image.SCALE_SMOOTH))));
               
        
        
        //==================================================
      
        
        
        //Add Action Listener
        //==================================================
          Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.setVisible(false);
                frame.dispose();
                try {
                    new Register();
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FontFormatException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
               
                
            }
        });
          
          Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Spel spel = new Spel();
                String gebruikersnaam = Username.getText();
                char[] wachtwoord = Password.getPassword();
                try {
                    if (spel.LoginPlayer(gebruikersnaam, wachtwoord)){
                        frame.setVisible(false);
                        frame.dispose();
                        
                        try {
                            new MainMenu();
                        } catch (IOException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FontFormatException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                } 
                catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            }
        });
        //==================================================
        
        
        //Set Properties
        //==================================================
                Title.setFont(new GFont(80));
                Title.setOpaque(true);
                Title.setBackground(new Color(255,255,255,95));
                
                LoginPane.setOpaque(true);
                LoginPane.setBackground(new Color(255,255,255,95));
                
                Username.setFont(new GFont(24));
                Username.setOpaque(true);
                Username.setBackground(new Color(255,255,255,200));
                
                Password.setFont(new GFont(24));
                Password.setOpaque(true);
                Password.setBackground(new Color(255,255,255,200));
                
                
                UsernameLabel.setFont(new GFont(20));
                UsernameLabel.setForeground(Color.BLACK);
                
                PasswordLabel.setFont(new GFont(20));
                PasswordLabel.setForeground(Color.black);
                
                RegisterLabel.setFont(new GFont(16));
                RegisterLabel.setForeground(Color.black);
                
               
                
               
                
                
                
                
                

              
                
        
        //==================================================

        //Set Bounds
        //==================================================
                 
        
        Title.setBounds(300,187,900,131);
        LoginPane.setBounds(1241,187,679,724);
        Username.setBounds(1535,346,357,49);
        Password.setBounds(1535,440,357,49);
        UsernameLabel.setBounds(1395, 358, 130,24);
        PasswordLabel.setBounds(1395,452,130,24);
        RegisterLabel.setBounds(1712,665,185,21);
        Background.setBounds(0,0,1920,1080);
        
        
        //==================================================
        
        //Add Components
        //==================================================
               
                frame.add(Title);
                
                frame.add(Username);
                frame.add(Password);
                
                frame.add(UsernameLabel);
                frame.add(PasswordLabel);
                frame.add(RegisterLabel);
                frame.add(Login);
                frame.add(Register);
               
                frame.add(LoginPane);
                frame.add(Background);
                
                
        
        //==================================================
    
        
    

    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    }

    
    
     public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
         new Login();
     }
}
