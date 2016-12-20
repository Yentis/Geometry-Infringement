/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GComponents.*;
import Game.Spel;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Renzie
 */
public class Login extends GPanel{
    
    private Login panel = this;

    public Login() throws MalformedURLException, IOException, FontFormatException {

        initComponents();
      
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        GLabel message = new GLabel("", 24f, 220,120,350,50, false, Color.white);
        GLabel lblusername = new GLabel("Username: ", 24f, 200,150,150,50, false, Color.white);
        GLabel lblpassword = new GLabel("Password: ", 24f, 200,220,150,50, false, Color.white);
        GInputField username = new GInputField(360,150,200,50);
        GPasswordField password = new GPasswordField(360,220,200,50);
        GButton register = new GButton("Register", 24f, 200,280,170,50);
        GButton login = new GButton("Login", 24f, 390,280,170,50);
        JButton Exit = new GButton("Exit", 24f, 820, 650, 170, 63);

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));
        message.setVisible(false);

        label.setBounds(25,25,650,100);

        this.add(label);
        this.add(lblusername);
        this.add(lblpassword);
        this.add(username);
        this.add(password);
        this.add(register);
        this.add(login);
        this.add(Exit);
        this.add(message);

        //Action Listeners
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getRegister().setVisible(true);
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(username.getText(), "")){
                    message.setText("Please enter a username");
                } else if (password.getPassword().length == 0){
                    message.setText("Please enter a password");
                } else {
                    String result = checkAndCreate(username.getText(), password.getPassword());

                    if(Objects.equals(result, "")){
                        message.setText("Login Successful");
                    } else {
                        message.setText(result + " is niet correct.");
                    }
                }
                message.setVisible(true);
            }
        });
    }

    private String checkAndCreate(String gebruikersnaam, char[] password){
        Spel spel = new Spel();
        String result = "";
        try {
            result = spel.loginChecker(gebruikersnaam, password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(Objects.equals(result, "")){
            //log in boys
            System.out.println("login successful");
        } else {
            return result;
        }
        return "";
    }
}
