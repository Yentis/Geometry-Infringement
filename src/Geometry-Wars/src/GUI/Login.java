/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
                JButton Login = new JButton("Login");
                JButton Register = new JButton("Register");
                JTextField Username = new JTextField();
                JPasswordField Password = new JPasswordField();
               
        
        
        //==================================================
      
        
        
        //Add Action Listener
        //==================================================
       
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
                
                Login.setFont(new GFont(24));
                Login.setOpaque(true);
                Login.setBackground(new Color(255,255,255,200));
                Login.setForeground(Color.black);
                
                Register.setFont(new GFont(24));
                Register.setOpaque(true);
                Register.setBackground(new Color(255,255,255,200));
                Register.setForeground(Color.black);
                
               
                
                
                
                
                

              
                
        
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
                 
        
        Title.setBounds(300,187,900,131);
        LoginPane.setBounds(1241,187,679,724);
        Username.setBounds(1535,346,357,49);
        Password.setBounds(1535,440,357,49);
        UsernameLabel.setBounds(1395, 358, 130,24);
        PasswordLabel.setBounds(1395,452,130,24);
        RegisterLabel.setBounds(1712,665,185,21);
        Login.setBounds(1775,531,117,44);
        Register.setBounds(1681,711,209,44);
        
        
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
                
                
        
        //==================================================
    
        
    

    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    }

    
    
     public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
         new Login();
     }
}
