/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GComponents.GFont;
import GComponents.GPanel;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Renzie
 */
public class Login extends GPanel{
    
    private Login panel = this;

    private String url = "https://discordapp.com/";
    /**
     * @param args the command line arguments
     */
    public Login() throws MalformedURLException, IOException, FontFormatException {
        
        
      
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        //Make components
        //==================================================
        
        JLabel LoginPane = new JLabel();
        JLabel UsernameLabel = new JLabel("Username");
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

        panel.add(Title);

        panel.add(Username);
        panel.add(Password);

        panel.add(UsernameLabel);
        panel.add(PasswordLabel);
        panel.add(RegisterLabel);
        panel.add(Login);
        panel.add(Register);

        panel.add(LoginPane);



        //==================================================
    }
}
