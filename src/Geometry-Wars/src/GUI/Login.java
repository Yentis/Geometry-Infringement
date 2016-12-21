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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.security.MessageDigest;
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
        GLabel message = new GLabel("", 24f, 220,120,600,50, false, Color.white);
        GLabel lblusername = new GLabel("Username: ", 24f, 200,170,150,50, false, Color.white);
        GLabel lblpassword = new GLabel("Password: ", 24f, 200,240,150,50, false, Color.white);
        GInputField username = new GInputField(360,170,200,50);
        GPasswordField password = new GPasswordField(360,240,200,50);
        GButton register = new GButton("Register", 24f, 200,300,170,50);
        JButton loginButton = new GButton("Login", 24f, 390,300,170,50);
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
        this.add(loginButton);
        this.add(Exit);
        this.add(message);

        //Action Listeners
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButton.doClick();
            }
        });

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButton.doClick();
            }
        });

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(username.getText(), "")){
                    message.setText("Please enter a username");
                } else if (password.getPassword().length == 0){
                    message.setText("Please enter a password");
                } else {
                    String result = null;
                    try {
                        result = checkAndCreate(username.getText(), password.getPassword());
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    if(Objects.equals(result, "")){
                        panel.setVisible(false);
                        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                        window.getMainMenu().setVisible(true);
                    } else {
                        message.setText(result + " is not correct.");
                    }
                }
                message.setVisible(true);
            }
        });
    }

    private String checkAndCreate(String gebruikersnaam, char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        String result = "";

        //hash
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(new String(password).getBytes("UTF-8"));
        byte[] digest = md.digest();
        String hashed = "";
        for (byte item:digest) {
            hashed += item;
        }

        result = window.getSpel().loginChecker(gebruikersnaam, hashed);

        if(Objects.equals(result, "")){
            window.getSpel().logIn(gebruikersnaam);
        } else {
            return result;
        }
        return "";
    }
}
